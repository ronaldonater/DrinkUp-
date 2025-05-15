package com.example.drinkup.repositories

import com.example.drinkup.data.dao.FriendshipDao
import com.example.drinkup.data.dao.UserDao
import com.example.drinkup.data.dao.WaterEntryDao
import com.example.drinkup.data.entities.Friendship
import com.example.drinkup.data.entities.LeaderboardEntry
import com.example.drinkup.data.entities.UserProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class FriendRepository(
    private val friendshipDao: FriendshipDao,
    private val userDao: UserDao,
    private val waterEntryDao: WaterEntryDao
) {



    suspend fun addFriend(userId: Int, friendUsername: String): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val friend = userDao.getUserByUsername(friendUsername)
                    ?: return@withContext Result.failure(IllegalArgumentException("User not found"))

                val existingFriendship = friendshipDao.getFriendship(userId, friend.id)
                if (existingFriendship != null || userId == friend.id) {
                    return@withContext Result.success(false)
                }

                val friendship = Friendship(
                    userId = userId,
                    friendId = friend.id
                )
                friendshipDao.insertFriendship(friendship)

                Result.success(true)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun removeFriend(userId: Int, friendId: Int): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val rowsDeleted = friendshipDao.deleteFriendship(userId, friendId)
                Result.success(rowsDeleted > 0)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getFriends(userId: Int): Result<List<UserProfile>> {
        return withContext(Dispatchers.IO) {
            try {
                val friends = friendshipDao.getFriendsForUser(userId)

                val friendProfiles = friends.map { user ->
                    UserProfile(
                        id = user.id,
                        username = user.username,
                        dailyGoal = user.dailyGoal,
                        createdAt = user.createdAt
                    )
                }

                Result.success(friendProfiles)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getLeaderboard(userId: Int): Result<List<LeaderboardEntry>> {
        return withContext(Dispatchers.IO) {
            try {
                val user = userDao.getUserById(userId)
                    ?: return@withContext Result.failure(IllegalArgumentException("User not found"))

                val friends = friendshipDao.getFriendsForUser(userId)

                val allUsers = friends + user

                val today = LocalDate.now()

                val leaderboardEntries = allUsers.map { u ->
                    val totalVolume = waterEntryDao.getTotalVolumeForDate(u.id, today) ?: 0

                    LeaderboardEntry(
                        userId = u.id,
                        username = u.username,
                        todayIntake = totalVolume,
                        percentage = calculatePercentage(totalVolume, u.dailyGoal)
                    )
                }

                val sortedEntries = leaderboardEntries
                    .sortedByDescending { it.todayIntake }
                    .mapIndexed { index, entry ->
                        entry.copy(rank = index + 1)
                    }

                Result.success(sortedEntries)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }


    private fun calculatePercentage(volume: Int, goal: Int): Double {
        return (volume.toDouble() / goal.toDouble() * 100).coerceIn(0.0, 100.0)
    }
}