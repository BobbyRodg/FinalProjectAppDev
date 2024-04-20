package com.example.woof

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.example.woof.data.Game
import com.example.woof.data.GameEntity
import com.example.woof.data.games
import com.example.woof.ui.components.GameCard
import com.example.woof.ui.components.MenuButtons
import com.example.woof.ui.components.StoreTopAppBar
import com.example.woof.ui.pages.LoginPage

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetroStoreApp()
        }
    }
}

/**
 * Composable that displays an app bar and a list of games.
 */
@Composable
fun RetroGameApp(
    whichScreen: Int,
    onChangeScreen: (Int) -> Unit,
    consoleFilter: String,
    conditionFilter: Int,
    viewModel: RetroViewModel
) {

    //var filteredGames = games.filter { (consoleFilter == stringResource(id = it.console) || consoleFilter == "all" ) && (conditionFilter == it.condition || conditionFilter == -1)}
    var filteredGames by remember { mutableStateOf(emptyList<GameEntity>()) }

    val allGames = viewModel.getAllGames().observeAsState(initial = emptyList<GameEntity>())

    filteredGames = allGames.value

    LaunchedEffect(Unit) {
        viewModel.getAllGames() // Trigger data fetch on launch
    }



    Scaffold(
        topBar = {
            Column {
                StoreTopAppBar()
                Spacer(modifier = Modifier.height(3.dp))
                MenuButtons(whichScreen, onChangeScreen, viewModel = viewModel)
            }
        }
    ) { it ->

        LazyVerticalGrid(
            GridCells.Fixed(2),
            contentPadding = it,
        ) {
            items(filteredGames, span = { game ->
                val span = if (game.selected == true ){
                    maxCurrentLineSpan
                }
                else{
                    1
                }
                GridItemSpan(span)
            }
            ) {
                GameCard(
                    game = it,
                    onChangeScreen = onChangeScreen, // Pass onChangeScreen
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                    viewmodel = viewModel
                )
            }
        }
    }
}


