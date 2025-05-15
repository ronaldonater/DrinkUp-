package com.example.drinkup.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(
    tableName = "water_entries",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("userId")]
)
data class WaterEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val volume: Int, // (milliliters)
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val date: LocalDate = LocalDate.now()
)

data class DailyIntakeSummary(
    val date: LocalDate,
    val totalVolume: Int,
    val goalVolume: Int,
    val percentage: Double
)