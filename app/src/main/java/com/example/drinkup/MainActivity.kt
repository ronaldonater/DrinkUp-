package com.example.drinkup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.drinkup.di.AppModule
import com.example.drinkup.navigation.DrinkUpNavHost
import com.example.drinkup.navigation.Screen
import com.example.drinkup.ui.theme.DrinkUpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppModule.initialize(this)
        val preferencesManager = AppModule.providePreferencesManager()

        setContent {
            val themeViewModel: com.example.drinkup.ui.ThemeViewModel = viewModel()
            val isDarkMode by themeViewModel.isDarkMode.collectAsStateWithLifecycle()


            DrinkUpTheme(darkTheme = isDarkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val startDestination = if (preferencesManager.isLoggedIn()) {
                        Screen.Home.route
                    } else {
                        Screen.Login.route
                    }

                    DrinkUpNavHost(startDestination = startDestination)
                }
            }
        }
    }
}