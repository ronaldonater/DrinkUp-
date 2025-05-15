package com.example.drinkup.ui.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Water
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.drinkup.R
import com.example.drinkup.ui.ThemeViewModel
import com.example.drinkup.viewmodels.AuthViewModel

private const val TAG = "LoginScreen"

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    themeViewModel: ThemeViewModel,
    onNavigateToHome: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLogin by remember { mutableStateOf(true) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val loginResult by authViewModel.loginResult.observeAsState()
    val registerResult by authViewModel.registerResult.observeAsState()
    val isDarkMode by themeViewModel.isDarkMode.collectAsStateWithLifecycle()


    LaunchedEffect(loginResult) {
        loginResult?.let { result ->
            isLoading = false
            result.fold(
                onSuccess = { authResult ->
                    if (authResult.success) {
                        Log.d(TAG, "Login successful, navigating to home")
                        onNavigateToHome()
                    } else {
                        Log.d(TAG, "Login failed: ${authResult.errorMessage}")
                        errorMessage = authResult.errorMessage ?: "Login failed"
                    }
                },
                onFailure = {
                    Log.e(TAG, "Login error", it)
                    errorMessage = it.message ?: "Login failed: ${it.javaClass.simpleName}"
                }
            )
        }
    }


    LaunchedEffect(registerResult) {
        registerResult?.let { result ->
            isLoading = false
            result.fold(
                onSuccess = { authResult ->
                    if (authResult.success) {
                        Log.d(TAG, "Registration successful, navigating to home")
                        onNavigateToHome()
                    } else {
                        Log.d(TAG, "Registration failed: ${authResult.errorMessage}")
                        errorMessage = authResult.errorMessage ?: "Registration failed"
                    }
                },
                onFailure = {
                    Log.e(TAG, "Registration error", it)
                    errorMessage = it.message ?: "Registration failed: ${it.javaClass.simpleName}"
                }
            )
        }
    }

    val isDark = isSystemInDarkTheme()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            imageVector = Icons.Default.Water,
            contentDescription = "DrinkUp Logo",
            modifier = Modifier.size(64.dp),
            colorFilter = ColorFilter.tint(if (isDarkMode) Color.White else Color.Black)
        )

        Text(
            text = "DrinkUp!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))


        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        val isPasswordValid = password.length >= 6
        val isFormValid = username.isNotBlank() && isPasswordValid

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                try {
                    Log.d(TAG, "Button clicked: ${if (isLogin) "Login" else "Register"}")
                    isLoading = true
                    errorMessage = null


                    if (username.isBlank()) {
                        isLoading = false
                        errorMessage = "Username cannot be empty"
                        return@Button
                    }

                    if (password.length < 6) {
                        isLoading = false
                        errorMessage = "Password must be at least 6 characters"
                        return@Button
                    }

                    if (isLogin) {
                        authViewModel.login(username, password)
                    } else {
                        authViewModel.register(username, password)
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error during ${if (isLogin) "login" else "registration"}", e)
                    isLoading = false
                    errorMessage = "Error: ${e.message}"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            enabled = !isLoading && isFormValid
        ) {
            if (isLoading) {
                Text(
                    text = "Loading...",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            } else {
                Text(
                    text = if (isLogin) stringResource(R.string.login) else stringResource(R.string.signup),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (!isLogin && password.isNotEmpty() && !isPasswordValid) {
            Text(
                text = "Password must be at least 6 characters",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }


        Button(
            onClick = {
                Log.d(TAG, "Toggling between login and register")
                isLogin = !isLogin
                errorMessage = null
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp
            ),
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Text(
                text = if (isLogin) "Need an account? Sign Up" else "Already have an account? Log In",
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }


        errorMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Snackbar(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = it)
            }
        }
    }
}