package com.marcsedev.rickandmortymasterlistapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun MyFloatingActionButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = {
            onClick()
        },
    ) {
        Icon(imageVector = Icons.Rounded.KeyboardArrowUp, contentDescription = null)

    }
}