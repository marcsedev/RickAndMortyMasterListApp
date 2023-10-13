package com.marcsedev.rickandmortymasterlistapp.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object MasterListScreen: AppScreens("master_list_screen")
    object CharacterDetailScreen: AppScreens("detail_character_screen")
}
