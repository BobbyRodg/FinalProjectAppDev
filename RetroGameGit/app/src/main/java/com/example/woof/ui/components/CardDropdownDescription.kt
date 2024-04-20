package com.example.woof.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.woof.R

/**
 * Composable that displays a game's description.
 *
 * @param description is the text string of the description to display
 * @param modifier modifiers to set to this composable
 */
@Composable
fun CardDropdownDescription(
    description: String,
    releaseYear: Int,
    onChangeScreen: (Int) -> Unit, // Add this parameter
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "About:",
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = "Release Year: $releaseYear",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge
        )
        Button(
            onClick = { onChangeScreen(2) }, // Navigate to the Buy Page (page 2)
            modifier = Modifier.padding(vertical = 8.dp) // Add some padding for better spacing
        ) {
            Text("More Info")
        }
    }
}