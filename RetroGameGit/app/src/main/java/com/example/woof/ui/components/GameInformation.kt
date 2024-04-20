package com.example.woof.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.woof.R

/**
 * Composable that displays a game's name and release year.
 *
 * @param name is the string of the game's name
 * @param releaseYear is the Int that represents the game's release date
 * @param modifier modifiers to set to this composable
 */
@Composable
fun GameInformation(
    name: String,
    conditionNum: Int,
    releaseYear: Int,
    modifier: Modifier = Modifier
) {
    val conditionMap = mapOf<Int, String>(
        4 to "New",
        3 to "Opened, Like New",
        2 to "Very Good",
        1 to "Good",
        0 to "For Parts. Not operational",
    )

    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(R.string.condition, conditionMap[conditionNum] ?: "Missing"),
            style = MaterialTheme.typography.bodyMedium
        )
    }

}