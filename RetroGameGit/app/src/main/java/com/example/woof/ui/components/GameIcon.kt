package com.example.woof.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.example.woof.R

/**
 * Composable that displays a photo of a game.
 *
 * @param gameIcon is the resource ID for the image of the game
 * @param modifier modifiers to set to this composable
 */
@Composable
fun GameIcon(
    @DrawableRes gameIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .aspectRatio(1F)
            .fillMaxSize()
            .size(dimensionResource(R.dimen.image_size))
        //.clip(MaterialTheme.shapes.small)
        //.padding(dimensionResource(R.dimen.padding_small))
        ,
        contentScale = ContentScale.Fit,
        painter = painterResource(gameIcon),

        // Content Description is not needed here - image is decorative, and setting a null content
        // description allows accessibility services to skip this element during navigation.

        contentDescription = null
    )
}