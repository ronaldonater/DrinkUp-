package com.example.drinkup.ui.screens

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.drinkup.di.AppModule
import com.example.drinkup.navigation.Screen
import com.example.drinkup.ui.components.DrinkUpBottomNavBar
import com.example.drinkup.viewmodels.IntakeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    intakeViewModel: IntakeViewModel,
    onNavigate: (String) -> Unit
) {
    val context = LocalContext.current
    val preferencesManager = AppModule.providePreferencesManager()
    val userId = preferencesManager.getUserId()
    val intakeRepository = AppModule.provideIntakeRepository()

    BackHandler {
        if (context is Activity) {
            context.finish()
        }
    }

    var selectedAmount by remember { mutableStateOf(0) }
    var isAddingWater by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isRemovingWater by remember { mutableStateOf(false) }


    val currentDate = intakeRepository.getCurrentDate()

    val todayIntakeResult by intakeViewModel.todayIntake.observeAsState()


    LaunchedEffect(currentDate) {
        intakeViewModel.loadTodayIntake(userId)
    }


    val addIntakeResult by intakeViewModel.addIntakeResult.observeAsState()
    LaunchedEffect(addIntakeResult) {
        addIntakeResult?.let { result ->
            isAddingWater = false
            isRemovingWater = false
            result.fold(
                onSuccess = {
                    selectedAmount = 0
                },
                onFailure = {
                    errorMessage = it.message ?: "Failed to update water intake"
                }
            )
        }
    }


    val todayIntake = todayIntakeResult?.getOrNull()
    val currentProgress = todayIntake?.percentage?.toFloat()?.div(100) ?: 0f
    val currentAmount = todayIntake?.totalVolume?.toFloat()?.div(1000) ?: 0f // Convert to liters
    val dailyGoal = todayIntake?.goalVolume?.toFloat()?.div(1000) ?: 2.0f // Convert to liters


    val percentageText = "${todayIntake?.percentage?.toInt() ?: 0}%"

    val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")
    val formattedDate = currentDate.format(formatter)
    val dayOfWeek = currentDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())


    fun advanceToNextDay() {
        CoroutineScope(Dispatchers.Main).launch {
            try {

                val nextDay = currentDate.plusDays(1)
                intakeRepository.setTestDate(nextDay)


                intakeViewModel.loadTodayIntake(userId)
                intakeViewModel.loadIntakeHistory(userId, 30)
                intakeViewModel.loadCurrentStreak(userId)


            } catch (e: Exception) {
                errorMessage = "Failed to advance day: ${e.message}"
            }
        }
    }

    // Function to go back to the previous day (only here for testing purposes)
    fun goBackOneDay() {
        CoroutineScope(Dispatchers.Main).launch {
            try {

                val previousDay = currentDate.minusDays(1)
                intakeRepository.setTestDate(previousDay)


                intakeViewModel.loadTodayIntake(userId)
                intakeViewModel.loadIntakeHistory(userId, 30)
                intakeViewModel.loadCurrentStreak(userId)


            } catch (e: Exception) {
                errorMessage = "Failed to go back a day: ${e.message}"
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") }
            )
        },
        bottomBar = {
            DrinkUpBottomNavBar(
                currentRoute = Screen.Home.route,
                onNavigate = onNavigate
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {

                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "User Profile",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = formattedDate,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = dayOfWeek,
                fontSize = 16.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))


            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(200.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = percentageText,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,

                            color = when {
                                (todayIntake?.percentage ?: 0.0) >= 100.0 -> Color.Green
                                (todayIntake?.percentage ?: 0.0) >= 75.0 -> Color(0xFFFFFF66)
                                (todayIntake?.percentage ?: 0.0) >= 50.0 -> Color(0xFFFFA500)
                                else -> Color.Red
                            }
                        )

                        Text(
                            text = "$currentAmount / ${dailyGoal}L",
                            fontSize = 16.sp,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(16.dp))


                        LinearProgressIndicator(
                            progress = currentProgress.coerceIn(0f, 1f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(16.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            color = when {
                                (todayIntake?.percentage ?: 0.0) >= 100.0 -> Color.Green
                                (todayIntake?.percentage ?: 0.0) >= 75.0 -> Color(0xFFFFFF66)
                                (todayIntake?.percentage ?: 0.0) >= 50.0 -> Color(0xFFFFA500)
                                else -> Color.Red
                            },
                            trackColor = Color.LightGray.copy(alpha = 0.3f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Button(
                    onClick = {
                        if (selectedAmount > 0) {
                            isAddingWater = true
                            errorMessage = null
                            intakeViewModel.addIntake(userId, selectedAmount)
                        } else {
                            errorMessage = "Please select an amount to add"
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    ),
                    enabled = !isAddingWater && !isRemovingWater && selectedAmount > 0
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.WaterDrop,
                            contentDescription = "Add Water"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Add Water", fontSize = 16.sp)
                    }
                }


                Button(
                    onClick = {
                        if (selectedAmount > 0) {
                            isRemovingWater = true
                            errorMessage = null
                            intakeViewModel.removeIntake(userId, selectedAmount)
                        } else {
                            errorMessage = "Please select an amount to remove"
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = Color.White
                    ),
                    enabled = !isRemovingWater && !isAddingWater && selectedAmount > 0
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.WaterDrop,
                            contentDescription = "Remove Water"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Remove Water", fontSize = 16.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                WaterAmountOption(
                    amount = 250,
                    isSelected = selectedAmount == 250,
                    onClick = { selectedAmount = 250 },
                    modifier = Modifier.weight(1f)
                )

                WaterAmountOption(
                    amount = 500,
                    isSelected = selectedAmount == 500,
                    onClick = { selectedAmount = 500 },
                    modifier = Modifier.weight(1f)
                )

                WaterAmountOption(
                    amount = 750,
                    isSelected = selectedAmount == 750,
                    onClick = { selectedAmount = 750 },
                    modifier = Modifier.weight(1f)
                )

                WaterAmountOption(
                    amount = 1000,
                    isSelected = selectedAmount == 1000,
                    onClick = { selectedAmount = 1000 },
                    modifier = Modifier.weight(1f)
                )
            }


            errorMessage?.let { message ->
                Snackbar(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(message)
                }
            }

            Spacer(modifier = Modifier.weight(1f))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { goBackOneDay() },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Previous Day"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Previous Day",
                            fontSize = 16.sp
                        )
                    }
                }

                Button(
                    onClick = { advanceToNextDay() },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Next Day",
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Next Day"
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaterAmountOption(
    amount: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .height(100.dp)
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.WaterDrop,
                contentDescription = "$amount ml",
                tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${amount}ml",
                fontSize = 16.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
            )
        }
    }
}