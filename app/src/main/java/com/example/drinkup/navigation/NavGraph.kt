package com.example.drinkup.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.drinkup.di.AppModule
import com.example.drinkup.ui.ThemeViewModel
import com.example.drinkup.ui.screens.HistoryScreen
import com.example.drinkup.ui.screens.HomeScreen
import com.example.drinkup.ui.screens.LeaderboardScreen
import com.example.drinkup.ui.screens.LoginScreen
import com.example.drinkup.ui.screens.SettingsScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object History : Screen("history")
    object Leaderboard : Screen("leaderboard")
    object Settings : Screen("settings")
}

@Composable
fun DrinkUpNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Login.route
) {
    val authViewModel = remember { AppModule.provideAuthViewModel() }
    val intakeViewModel = remember { AppModule.provideIntakeViewModel() }
    val friendViewModel = remember { AppModule.provideFriendViewModel() }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Login.route) {
            val themeViewModel: ThemeViewModel = viewModel()

            LoginScreen(
                authViewModel = authViewModel,
                themeViewModel = themeViewModel,
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                intakeViewModel = intakeViewModel,
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable(Screen.History.route) {
            HistoryScreen(
                navController = navController,
                intakeViewModel = intakeViewModel,
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable(Screen.Leaderboard.route) {
            LeaderboardScreen(
                navController = navController,
                friendViewModel = friendViewModel,
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                navController = navController,
                authViewModel = authViewModel,
                onNavigate = { route -> navController.navigate(route) },
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                        restoreState = false
                    }
                }
            )
        }
    }
}