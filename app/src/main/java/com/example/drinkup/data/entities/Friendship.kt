package com.example.drinkup.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "friendships",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["friendId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("userId"), Index("friendId")]
)
data class Friendship(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val friendId: Int,
    val createdAt: LocalDateTime = LocalDateTime.now()
)

data class LeaderboardEntry(
    val userId: Int,
    val username: String,
    val todayIntake: Int,
    val percentage: Double,
    val rank: Int = 0
)