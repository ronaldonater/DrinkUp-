package com.example.drinkup.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val passwordHash: String,
    val dailyGoal: Int = 2000, // Default 2000ml
    val createdAt: LocalDateTime = LocalDateTime.now()
)

data class UserProfile(
    val id: Int,
    val username: String,
    val dailyGoal: Int,
    val createdAt: LocalDateTime
)

data class UserRegistration(
    val username: String,
    val password: String,
    val dailyGoal: Int = 2000
)

data class UserLogin(
    val username: String,
    val password: String
)

data class AuthResult(
    val success: Boolean,
    val userId: Int? = null,
    val username: String? = null,
    val errorMessage: String? = null
)