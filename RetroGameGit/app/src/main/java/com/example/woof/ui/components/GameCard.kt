package com.example.woof.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.woof.R
import com.example.woof.RetroViewModel
import com.example.woof.data.Game
import com.example.woof.data.GameEntity

@Composable
fun GameCard(
    game: GameEntity,
    onChangeScreen: (Int) -> Unit, // Add onChangeScreen parameter
    modifier: Modifier = Modifier,
    viewmodel: RetroViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small))
            ){
                GameIcon(game.imageResourceId)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                GameItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                )
                GameInformation(game.title, game.condition, game.releaseYear)
                Spacer(Modifier.weight(1f))
            }
            if (expanded) {
                //game.selected = true
                viewmodel.updateSelectedById(game.id, true)
                CardDropdownDescription(
                    game.description, game.releaseYear, onChangeScreen, // Pass onChangeScreen
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        bottom = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
            else{
                //game.selected = false
                viewmodel.updateSelectedById(game.id, false)
            }
        }
    }
}