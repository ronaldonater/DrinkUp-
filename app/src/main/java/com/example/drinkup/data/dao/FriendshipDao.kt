package com.example.drinkup.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.drinkup.data.entities.Friendship
import com.example.drinkup.data.entities.User

@Dao
interface FriendshipDao {
    @Insert
    suspend fun insertFriendship(friendship: Friendship): Long

    @Query("SELECT * FROM friendships WHERE (userId = :userId AND friendId = :friendId) OR (userId = :friendId AND friendId = :userId) LIMIT 1")
    suspend fun getFriendship(userId: Int, friendId: Int): Friendship?

    @Query("DELETE FROM friendships WHERE (userId = :userId AND friendId = :friendId) OR (userId = :friendId AND friendId = :userId)")
    suspend fun deleteFriendship(userId: Int, friendId: Int): Int

    @Transaction
    @Query("SELECT u.* FROM users u INNER JOIN friendships f ON (u.id = f.friendId AND f.userId = :userId) OR (u.id = f.userId AND f.friendId = :userId)")
    suspend fun getFriendsForUser(userId: Int): List<User>
}