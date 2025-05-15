package com.example.drinkup.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.drinkup.R
import com.example.drinkup.navigation.Screen

data class BottomNavItem(
    val route: String,
    val icon: @Composable () -> Unit,
    val label: String
)

@Composable
fun DrinkUpBottomNavBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    val navItems = listOf(
        BottomNavItem(
            route = Screen.Home.route,
            icon = { Icon(Icons.Default.Home, contentDescription = stringResource(R.string.home)) },
            label = stringResource(R.string.home)
        ),
        BottomNavItem(
            route = Screen.History.route,
            icon = { Icon(Icons.Default.ShowChart, contentDescription = stringResource(R.string.history)) },
            label = stringResource(R.string.history)
        ),
        BottomNavItem(
            route = Screen.Leaderboard.route,
            icon = { Icon(Icons.Default.Leaderboard, contentDescription = stringResource(R.string.leaderboard)) },
            label = stringResource(R.string.leaderboard)
        ),
        BottomNavItem(
            route = Screen.Settings.route,
            icon = { Icon(Icons.Default.Settings, contentDescription = stringResource(R.string.settings)) },
            label = stringResource(R.string.settings)
        )
    )

    NavigationBar {
        navItems.forEach { item ->
            NavigationBarItem(
                icon = { item.icon() },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    try {
                        onNavigate(item.route)
                    } catch (e: Exception) {
                    }
                }
            )
        }
    }
}