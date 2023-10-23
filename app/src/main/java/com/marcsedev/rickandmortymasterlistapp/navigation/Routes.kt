package com.marcsedev.rickandmortymasterlistapp.navigation

sealed class Routes(val route: String) {
    object SplashScreen : Routes("splash_screen")
    object MasterListScreen : Routes("master_list_screen")
    object CharacterDetailScreen : Routes("detail_character_screen/{id}") {
        fun createRoute(id: Int) = "CharacterDetailScreen/$id"
    }
}
