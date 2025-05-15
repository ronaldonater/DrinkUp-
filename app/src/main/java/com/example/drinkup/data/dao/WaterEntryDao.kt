package com.example.drinkup.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.drinkup.data.entities.WaterEntry
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface WaterEntryDao {
    @Insert
    suspend fun insertWaterEntry(waterEntry: WaterEntry): Long

    @Query("SELECT * FROM water_entries WHERE userId = :userId ORDER BY timestamp DESC")
    fun getAllEntriesForUser(userId: Int): Flow<List<WaterEntry>>

    @Query("SELECT SUM(volume) FROM water_entries WHERE userId = :userId AND date = :date")
    suspend fun getTotalVolumeForDate(userId: Int, date: LocalDate): Int?

    @Query("SELECT * FROM water_entries WHERE userId = :userId AND date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    suspend fun getEntriesForDateRange(userId: Int, startDate: LocalDate, endDate: LocalDate): List<WaterEntry>

    @Query("SELECT DISTINCT date FROM water_entries WHERE userId = :userId AND volume >= :minVolume ORDER BY date DESC")
    suspend fun getDatesWithMinVolume(userId: Int, minVolume: Int): List<LocalDate>
}