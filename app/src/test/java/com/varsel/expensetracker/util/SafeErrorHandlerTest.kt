package com.varsel.expensetracker.util

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import kotlinx.coroutines.CancellationException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.FileNotFoundException
import java.io.IOException

class SafeErrorHandlerTest {

    @Test(expected = CancellationException::class)
    fun `handle rethrows CancellationException unconditionally`() {
        val cancellation = CancellationException("Job was cancelled")
        SafeErrorHandler.handle("TestTag", cancellation)
    }

    @Test
    fun `handle maps SQLiteConstraintException to Database constraint error`() {
        val exception = SQLiteConstraintException("UNIQUE constraint failed: table.column")
        val error = SafeErrorHandler.handle("TestTag", exception, "Insert Item")

        assertTrue(error is AppError.Database)
        val dbError = error as AppError.Database
        assertTrue(dbError.isConstraintViolation)
        assertEquals("Insert Item", dbError.operation)
    }

    @Test
    fun `handle maps generic SQLiteException to Database error without constraint flag`() {
        val exception = SQLiteException("no such table: transactions")
        val error = SafeErrorHandler.handle("TestTag", exception)

        assertTrue(error is AppError.Database)
        val dbError = error as AppError.Database
        assertEquals(false, dbError.isConstraintViolation)
    }

    @Test
    fun `handle maps SecurityException to PermissionDenied`() {
        val exception = SecurityException("Permission denial")
        val error = SafeErrorHandler.handle("TestTag", exception)

        assertEquals(AppError.PermissionDenied, error)
    }

    @Test
    fun `handle maps FileNotFoundException to FileNotFound`() {
        val exception = FileNotFoundException("File not found")
        val error = SafeErrorHandler.handle("TestTag", exception)

        assertEquals(AppError.FileNotFound, error)
    }

    @Test
    fun `handle maps IOException with ENOSPC to StorageFull`() {
        val exception = IOException("write failed: ENOSPC (No space left on device)")
        val error = SafeErrorHandler.handle("TestTag", exception)

        assertEquals(AppError.StorageFull, error)
    }

    @Test
    fun `handle maps IllegalArgumentException to InvalidInput`() {
        val exception = IllegalArgumentException("Amount cannot be negative")
        val error = SafeErrorHandler.handle("TestTag", exception)

        assertTrue(error is AppError.InvalidInput)
    }

    @Test
    fun `sanitize masks account numbers, emails, passwords, and cards`() {
        val textWithAccount = "Processing account 123456789012 for user@example.com with password=mySecretPassword123"
        val sanitized = SafeLog.sanitize(textWithAccount)

        assertTrue(!sanitized.contains("123456789012"))
        assertTrue(sanitized.contains("XX9012"))
        assertTrue(!sanitized.contains("mySecretPassword123"))
        assertTrue(sanitized.contains("password=***REDACTED***"))
        assertTrue(!sanitized.contains("user@example.com"))
        assertTrue(sanitized.contains("u***@example.com"))
    }

    @Test
    fun `AppErrorMessageMapper returns friendly message without sql or path leaks`() {
        val dbError = AppError.Database(operation = "Save Transaction", isConstraintViolation = true)
        val message = AppErrorMessageMapper.getUserMessage(dbError)

        assertTrue(!message.contains("SQL"))
        assertTrue(!message.contains("SQLite"))
        assertTrue(message.isNotEmpty())

        val suggestion = AppErrorMessageMapper.getActionSuggestion(dbError)
        assertEquals("Retry saving", suggestion)
    }
}
