package com.marcsedev.rickandmortymasterlistapp.ui.list.masterList.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable

class MasterLListNavigation {
}
const val HOME_NAVIGATION_ROUTE = "home_route"

fun NavController.navigateToHome(builder: NavOptionsBuilder.() -> Unit) {
    this.navigate(HOME_NAVIGATION_ROUTE, builder)
}

fun NavGraphBuilder.homeRoute(navController: NavHostController) {
    composable(route = HOME_NAVIGATION_ROUTE) {
        MainRoute(
            it.savedStateHandle,
            mainNavController = navController,
            onNavigateToSelectDiocese = {
                navController.navigateToDiocese {
                    popUpTo(HOME_NAVIGATION_ROUTE) {
                        inclusive = true
                    }
                }
            },
            goToLogin = navController::navigateToLogin,
            goToSelectDiocese = navController::navigateToDiocese,
            goToTermsAndConditions = navController::navigateToTermsAndConditions,
            goToDocuments = navController::navigateToDocumentList,
            goToFAQS = navController::navigateToFaqs,
            navigateToMessages = {
                navController.navigateToAnonymousConversationDetail(
                    conversationId = it
                )
            },
            navigateToSignings = navController::navigateToSigning,
            navigateToEmployment = navController::navigateToEmployment
        )
    }
}