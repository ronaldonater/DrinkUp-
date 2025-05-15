package com.example.drinkup.repositories

import android.util.Log
import com.example.drinkup.data.dao.UserDao
import com.example.drinkup.data.entities.AuthResult
import com.example.drinkup.data.entities.User
import com.example.drinkup.data.entities.UserProfile
import com.example.drinkup.di.AppModule
import com.example.drinkup.utils.PasswordUtils
import com.example.drinkup.utils.PreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(
    private val userDao: UserDao,
    private val preferencesManager: PreferenceManager
) {
    private val TAG = "AuthRepository"

    suspend fun register(username: String, password: String, dailyGoal: Int = 2000): Result<AuthResult> {
        return withContext(Dispatchers.IO) {
            try {
                Log.d(TAG, "Registering user: $username")

                // Checks if username already exists (ALL UNIQUE)
                val existingUser = userDao.getUserByUsername(username)
                if (existingUser != null) {
                    Log.d(TAG, "Username already exists: $username")
                    return@withContext Result.failure(IllegalArgumentException("Username already exists"))
                }

                // Password hashing
                val passwordHash: String
                try {
                    passwordHash = PasswordUtils.hashPassword(password)
                } catch (e: Exception) {
                    Log.e(TAG, "Error hashing password", e)
                    return@withContext Result.failure(Exception("Error processing password: ${e.message}"))
                }
                val user = User(
                    username = username,
                    passwordHash = passwordHash,
                    dailyGoal = dailyGoal
                )


                var userId: Int
                try {
                    val insertedId = userDao.insertUser(user)
                    userId = insertedId.toInt()
                    Log.d(TAG, "User registered successfully: ID=$userId, Username=$username")
                } catch (e: Exception) {
                    Log.e(TAG, "Error inserting user into database", e)
                    return@withContext Result.failure(Exception("Error creating user account: ${e.message}"))
                }


                try {
                    preferencesManager.saveUserInfo(userId, username)
                } catch (e: Exception) {
                    Log.e(TAG, "Error saving user info to preferences", e)
                }

                Result.success(AuthResult(
                    success = true,
                    userId = userId,
                    username = username
                ))
            } catch (e: Exception) {
                Log.e(TAG, "Error registering user", e)
                Result.failure(e)
            }
        }
    }

    suspend fun login(username: String, password: String): Result<AuthResult> {
        return withContext(Dispatchers.IO) {
            try {
                Log.d(TAG, "Logging in user: $username")


                val user = userDao.getUserByUsername(username)
                if (user == null) {
                    Log.d(TAG, "User not found: $username")
                    return@withContext Result.failure(IllegalArgumentException("Invalid username or password"))
                }


                val passwordValid: Boolean
                try {
                    passwordValid = PasswordUtils.checkPassword(password, user.passwordHash)
                } catch (e: Exception) {
                    Log.e(TAG, "Error checking password", e)
                    return@withContext Result.failure(Exception("Error verifying password: ${e.message}"))
                }

                if (!passwordValid) {
                    Log.d(TAG, "Invalid password for user: $username")
                    return@withContext Result.failure(IllegalArgumentException("Invalid username or password"))
                }


                try {
                    preferencesManager.saveUserInfo(user.id, user.username)
                    Log.d(TAG, "User logged in successfully: ID=${user.id}, Username=${user.username}")
                } catch (e: Exception) {
                    Log.e(TAG, "Error saving user info to preferences", e)
                }

                Result.success(AuthResult(
                    success = true,
                    userId = user.id,
                    username = user.username
                ))
            } catch (e: Exception) {
                Log.e(TAG, "Error logging in user", e)
                Result.failure(e)
            }
        }
    }

    fun logout() {
        AppModule.providePreferencesManager().clearUserInfo()
        Log.d(TAG, "Logging out user")
        try {
            preferencesManager.clearUserInfo()
        } catch (e: Exception) {
            Log.e(TAG, "Error clearing user info", e)
        }
    }

    fun isLoggedIn(): Boolean {
        try {
            val isLoggedIn = preferencesManager.isLoggedIn()
            Log.d(TAG, "Checking if user is logged in: $isLoggedIn")
            return isLoggedIn
        } catch (e: Exception) {
            Log.e(TAG, "Error checking login status", e)
            return false
        }
    }

    suspend fun getUserProfile(userId: Int): Result<UserProfile> {
        return withContext(Dispatchers.IO) {
            try {
                Log.d(TAG, "Getting user profile for ID: $userId")

                val user = userDao.getUserById(userId)
                if (user == null) {
                    Log.d(TAG, "User not found for ID: $userId")
                    return@withContext Result.failure(IllegalArgumentException("User not found"))
                }

                Log.d(TAG, "User profile retrieved successfully for ID: $userId")
                Result.success(UserProfile(
                    id = user.id,
                    username = user.username,
                    dailyGoal = user.dailyGoal,
                    createdAt = user.createdAt
                ))
            } catch (e: Exception) {
                Log.e(TAG, "Error getting user profile", e)
                Result.failure(e)
            }
        }
    }

    suspend fun updateDailyGoal(userId: Int, newGoal: Int): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                Log.d(TAG, "Updating daily goal for user ID: $userId to $newGoal")

                userDao.updateDailyGoal(userId, newGoal)
                Log.d(TAG, "Daily goal updated successfully for user ID: $userId")

                Result.success(true)
            } catch (e: Exception) {
                Log.e(TAG, "Error updating daily goal", e)
                Result.failure(e)
            }
        }
    }
}