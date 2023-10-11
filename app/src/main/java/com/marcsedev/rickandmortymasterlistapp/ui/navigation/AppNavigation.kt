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


/*

    val navController = rememberNavController()

    navController.popBackStack(HOME_NAVIGATION_ROUTE, false)

    NavHost(
        navController = navController,
        startDestination = HOME_NAVIGATION_ROUTE
    ) {
        homeRoute(navController)
        dioceseGraph(navController, navigateToHome = {
            // If there is a 'home' route in the backstack (user is changing the diocese),
            // close the diocese selection. Otherwise (it is the first diocese selection from
            // the user, the first time app is opened), navigate to home.
            if (navController.backQueue.any { it.destination.route == HOME_NAVIGATION_ROUTE }) {
                navController.popBackStack(DIOCESE_ROUTE, inclusive = true)
            } else {
                navController.navigateToHome {
                    popUpTo(DIOCESE_ROUTE) {
                        inclusive = true
                    }
                }
            }
        })
        loginGraph(navController, onLoginDone = {
            navController.popBackStack(LOGIN_NAVIGATION_ROUTE, inclusive = true)
        })

        editAddressScreen(navController)
        moreGraph(navController)
        appointmentsGraph(goToWriteMessage = {
            navController.navigateToConversationDetail(
                conversationId = it,
                groupConversation = false,
                conversationClosed = false
            )
        }, navController)
        documentsGraph(navController, onViewImageClick = { imagePath ->
            navController.navigateToImageViewer(imagePath)
        })
        imageViewer(navController)
        messagesGraph(navController, onViewImageClick = { imagePath ->
            navController.navigateToImageViewer(imagePath)
        })
        signingGraph(
            navController = navController,
        )
        faqsGraph(navController = navController)
        employmentGraph(navController = navController)
    }
}
*/