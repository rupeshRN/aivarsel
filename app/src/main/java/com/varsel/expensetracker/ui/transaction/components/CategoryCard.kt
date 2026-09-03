package com.varsel.expensetracker.ui.transaction.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
<<<<<<< HEAD
<<<<<<< HEAD
import androidx.compose.foundation.layout.size
=======
>>>>>>> de06015 (ui: enhance visual contrast and category filtering)
=======
import androidx.compose.foundation.layout.size
>>>>>>> ad6b817 (major auto link transfer and hdfc aupport)
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.varsel.expensetracker.category.CategoryUi

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    category: CategoryUi,
    selected: Boolean,
    onClick: () -> Unit
) {
<<<<<<< HEAD
<<<<<<< HEAD
    val categoryColor = category.color

=======
>>>>>>> de06015 (ui: enhance visual contrast and category filtering)
=======
    val categoryColor = category.color

>>>>>>> ad6b817 (major auto link transfer and hdfc aupport)
    Card(
        modifier = modifier
            .aspectRatio(1.22f)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
<<<<<<< HEAD
<<<<<<< HEAD
                categoryColor.copy(alpha = 0.15f)
=======
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.85f)
>>>>>>> de06015 (ui: enhance visual contrast and category filtering)
=======
                categoryColor.copy(alpha = 0.15f)
>>>>>>> ad6b817 (major auto link transfer and hdfc aupport)
            else
                MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (selected) 2.dp else 0.dp
        ),
        border = if (selected) {
<<<<<<< HEAD
<<<<<<< HEAD
            BorderStroke(2.dp, categoryColor)
=======
            BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
>>>>>>> de06015 (ui: enhance visual contrast and category filtering)
=======
            BorderStroke(2.dp, categoryColor)
>>>>>>> ad6b817 (major auto link transfer and hdfc aupport)
        } else {
            BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> ad6b817 (major auto link transfer and hdfc aupport)
            Icon(
                imageVector = category.icon,
                contentDescription = category.id,
                tint = if (selected) categoryColor else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
<<<<<<< HEAD
            )

            Spacer(modifier = Modifier.height(4.dp))

=======
            Text(
                text = category.icon,
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 24.sp
            )

>>>>>>> de06015 (ui: enhance visual contrast and category filtering)
=======
            )

            Spacer(modifier = Modifier.height(4.dp))

>>>>>>> ad6b817 (major auto link transfer and hdfc aupport)
            Text(
                text = category.id,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
<<<<<<< HEAD
<<<<<<< HEAD
                color = if (selected) categoryColor else MaterialTheme.colorScheme.onSurface,
=======
                color = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface,
>>>>>>> de06015 (ui: enhance visual contrast and category filtering)
=======
                color = if (selected) categoryColor else MaterialTheme.colorScheme.onSurface,
>>>>>>> ad6b817 (major auto link transfer and hdfc aupport)
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
