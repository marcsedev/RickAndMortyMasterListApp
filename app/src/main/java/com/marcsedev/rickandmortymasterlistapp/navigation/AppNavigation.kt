package com.marcsedev.rickandmortymasterlistapp.navigation

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

@Composable
fun AppNavigation(masterListViewModel: MasterListViewModel) {
    val navController = rememberNavController()
    val characterDetailViewModel: CharacterDetailViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.SplashScreen.route
    ) {
        composable(route = Routes.SplashScreen.route) { SplashScreen(navController) }
        composable(route = Routes.MasterListScreen.route) {
            MasterListScreen(
                masterListViewModel,
                navController
            )
        }
        composable(
            route = Routes.CharacterDetailScreen.route,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            CharacterDetailScreen(
                characterDetailViewModel,
                navController,
                backStackEntry.arguments?.getInt("id") ?: 0
            )
        }
    }
}