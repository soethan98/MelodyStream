package com.soethan.melodystream.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.soethan.melodystream.presentation.components.PlayerScreen
import com.soethan.melodystream.presentation.screens.MainScreen

@Composable
fun AppMainNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainScreenRoute,
        modifier = Modifier.padding()

    ) {
        composable<MainScreenRoute> {
            MainScreen(navController)
        }
        composable<PlayerScreenRoute> {
            val args = it.toRoute<PlayerScreenRoute>()
            PlayerScreen(uri = args.uri)
        }
    }
}