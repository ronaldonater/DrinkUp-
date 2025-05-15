package com.example.drinkup.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.drinkup.data.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User): Long

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Int): User?

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): User?

    @Query("UPDATE users SET dailyGoal = :dailyGoal WHERE id = :userId")
    suspend fun updateDailyGoal(userId: Int, dailyGoal: Int)

    @Query("SELECT * FROM users WHERE id != :userId")
    suspend fun getAllOtherUsers(userId: Int): List<User>
}
