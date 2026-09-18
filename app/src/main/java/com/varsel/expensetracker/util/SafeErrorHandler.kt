package com.varsel.expensetracker.util

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import kotlinx.coroutines.CancellationException
import java.io.FileNotFoundException
import java.io.IOException

/**
 * Centralized exception handler that maps raw Android/JVM exceptions to typed [AppError]s
 * while logging sanitized diagnostic details and strictly preserving coroutine cancellation.
 */
object SafeErrorHandler {

    /**
     * Inspects a throwable, logs the sanitized failure, and maps it to a safe [AppError].
     *
     * @param tag Logging tag (e.g. ViewModel name).
     * @param throwable The thrown exception.
     * @param operationContext Optional operation name (e.g. "Save Transaction", "Import Statement").
     * @return Safe [AppError] category.
     * @throws CancellationException Always re-thrown to avoid breaking coroutine lifecycles.
     */
    fun handle(
        tag: String,
        throwable: Throwable,
        operationContext: String? = null
    ): AppError {
        // CRITICAL: Never swallow or convert CancellationException
        if (throwable is CancellationException) {
            SafeLog.d(tag, "Operation cancelled: ${operationContext ?: "job"}")
            throw throwable
        }

        val contextPrefix = if (operationContext != null) "[$operationContext] " else ""
        SafeLog.e(
            tag = tag,
            message = "${contextPrefix}Failure occurred: ${throwable.javaClass.simpleName}",
            throwable = throwable
        )

        val exceptionName = throwable.javaClass.name.lowercase()
        val message = throwable.message?.lowercase() ?: ""

        return when {
            // Password-protected PDF / Invalid PDF password
            exceptionName.contains("invalidpassword") || message.contains("password") -> {
                AppError.InvalidPassword
            }
            // Room / SQLite database constraints (foreign keys, unique index violations)
            throwable is SQLiteConstraintException -> {
                AppError.Database(operation = operationContext, isConstraintViolation = true)
            }
            // Generic SQLite errors (disk I/O, corrupted db, locked table)
            throwable is SQLiteException -> {
                AppError.Database(operation = operationContext, isConstraintViolation = false)
            }
            // Security / Permissions
            throwable is SecurityException -> {
                AppError.PermissionDenied
            }
            // File not found / URI revoked
            throwable is FileNotFoundException -> {
                AppError.FileNotFound
            }
            // File I/O errors (disk full, unexpected EOF, corrupted stream)
            throwable is IOException -> {
                if (message.contains("enospc") || message.contains("no space left")) {
                    AppError.StorageFull
                } else {
                    AppError.InvalidFile(reason = "File unreadable or damaged")
                }
            }
            // KeyStore / Crypto issues
            exceptionName.contains("keystore") || exceptionName.contains("crypto") -> {
                AppError.KeyStoreError(reason = "Security provider failure")
            }
            // Input validation errors
            throwable is IllegalArgumentException -> {
                AppError.InvalidInput(reason = "Invalid input values provided")
            }
            // Default unclassified error
            else -> {
                AppError.Unknown(technicalMessage = throwable.javaClass.simpleName)
            }
        }
    }
}
