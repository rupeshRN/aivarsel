package com.varsel.expensetracker.ui.dashboard.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
<<<<<<< HEAD
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
=======
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.ArrowUpward
<<<<<<< HEAD
import androidx.compose.material.icons.outlined.CreditCard
=======
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varsel.expensetracker.ui.components.BankLogoBadge
import com.varsel.expensetracker.ui.model.AccountBalanceUiModel
import com.varsel.expensetracker.ui.model.BalanceSummaryUiModel
import kotlin.math.abs

@Composable
fun BalanceCard(
    summary: BalanceSummaryUiModel,
    modifier: Modifier = Modifier
) {
    var isBalanceHidden by remember { mutableStateOf(false) }
<<<<<<< HEAD
    val isDark = isSystemInDarkTheme()
=======
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        //--------------------------------------------------
<<<<<<< HEAD
        // Hero Balance Card with Rich Tonal Depth Gradient
        //--------------------------------------------------
        val heroGradient = Brush.linearGradient(
            colors = if (isDark) {
                listOf(
                    MaterialTheme.colorScheme.primaryContainer,
                    MaterialTheme.colorScheme.surfaceColorAtElevation(6.dp),
                    MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp)
                )
            } else {
                listOf(
                    MaterialTheme.colorScheme.primaryContainer,
                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.85f),
                    MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.65f)
                )
            }
        )

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(26.dp),
            shadowElevation = if (isDark) 2.dp else 4.dp,
            tonalElevation = 4.dp,
            border = androidx.compose.foundation.BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = if (isDark) 0.25f else 0.18f)
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(heroGradient)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 22.dp, vertical = 22.dp),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {
                    // Header with Privacy Eye Toggle
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "Net Liquid Balance",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = 0.5.sp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                            )
                        }

                        IconButton(
                            onClick = { isBalanceHidden = !isBalanceHidden },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = if (isBalanceHidden) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                                contentDescription = if (isBalanceHidden) "Show balance" else "Hide balance",
                                tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    // Dominant Hero Balance Amount Display
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = if (isBalanceHidden) "₹ ••••••••" else "₹%,.2f".format(summary.totalBalance),
                            style = MaterialTheme.typography.displaySmall.copy(
                                fontSize = 38.sp,
                                lineHeight = 44.sp
                            ),
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            letterSpacing = (-1).sp
=======
        // Main Hero Balance Card
        //--------------------------------------------------
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Total Balance Header + Eye Toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Net Liquid Balance",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                    )

                    IconButton(
                        onClick = { isBalanceHidden = !isBalanceHidden },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = if (isBalanceHidden) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                            contentDescription = if (isBalanceHidden) "Show balance" else "Hide balance",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                            modifier = Modifier.size(18.dp)
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
                        )
                    }
                }

<<<<<<< HEAD
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.15f),
                        thickness = 1.dp
=======
                // Balance Amount Display
                Text(
                    text = if (isBalanceHidden) "₹ ••••••••" else "₹%,.2f".format(summary.totalBalance),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    letterSpacing = (-0.5).sp
                )

                HorizontalDivider(
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.12f),
                    thickness = 1.dp
                )

                // Monthly Income and Expense Pills
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    IncomeExpensePill(
                        modifier = Modifier.weight(1f),
                        title = "Income",
                        amount = summary.totalIncome,
                        isIncome = true,
                        isBalanceHidden = isBalanceHidden,
                        changePercent = summary.incomeChangePercent
                    )

                    IncomeExpensePill(
                        modifier = Modifier.weight(1f),
                        title = "Expense",
                        amount = summary.totalExpense,
                        isIncome = false,
                        isBalanceHidden = isBalanceHidden,
                        changePercent = summary.expenseChangePercent
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
                    )

                    // Monthly Income and Expense Pills with Strong Semantic Styling
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        IncomeExpensePill(
                            modifier = Modifier.weight(1f),
                            title = "Income",
                            amount = summary.totalIncome,
                            isIncome = true,
                            isBalanceHidden = isBalanceHidden,
                            changePercent = summary.incomeChangePercent
                        )

                        IncomeExpensePill(
                            modifier = Modifier.weight(1f),
                            title = "Expense",
                            amount = summary.totalExpense,
                            isIncome = false,
                            isBalanceHidden = isBalanceHidden,
                            changePercent = summary.expenseChangePercent
                        )
                    }
                }
            }
        }

        //--------------------------------------------------
