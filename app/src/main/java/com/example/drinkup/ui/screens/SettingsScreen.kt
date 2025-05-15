package com.example.drinkup.ui.screens

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.drinkup.di.AppModule
import com.example.drinkup.navigation.Screen
import com.example.drinkup.ui.ThemeViewModel
import com.example.drinkup.ui.components.DrinkUpBottomNavBar
import com.example.drinkup.viewmodels.AuthViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    onNavigate: (String) -> Unit,
    onLogout: () -> Unit
) {
    val context = LocalContext.current


    BackHandler {
        if (!navController.popBackStack()) {
            if (context is Activity) {
                context.finish()
            }
        }
    }

    val activity = LocalContext.current as ComponentActivity
    val themeViewModel: ThemeViewModel = viewModel(viewModelStoreOwner = activity)
    val isDarkMode by themeViewModel.isDarkMode.collectAsStateWithLifecycle()
    var dailyGoal by remember { mutableStateOf(2.0f) }
    val preferencesManager = AppModule.providePreferencesManager()
    val userId = preferencesManager.getUserId()
    var notificationsEnabled by remember { mutableStateOf(preferencesManager.isNotificationsEnabled()) }
    var isUpdatingGoal by remember { mutableStateOf(false) }
    var goalUpdateMessage by remember { mutableStateOf<String?>(null) }


    LaunchedEffect(Unit) {
        try {
            val userProfile = authViewModel.getUserProfile(userId)
            userProfile.fold(
                onSuccess = { profile ->
                    dailyGoal = (profile.dailyGoal / 1000f).coerceIn(1.0f, 4.0f) // Convert from ml to L
                },
                onFailure = {
                    goalUpdateMessage = "Failed to load daily goal"
                }
            )
        } catch (e: Exception) {
            goalUpdateMessage = "Error: ${e.message}"
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            DrinkUpBottomNavBar(
                currentRoute = Screen.Settings.route,
                onNavigate = onNavigate
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Dark Mode",
                    modifier = Modifier.weight(1f),
                    fontSize = 16.sp
                )
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { themeViewModel.toggleTheme() }
                )
            }

            Divider()


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Daily Water Goal",
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Slider(
                        value = dailyGoal,
                        onValueChange = { newGoal -> dailyGoal = newGoal },
                        valueRange = 1.0f..4.0f,
                        steps = 5,
                        modifier = Modifier.weight(1f),
                        colors = if (isDarkMode) {
                            SliderDefaults.colors(
                                thumbColor = Color.White,
                                activeTrackColor = Color.White,
                                inactiveTrackColor = Color.Black
                            )
                        } else {
                            SliderDefaults.colors(
                                thumbColor = MaterialTheme.colorScheme.primary,
                                activeTrackColor = MaterialTheme.colorScheme.primary,
                                inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = String.format("%.1fL", dailyGoal),
                        color = if (isDarkMode) Color.White else MaterialTheme.colorScheme.onSurface
                    )
                }


                Button(
                    onClick = {
                        isUpdatingGoal = true
                        goalUpdateMessage = null
                        val goalInMl = (dailyGoal * 1000).toInt() // Converts from L to ml

                        activity.lifecycleScope.launch {
                            try {
                                val result = authViewModel.updateDailyGoal(userId, goalInMl)
                                result.fold(
                                    onSuccess = { success ->
                                        if (success) {
                                            goalUpdateMessage = "Daily goal updated successfully"
                                        } else {
                                            goalUpdateMessage = "Failed to update daily goal"
                                        }
                                    },
                                    onFailure = { error ->
                                        goalUpdateMessage = "Error: ${error.message}"
                                    }
                                )
                                isUpdatingGoal = false
                            } catch (e: Exception) {
                                goalUpdateMessage = "Error: ${e.message}"
                                isUpdatingGoal = false
                            }
                        }
                    },
                    enabled = !isUpdatingGoal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    if (isUpdatingGoal) {
                        Text("Updating...")
                    } else {
                        Text("Save Daily Goal")
                    }
                }


                goalUpdateMessage?.let {
                    Text(
                        text = it,
                        color = if (it.startsWith("Error") || it.startsWith("Failed"))
                            MaterialTheme.colorScheme.error
                        else if (isDarkMode)
                            Color.White
                        else
                            MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }

            Divider()


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Enable Notifications",
                    modifier = Modifier.weight(1f),
                    fontSize = 16.sp
                )
                Switch(
                    checked = notificationsEnabled,
                    onCheckedChange = {
                        notificationsEnabled = it
                        preferencesManager.setNotificationsEnabled(it)
                    }
                )
            }

            Spacer(modifier = Modifier.weight(1f))


            Button(
                onClick = {
                    authViewModel.logout()
                    onLogout()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.ExitToApp, contentDescription = "Logout")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Logout")
            }

        }
    }
}
