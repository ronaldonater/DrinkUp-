package com.example.drinkup.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinkup.data.entities.AuthResult
import com.example.drinkup.data.entities.UserProfile
import com.example.drinkup.repositories.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {
    private val TAG = "AuthViewModel"

    private val _loginResult = MutableLiveData<Result<AuthResult>?>()
    val loginResult: LiveData<Result<AuthResult>?> = _loginResult

    private val _registerResult = MutableLiveData<Result<AuthResult>?>()
    val registerResult: LiveData<Result<AuthResult>?> = _registerResult

    private val _userProfile = MutableLiveData<Result<UserProfile>?>()
    val userProfile: LiveData<Result<UserProfile>?> = _userProfile

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Attempting login for user: $username")
                val result = repository.login(username, password)
                _loginResult.value = result
            } catch (e: Exception) {
                Log.e(TAG, "Error in login", e)
                _loginResult.value = Result.failure(e)
            }
        }
    }

    fun register(username: String, password: String, dailyGoal: Int = 2000) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Attempting registration for user: $username")
                val result = repository.register(username, password, dailyGoal)
                _registerResult.value = result
            } catch (e: Exception) {
                Log.e(TAG, "Error in registration", e)
                _registerResult.value = Result.failure(e)
            }
        }
    }

    fun logout() {
        try {
            Log.d(TAG, "Logging out user")
            repository.logout()


            _loginResult.value = null
            _registerResult.value = null
            _userProfile.value = null
        } catch (e: Exception) {
            Log.e(TAG, "Error during logout", e)
        }
    }


    suspend fun getUserProfile(userId: Int): Result<UserProfile> {
        return try {
            Log.d(TAG, "Getting user profile for ID: $userId")
            repository.getUserProfile(userId)
        } catch (e: Exception) {
            Log.e(TAG, "Error getting user profile", e)
            Result.failure(e)
        }
    }

    suspend fun updateDailyGoal(userId: Int, newGoal: Int): Result<Boolean> {
        return try {
            Log.d(TAG, "Updating daily goal for user ID: $userId to $newGoal")
            repository.updateDailyGoal(userId, newGoal)
        } catch (e: Exception) {
            Log.e(TAG, "Error updating daily goal", e)
            Result.failure(e)
        }
    }
}
