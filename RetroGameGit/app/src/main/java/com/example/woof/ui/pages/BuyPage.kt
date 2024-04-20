package com.example.woof.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.woof.R
import com.example.woof.RetroViewModel
import com.example.woof.data.GameEntity

@Composable
fun BuyPage(whichScreen: Int, onChangeScreen: (Int) -> Unit, viewModel: RetroViewModel){
    var selectedGame = viewModel.getSelectedGame()

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Start
        ){
            IconButton(
                onClick = { onChangeScreen(1) }, // Navigate back to the previous screen
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "Back button"
                )
            }
        }
        Image(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_small)),
            painter = painterResource(selectedGame.imageResourceId),
            contentDescription = selectedGame.title
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = selectedGame.title, style = MaterialTheme.typography.displayMedium)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text="Price: $")
                Button(onClick = { onChangeScreen(4) }){
                    Text("BUY NOW")
                }
            }
        }
    }
}