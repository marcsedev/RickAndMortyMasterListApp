package com.marcsedev.rickandmortymasterlistapp.ui.navigation

sealed class AppScreens(val route: String) {
    object MasterListScreen: AppScreens("master_list_screen")
    object CharacterDetailScreen: AppScreens("detail_character_screen")
}
