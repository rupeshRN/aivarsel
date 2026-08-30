package com.varsel.expensetracker.ui.transaction.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DescriptionSection(
    description: String,
    onDescriptionChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Description / Merchant",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
<<<<<<< HEAD
<<<<<<< HEAD
            modifier = Modifier.padding(bottom = 6.dp)
=======
            modifier = Modifier.padding(bottom = 8.dp)
>>>>>>> de06015 (ui: enhance visual contrast and category filtering)
=======
            modifier = Modifier.padding(bottom = 6.dp)
>>>>>>> 740f58d (refactor(category): consolidate categories and migrate to vector icons)
        )

        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChanged,
            modifier = Modifier.fillMaxWidth(),
<<<<<<< HEAD
<<<<<<< HEAD
            shape = RoundedCornerShape(14.dp),
=======
            shape = RoundedCornerShape(16.dp),
>>>>>>> de06015 (ui: enhance visual contrast and category filtering)
=======
            shape = RoundedCornerShape(14.dp),
>>>>>>> 740f58d (refactor(category): consolidate categories and migrate to vector icons)
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            ),
<<<<<<< HEAD
<<<<<<< HEAD
            singleLine = true,
            maxLines = 2,
=======
            singleLine = false,
            minLines = 2,
>>>>>>> de06015 (ui: enhance visual contrast and category filtering)
=======
            singleLine = true,
            maxLines = 2,
>>>>>>> 740f58d (refactor(category): consolidate categories and migrate to vector icons)
            placeholder = {
                Text("Enter transaction narration or merchant name")
            }
        )
    }
}

