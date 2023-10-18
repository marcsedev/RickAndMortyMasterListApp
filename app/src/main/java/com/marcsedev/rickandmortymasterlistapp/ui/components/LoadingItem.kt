package com.marcsedev.rickandmortymasterlistapp.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.marcsedev.rickandmortymasterlistapp.R

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.cielo_estrellado),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
        )
        CircularProgressIndicator(
            Modifier
                .align(Alignment.Center)
                .animateContentSize()
        )
    }

}