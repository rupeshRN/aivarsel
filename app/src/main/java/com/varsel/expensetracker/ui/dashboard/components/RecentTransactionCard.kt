package com.varsel.expensetracker.ui.dashboard.components

import androidx.compose.foundation.clickable
<<<<<<< HEAD
<<<<<<< HEAD
import androidx.compose.foundation.isSystemInDarkTheme
=======
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
import androidx.compose.foundation.isSystemInDarkTheme
>>>>>>> 5e062f3 (feat(ui): refine dashboard aesthetic and interactions)
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
<<<<<<< HEAD
import com.varsel.expensetracker.category.CategoryIconCatalog
import com.varsel.expensetracker.ui.design.CategoryPalette
=======
import androidx.compose.ui.unit.sp
import com.varsel.expensetracker.category.CategoryMetadata
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
import com.varsel.expensetracker.ui.model.TransactionUiModel

@Composable
fun RecentTransactionCard(
    transaction: TransactionUiModel,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
<<<<<<< HEAD
<<<<<<< HEAD
    val isDark = isSystemInDarkTheme()
    val incomeColor = if (isDark) Color(0xFF81C784) else Color(0xFF1B5E20)
    val expenseColor = if (isDark) Color(0xFFFF8A80) else Color(0xFFB71C1C)

    val categoryColor = CategoryPalette.colorFor(transaction.category)
    val categoryIcon = CategoryIconCatalog.iconFor(transaction.category)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = onClick != null) {
                onClick?.invoke()
            }
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Category Icon Avatar with standardized vector icon and semantic palette tint
        Surface(
            shape = CircleShape,
            color = categoryColor.copy(alpha = 0.14f),
            modifier = Modifier.size(40.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = categoryIcon,
                    contentDescription = transaction.category,
                    tint = categoryColor,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // Description & Metadata
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = transaction.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = transaction.category,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "•",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outlineVariant
                )

                Text(
                    text = transaction.dateText,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
=======
    Surface(
=======
    val isDark = isSystemInDarkTheme()
    val incomeColor = if (isDark) Color(0xFF66BB6A) else Color(0xFF2E7D32)
    val expenseColor = if (isDark) Color(0xFFEF5350) else Color(0xFFC62828)

    Row(
>>>>>>> 5e062f3 (feat(ui): refine dashboard aesthetic and interactions)
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = onClick != null) {
                onClick?.invoke()
            }
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Category Emoji Avatar with subtle semantic tint
        val emoji = CategoryMetadata.emojiForCategory(
            transaction.category,
            isIncome = transaction.isIncome
        )
        Surface(
            shape = CircleShape,
            color = if (transaction.isIncome) {
                incomeColor.copy(alpha = 0.12f)
            } else {
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.70f)
            },
            modifier = Modifier.size(40.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
<<<<<<< HEAD
                    text = transaction.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = transaction.category,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = "•",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.outline
                    )

                    Text(
                        text = transaction.dateText,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
            }
        }

<<<<<<< HEAD
        // Amount Display with semantic green / red colors
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (transaction.isIncome) "+${transaction.amountText}" else "-${transaction.amountText}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = if (transaction.isIncome) incomeColor else expenseColor
            )
=======
            // Amount Display
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (transaction.isIncome) "+${transaction.amountText}" else "-${transaction.amountText}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (transaction.isIncome) {
                        Color(0xFF2E7D32)
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
=======
                    text = emoji,
                    fontSize = 18.sp
>>>>>>> 5e062f3 (feat(ui): refine dashboard aesthetic and interactions)
                )
            }
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
        }

        // Description & Metadata
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = transaction.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = transaction.category,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "•",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outlineVariant
                )

                Text(
                    text = transaction.dateText,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Amount Display with semantic green / red colors
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (transaction.isIncome) "+${transaction.amountText}" else "-${transaction.amountText}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = if (transaction.isIncome) incomeColor else expenseColor
            )
        }
    }
}
