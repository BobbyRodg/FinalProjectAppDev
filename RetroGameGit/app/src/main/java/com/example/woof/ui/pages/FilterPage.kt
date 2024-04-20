package com.example.woof.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.woof.data.games
import com.example.woof.ui.components.ConditionDropdownMenuBox
import com.example.woof.ui.components.ConsoleDropdownMenuBox

@Composable
fun FilterPage(whichScreen: Int, onChangeScreen: (Int) -> Unit,
               consoleFilter: String, changeConsoleFilter: (String) -> Unit,
               conditionFilter: Int, changeConditionFilter: (Int) -> Unit
) {

    val conditionMap = mapOf<Int, String>(
        4 to "New",
        3 to "Opened, Like New",
        2 to "Very Good",
        1 to "Good",
        0 to "For Parts. Not operational",
        -1 to "All",
    )

    val consoles: List<String> = listOf("All") + games.map { stringResource(it.console) }.toList().distinct()

    val conditions: List<String> = listOf("All") + games.map { conditionMap[it.condition] ?: "null" }.toList().distinct()


    Column(){
        Row(){
            Text(text="Console")
            ConsoleDropdownMenuBox(consoles, consoleFilter, changeConsoleFilter)
        }
        Row(){
            Text(text="Condition")
            ConditionDropdownMenuBox(conditions, conditionFilter, changeConditionFilter)
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Button(onClick = { onChangeScreen(1) }, modifier = Modifier.padding(30.dp)) {
                Text(text="Accept")
            }
        }

    }

}