<<<<<<< HEAD
        // Account-wise Section (Slide Carousel for multiple)
        //--------------------------------------------------
        if (summary.accounts.isNotEmpty()) {
            val listState = rememberLazyListState()

=======
        // Account-wise Section with Bank Badges
        //--------------------------------------------------
        if (summary.accounts.isNotEmpty()) {
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
<<<<<<< HEAD
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.CreditCard,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = if (summary.accounts.size == 1) "Bank Account" else "Linked Accounts (${summary.accounts.size})",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    if (summary.accounts.size > 1) {
                        Text(
                            text = "Swipe to view →",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                        )
                    }
                }

                // If single account: full width card. If multiple: horizontal slide cards
                if (summary.accounts.size == 1) {
                    BankAccountCard(
                        account = summary.accounts.first(),
                        isBalanceHidden = isBalanceHidden,
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        LazyRow(
                            state = listState,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            contentPadding = PaddingValues(horizontal = 2.dp)
                        ) {
                            items(summary.accounts) { account ->
                                BankAccountCard(
                                    account = account,
                                    isBalanceHidden = isBalanceHidden,
                                    modifier = Modifier.width(260.dp)
                                )
                            }
                        }

                        // Slide Indicator Dots
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 2.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            summary.accounts.forEachIndexed { index, _ ->
                                val isSelected = derivedStateOf {
                                    listState.firstVisibleItemIndex == index
                                }
                                Box(
                                    modifier = Modifier
                                        .padding(horizontal = 3.dp)
                                        .height(4.dp)
                                        .width(if (isSelected.value) 16.dp else 6.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (isSelected.value) {
                                                MaterialTheme.colorScheme.primary
                                            } else {
                                                MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
                                            }
                                        )
                                )
                            }
=======
                    Text(
                        text = "Bank Accounts (${summary.accounts.size})",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                // If 1 or 2 accounts, show 2-column or stacked cards; if more, use scrollable row
                if (summary.accounts.size <= 2) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        summary.accounts.forEach { account ->
                            BankAccountCard(
                                account = account,
                                isBalanceHidden = isBalanceHidden,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                } else {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(horizontal = 2.dp)
                    ) {
                        items(summary.accounts) { account ->
                            BankAccountCard(
                                account = account,
                                isBalanceHidden = isBalanceHidden,
                                modifier = Modifier.width(220.dp)
                            )
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun IncomeExpensePill(
    modifier: Modifier = Modifier,
    title: String,
    amount: Double,
    isIncome: Boolean,
    isBalanceHidden: Boolean,
    changePercent: Double?
) {
<<<<<<< HEAD
    val isDark = isSystemInDarkTheme()

    // High Contrast Semantic Green & Red Palettes
    val primaryColor = if (isIncome) {
        if (isDark) Color(0xFF81C784) else Color(0xFF1B5E20)
    } else {
        if (isDark) Color(0xFFFF8A80) else Color(0xFFB71C1C)
    }

    val pillBackground = if (isDark) {
        MaterialTheme.colorScheme.surface.copy(alpha = 0.65f)
    } else {
        Color(0xFFFFFFFF).copy(alpha = 0.92f)
    }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = pillBackground,
        shadowElevation = if (isDark) 0.dp else 1.dp,
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isDark) MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.30f)
            else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.20f)
        )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        imageVector = if (isIncome) Icons.Outlined.ArrowDownward else Icons.Outlined.ArrowUpward,
                        contentDescription = null,
                        tint = primaryColor,
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = primaryColor
                    )
                }

=======
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = if (isIncome) Icons.Outlined.ArrowDownward else Icons.Outlined.ArrowUpward,
                    contentDescription = null,
                    tint = if (isIncome) Color(0xFF2E7D32) else Color(0xFFC62828),
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
                changePercent?.let { pct ->
                    val arrow = if (pct > 0) "↑" else "↓"
                    Text(
                        text = "$arrow${abs(pct).toInt()}%",
                        style = MaterialTheme.typography.labelSmall,
<<<<<<< HEAD
                        fontWeight = FontWeight.ExtraBold,
                        color = primaryColor
=======
                        fontWeight = FontWeight.Bold,
                        color = if (isIncome) {
                            if (pct >= 0) Color(0xFF2E7D32) else MaterialTheme.colorScheme.error
                        } else {
                            if (pct <= 0) Color(0xFF2E7D32) else MaterialTheme.colorScheme.error
                        }
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
                    )
                }
            }

            Text(
                text = if (isBalanceHidden) "₹ ••••" else "₹%,.2f".format(amount),
<<<<<<< HEAD
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                color = primaryColor
=======
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
            )
        }
    }
}

@Composable
private fun BankAccountCard(
    account: AccountBalanceUiModel,
    isBalanceHidden: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
<<<<<<< HEAD
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.40f),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.25f)
=======
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
        )
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                BankLogoBadge(
                    bankName = account.bankName,
<<<<<<< HEAD
                    size = 34.dp
=======
                    size = 32.dp
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
                )

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = account.bankName,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1
                    )
                    Text(
                        text = account.accountDisplayName,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

<<<<<<< HEAD
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
=======
            Column {
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
                Text(
                    text = "Available Balance",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = if (isBalanceHidden) "₹ •••••" else "₹%,.2f".format(account.balance),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
