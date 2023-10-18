package com.marcsedev.rickandmortymasterlistapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.marcsedev.rickandmortymasterlistapp.ui.screens.detailList.CharacterDetailScreen
import com.marcsedev.rickandmortymasterlistapp.ui.screens.detailList.CharacterDetailViewModel
import com.marcsedev.rickandmortymasterlistapp.ui.screens.masterList.MasterListScreen
import com.marcsedev.rickandmortymasterlistapp.ui.screens.masterList.MasterListViewModel
import com.marcsedev.rickandmortymasterlistapp.ui.screens.splashScreen.SplashScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val characterDetailViewModel: CharacterDetailViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ) {
        composable(route = AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
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