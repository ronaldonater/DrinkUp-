package com.example.drinkup.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class PreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREF_NAME, Context.MODE_PRIVATE
    )

    companion object {
        private const val PREF_NAME = "drinkup_preferences"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USERNAME = "username"
        private const val TAG = "PreferenceManager"
    }

    fun saveUserInfo(userId: Int, username: String) {
        try {
            Log.d(TAG, "Saving user info: ID=$userId, Username=$username")
            sharedPreferences.edit()
                .putInt(KEY_USER_ID, userId)
                .putString(KEY_USERNAME, username)
                .apply()
        } catch (e: Exception) {
            Log.e(TAG, "Error saving user info", e)
        }
    }

    fun getUserId(): Int {
        val userId = sharedPreferences.getInt(KEY_USER_ID, -1)
        Log.d(TAG, "Getting user ID: $userId")
        return userId
    }

    fun getUsername(): String? {
        val username = sharedPreferences.getString(KEY_USERNAME, null)
        Log.d(TAG, "Getting username: $username")
        return username
    }

    fun clearUserInfo() {
        Log.d(TAG, "Clearing user info")
        sharedPreferences.edit()
            .remove(KEY_USER_ID)
            .remove(KEY_USERNAME)
            .apply()
    }

    fun isLoggedIn(): Boolean {
        val userId = getUserId()
        val username = getUsername()
        val isLoggedIn = userId != -1 && username != null
        Log.d(TAG, "Checking if logged in: $isLoggedIn (ID=$userId, Username=$username)")
        return isLoggedIn
    }

    fun setNotificationsEnabled(enabled: Boolean) {
        sharedPreferences.edit().putBoolean("notifications_enabled", enabled).apply()
    }

    fun isNotificationsEnabled(): Boolean {
        return sharedPreferences.getBoolean("notifications_enabled", false)
    }
}