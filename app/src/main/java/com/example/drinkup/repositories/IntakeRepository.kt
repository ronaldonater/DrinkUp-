package com.example.drinkup.repositories

import com.example.drinkup.data.dao.UserDao
import com.example.drinkup.data.dao.WaterEntryDao
import com.example.drinkup.data.entities.DailyIntakeSummary
import com.example.drinkup.data.entities.WaterEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class IntakeRepository(
    private val waterEntryDao: WaterEntryDao,
    private val userDao: UserDao
) {
    private var currentTestDate: LocalDate = LocalDate.now()

    fun setTestDate(date: LocalDate) {
        currentTestDate = date
    }

    fun getCurrentDate(): LocalDate {
        return currentTestDate
    }

    suspend fun addIntake(userId: Int, volume: Int, date: LocalDate = getCurrentDate()): Result<DailyIntakeSummary> {
        return withContext(Dispatchers.IO) {
            try {
                val user = userDao.getUserById(userId)
                    ?: return@withContext Result.failure(IllegalArgumentException("User not found"))

                val waterEntry = WaterEntry(
                    userId = userId,
                    volume = volume,
                    date = date
                )
                waterEntryDao.insertWaterEntry(waterEntry)

                val summary = getTodayIntake(userId)
                summary
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun removeIntake(userId: Int, volume: Int, date: LocalDate = getCurrentDate()): Result<DailyIntakeSummary> {
        return withContext(Dispatchers.IO) {
            try {
                val user = userDao.getUserById(userId)
                    ?: return@withContext Result.failure(IllegalArgumentException("User not found"))

                val currentTotal = waterEntryDao.getTotalVolumeForDate(userId, date) ?: 0

                val volumeToRemove = minOf(volume, currentTotal)

                if (volumeToRemove > 0) {
                    val waterEntry = WaterEntry(
                        userId = userId,
                        volume = -volumeToRemove,
                        date = date
                    )
                    waterEntryDao.insertWaterEntry(waterEntry)
                }

                val summary = getTodayIntake(userId)
                summary
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getTodayIntake(userId: Int): Result<DailyIntakeSummary> {
        return withContext(Dispatchers.IO) {
            try {
                val user = userDao.getUserById(userId)
                    ?: return@withContext Result.failure(IllegalArgumentException("User not found"))

                val today = getCurrentDate()
                val totalVolume = waterEntryDao.getTotalVolumeForDate(userId, today) ?: 0

                val summary = DailyIntakeSummary(
                    date = today,
                    totalVolume = totalVolume,
                    goalVolume = user.dailyGoal,
                    percentage = calculatePercentage(totalVolume, user.dailyGoal)
                )

                Result.success(summary)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getIntakeHistory(userId: Int, days: Int = 7): Result<List<DailyIntakeSummary>> {
        return withContext(Dispatchers.IO) {
            try {
                val user = userDao.getUserById(userId)
                    ?: return@withContext Result.failure(IllegalArgumentException("User not found"))

                val endDate = getCurrentDate()
                val startDate = endDate.minusDays(days.toLong() - 1)

                val entries = waterEntryDao.getEntriesForDateRange(userId, startDate, endDate)

                val entriesByDate = entries.groupBy { it.date }

                val summaries = (0 until days).map { i ->
                    val date = endDate.minusDays(i.toLong())
                    val dailyEntries = entriesByDate[date] ?: emptyList()
                    val totalVolume = dailyEntries.sumOf { it.volume }

                    DailyIntakeSummary(
                        date = date,
                        totalVolume = totalVolume,
                        goalVolume = user.dailyGoal,
                        percentage = calculatePercentage(totalVolume, user.dailyGoal)
                    )
                }.sortedBy { it.date }

                Result.success(summaries)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getCurrentStreak(userId: Int): Result<Int> {
        return withContext(Dispatchers.IO) {
            try {
                val user = userDao.getUserById(userId)
                    ?: return@withContext Result.failure(IllegalArgumentException("User not found"))

                val dailyGoal = user.dailyGoal
                val today = getCurrentDate()

                var currentDate = today
                var streak = 0
                var continuousStreak = true

                while (continuousStreak) {
                    val totalVolume = waterEntryDao.getTotalVolumeForDate(userId, currentDate) ?: 0

                    if (totalVolume >= dailyGoal) {
                        streak++
                        currentDate = currentDate.minusDays(1)
                    } else {

                        if (currentDate.isEqual(today) && streak == 0) {
                            currentDate = currentDate.minusDays(1)
                        } else {

                            continuousStreak = false
                        }
                    }


                    if (streak > 365 || currentDate.isBefore(today.minusDays(365))) {
                        continuousStreak = false
                    }
                }

                Result.success(streak)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    private fun calculatePercentage(volume: Int, goal: Int): Double {
        return (volume.toDouble() / goal.toDouble() * 100)
    }
}
