package com.example.drinkup.ui.screens

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.drinkup.data.entities.DailyIntakeSummary
import com.example.drinkup.di.AppModule
import com.example.drinkup.navigation.Screen
import com.example.drinkup.ui.components.DrinkUpBottomNavBar
import com.example.drinkup.viewmodels.IntakeViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    navController: NavController,
    intakeViewModel: IntakeViewModel,
    onNavigate: (String) -> Unit
) {
    val context = LocalContext.current
    val preferencesManager = AppModule.providePreferencesManager()
    val userId = preferencesManager.getUserId()
    val intakeRepository = AppModule.provideIntakeRepository()
    val scrollState = rememberScrollState()

    BackHandler {
        if (!navController.popBackStack()) {
            if (context is Activity) {
                context.finish()
            }
        }
    }


    val currentDate = intakeRepository.getCurrentDate()
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }


    var weekOffset by remember { mutableStateOf(0) }

    val intakeHistoryResult by intakeViewModel.intakeHistory.observeAsState()
    val currentStreakResult by intakeViewModel.currentStreak.observeAsState()


    LaunchedEffect(Unit) {
        isLoading = true
        errorMessage = null
        intakeViewModel.loadIntakeHistory(userId, 60)
        intakeViewModel.loadCurrentStreak(userId)
    }


    LaunchedEffect(currentDate) {
        isLoading = true
        intakeViewModel.loadIntakeHistory(userId, 60)
        intakeViewModel.loadCurrentStreak(userId)
    }


    LaunchedEffect(intakeHistoryResult) {
        intakeHistoryResult?.let {
            isLoading = false
        }
    }


    val intakeHistory = intakeHistoryResult?.getOrNull() ?: emptyList()
    val currentStreak = currentStreakResult?.getOrNull() ?: 0


    val daysWithData = intakeHistory.filter { it.totalVolume > 0 }
    val dailyAverage = if (daysWithData.isNotEmpty()) {
        daysWithData.sumOf { it.totalVolume }.toFloat() / daysWithData.size / 1000 // Convert to liters
    } else {
        0f
    }


    val weekData by remember(intakeHistory, currentDate, weekOffset) {
        derivedStateOf {
            getWeekData(intakeHistory, currentDate, weekOffset)
        }
    }


    val weekStartDate = currentDate.minusDays((weekOffset * 7 + 6).toLong())
    val weekEndDate = currentDate.minusDays(weekOffset * 7.toLong())
    val weekDateRange = "${weekStartDate.format(DateTimeFormatter.ofPattern("MMM d"))} - ${weekEndDate.format(DateTimeFormatter.ofPattern("MMM d"))}"


    val middleOfWeek = weekStartDate.plusDays(3)
    val currentMonthDisplay = middleOfWeek.format(DateTimeFormatter.ofPattern("MMMM yyyy"))

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("History") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
        bottomBar = {
            DrinkUpBottomNavBar(
                currentRoute = Screen.History.route,
                onNavigate = onNavigate
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
        ) {

            Text(
                text = currentMonthDisplay,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Loading...",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            } else {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Daily Average",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )

                            Text(
                                text = "${String.format("%.1f", dailyAverage)}L",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }


                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Current Streak",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )

                            Text(
                                text = "$currentStreak days",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))


                Text(
                    text = "Water Intake Graph",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                ) {
                    val isDark = isSystemInDarkTheme()
                    val graphColor = if (isDark) Color.White else Color.Gray

                    if (weekData.isNotEmpty()) {
                       //makes the line graph
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val width = size.width
                            val height = size.height

                            val maxIntake = weekData.maxOfOrNull { it.totalVolume } ?: 1
                            if (maxIntake > 0) {
                                for (i in 0 until weekData.size - 1) {
                                    val startX = width * i / (weekData.size - 1)
                                    val startY = height * (1 - weekData[i].totalVolume.toFloat() / maxIntake)
                                    val endX = width * (i + 1) / (weekData.size - 1)
                                    val endY = height * (1 - weekData[i + 1].totalVolume.toFloat() / maxIntake)

                                    drawLine(
                                        color = graphColor,
                                        start = Offset(startX, startY),
                                        end = Offset(endX, endY),
                                        strokeWidth = 5f
                                    )
                                }
                            }
                        }
                    } else {
                        //else it makes a placeholder
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val width = size.width
                            val height = size.height
                            val points = listOf(0.7f, 0.5f, 0.8f, 0.6f, 0.9f, 0.7f, 0.8f)

                            for (i in 0 until points.size - 1) {
                                val startX = width * i / (points.size - 1)
                                val startY = height * (1 - points[i])
                                val endX = width * (i + 1) / (points.size - 1)
                                val endY = height * (1 - points[i + 1])

                                drawLine(
                                    color = graphColor,
                                    start = Offset(startX, startY),
                                    end = Offset(endX, endY),
                                    strokeWidth = 5f
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Weekly Progress",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = weekDateRange,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))


                if (weekData.isNotEmpty()) {

                    weekData.forEach { day ->
                        val isToday = day.date.isEqual(currentDate)
                        val isYesterday = day.date.isEqual(currentDate.minusDays(1))

                        val dateFormatter = DateTimeFormatter.ofPattern("EEE, MMM d")
                        val dayLabel = day.date.format(dateFormatter) + when {
                            isToday -> " (Today)"
                            isYesterday -> " (Yesterday)"
                            else -> ""
                        }

                        CompactDailyProgressItem(
                            day = dayLabel,
                            current = day.totalVolume / 1000f,
                            goal = day.goalVolume / 1000f,
                            progress = day.percentage.toFloat() / 100f
                        )

                        if (weekData.indexOf(day) < weekData.size - 1) {
                            Divider(modifier = Modifier.padding(vertical = 8.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {

                                weekOffset += 1
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowLeft,
                                contentDescription = "Previous Week"
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = if (weekOffset == 0) "Current Week" else "Week -$weekOffset",
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        IconButton(
                            onClick = {

                                if (weekOffset > 0) weekOffset -= 1
                            },
                            enabled = weekOffset > 0
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowRight,
                                contentDescription = "Next Week",
                                tint = if (weekOffset > 0) MaterialTheme.colorScheme.primary else Color.Gray
                            )
                        }
                    }
                } else {
                    Text(
                        text = "No data available for this week",
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        textAlign = TextAlign.Center
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


                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


fun getWeekData(
    intakeHistory: List<DailyIntakeSummary>,
    currentDate: LocalDate,
    weekOffset: Int
): List<DailyIntakeSummary> {
    val result = mutableListOf<DailyIntakeSummary>()


    val historyByDate = intakeHistory.associateBy { it.date }


    val weekStartOffset = weekOffset * 7 + 6
    val weekEndOffset = weekOffset * 7


    for (i in weekStartOffset downTo weekEndOffset) {
        val date = currentDate.minusDays(i.toLong())
        val entry = historyByDate[date]

        if (entry != null) {

            result.add(entry)
        } else {

            result.add(
                DailyIntakeSummary(
                    date = date,
                    totalVolume = 0,
                    goalVolume = intakeHistory.firstOrNull()?.goalVolume ?: 2000,
                    percentage = 0.0
                )
            )
        }
    }


    return result.sortedByDescending { it.date }
}


@Composable
fun CompactDailyProgressItem(
    day: String,
    current: Float,
    goal: Float,
    progress: Float
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = day,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "${String.format("%.1f", current)}L / ${String.format("%.1f", goal)}L",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        LinearProgressIndicator(
            progress = progress.coerceIn(0f, 1f),
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
            trackColor = Color.LightGray.copy(alpha = 0.3f)
        )
    }
}