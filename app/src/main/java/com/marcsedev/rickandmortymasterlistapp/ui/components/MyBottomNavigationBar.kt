package com.marcsedev.rickandmortymasterlistapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color


@Composable
fun MyBottomNavigationBar() {
    var index by remember { mutableIntStateOf(0) }

    NavigationBar(
        containerColor = Color.Black
    ) {
        NavigationBarItem(
            selected = index == 0,
            label = {
                Text(
                    text = "Characters",
                    color = if (index == 0) Color.White else LocalContentColor.current
                )
            },
            onClick = { index = 0 },
            icon = { Icon(imageVector = Icons.Filled.Person, contentDescription = "") },
        )
        NavigationBarItem(
            selected = index == 1,
            label = {
                Text(
                    text = "Chapters",
                    color = if (index == 1) Color.White else LocalContentColor.current
                )
            },
            onClick = { index = 1 },
            icon = { Icon(imageVector = Icons.Filled.List, contentDescription = "") },
        )
        NavigationBarItem(
            selected = index == 2,
            label = {
                Text(
                    text = "Favorites",
                    color = if (index == 2) Color.White else LocalContentColor.current
                )
            },
            onClick = { index = 2 },
            icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = "") },
        )
    }
}