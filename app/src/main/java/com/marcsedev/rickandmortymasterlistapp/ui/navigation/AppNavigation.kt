package com.marcsedev.rickandmortymasterlistapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.marcsedev.rickandmortymasterlistapp.ui.list.detailList.CharacterDetailScreen
import com.marcsedev.rickandmortymasterlistapp.ui.list.detailList.CharacterDetailViewModel
import com.marcsedev.rickandmortymasterlistapp.ui.list.masterList.MasterListScreen
import com.marcsedev.rickandmortymasterlistapp.ui.list.masterList.MasterListViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val characterDetailViewModel: CharacterDetailViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = AppScreens.MasterListScreen.route
    ) {
        composable(route = AppScreens.MasterListScreen.route) {
            MasterListScreen(MasterListViewModel(), {}, navController)
        }
        composable(
            route = AppScreens.CharacterDetailScreen.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            id?.let {
                CharacterDetailScreen(characterDetailViewModel, navController, id)
            }
        }
    }
}