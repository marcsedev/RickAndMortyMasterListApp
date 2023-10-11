package com.marcsedev.rickandmortymasterlistapp.ui.navigation

sealed class AppScreens(val route: String) {
    object MasterListScreen: AppScreens("first_screen")
    object CharacterDetailScreen: AppScreens("second_screen")
}
