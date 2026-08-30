package com.varsel.expensetracker.ui.dashboard.components

import androidx.compose.foundation.layout.*
<<<<<<< HEAD
<<<<<<< HEAD
import androidx.compose.foundation.shape.RoundedCornerShape
=======
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
import androidx.compose.foundation.shape.RoundedCornerShape
>>>>>>> 5e062f3 (feat(ui): refine dashboard aesthetic and interactions)
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.varsel.expensetracker.ui.model.TransactionUiModel

@Composable
fun DashboardRecentSection(
    transactions: List<TransactionUiModel>,
    onViewAll: () -> Unit,
    onTransactionClick: (TransactionUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
<<<<<<< HEAD
<<<<<<< HEAD
        verticalArrangement = Arrangement.spacedBy(8.dp)
=======
        verticalArrangement = Arrangement.spacedBy(10.dp)
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
        verticalArrangement = Arrangement.spacedBy(8.dp)
>>>>>>> 5e062f3 (feat(ui): refine dashboard aesthetic and interactions)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Recent Transactions",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (transactions.isNotEmpty()) {
                TextButton(
                    onClick = onViewAll,
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
                ) {
                    Text(
                        text = "View All",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        if (transactions.isEmpty()) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
<<<<<<< HEAD
<<<<<<< HEAD
                shape = RoundedCornerShape(18.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.30f)
=======
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
                shape = RoundedCornerShape(18.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.30f)
>>>>>>> 5e062f3 (feat(ui): refine dashboard aesthetic and interactions)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "🧾",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = "No Transactions Yet",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Import a bank statement to get started",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 5e062f3 (feat(ui): refine dashboard aesthetic and interactions)
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.30f),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.20f)
<<<<<<< HEAD
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    transactions.take(5).forEachIndexed { index, transaction ->
                        RecentTransactionCard(
                            transaction = transaction,
                            onClick = { onTransactionClick(transaction) }
                        )

                        if (index < transactions.take(5).lastIndex) {
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 14.dp),
                                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.18f),
                                thickness = 0.8.dp
                            )
                        }
                    }
                }
=======
            transactions.take(5).forEach { transaction ->
                RecentTransactionCard(
                    transaction = transaction,
                    onClick = { onTransactionClick(transaction) }
                )
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    transactions.take(5).forEachIndexed { index, transaction ->
                        RecentTransactionCard(
                            transaction = transaction,
                            onClick = { onTransactionClick(transaction) }
                        )

                        if (index < transactions.take(5).lastIndex) {
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 14.dp),
                                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.18f),
                                thickness = 0.8.dp
                            )
                        }
                    }
                }
>>>>>>> 5e062f3 (feat(ui): refine dashboard aesthetic and interactions)
            }
        }
    }
}
