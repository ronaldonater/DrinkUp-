package com.example.drinkup.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinkup.data.entities.DailyIntakeSummary
import com.example.drinkup.repositories.IntakeRepository
import kotlinx.coroutines.launch

class IntakeViewModel(private val repository: IntakeRepository) : ViewModel() {

    private val _todayIntake = MutableLiveData<Result<DailyIntakeSummary>>()
    val todayIntake: LiveData<Result<DailyIntakeSummary>> = _todayIntake

    private val _addIntakeResult = MutableLiveData<Result<DailyIntakeSummary>>()
    val addIntakeResult: LiveData<Result<DailyIntakeSummary>> = _addIntakeResult

    private val _intakeHistory = MutableLiveData<Result<List<DailyIntakeSummary>>>()
    val intakeHistory: LiveData<Result<List<DailyIntakeSummary>>> = _intakeHistory

    private val _currentStreak = MutableLiveData<Result<Int>>()
    val currentStreak: LiveData<Result<Int>> = _currentStreak

    fun loadTodayIntake(userId: Int) {
        viewModelScope.launch {
            try {
                if (userId <= 0) {
                    _todayIntake.value = Result.failure(IllegalArgumentException("Invalid user ID"))
                    return@launch
                }

                val result = repository.getTodayIntake(userId)
                _todayIntake.value = result
            } catch (e: Exception) {
                _todayIntake.value = Result.failure(e)
            }
        }
    }

    fun addIntake(userId: Int, volume: Int) {
        viewModelScope.launch {
            try {
                if (userId <= 0) {
                    _addIntakeResult.value = Result.failure(IllegalArgumentException("Invalid user ID"))
                    return@launch
                }

                val result = repository.addIntake(userId, volume)
                _addIntakeResult.value = result

                _todayIntake.value = result
            } catch (e: Exception) {
                _addIntakeResult.value = Result.failure(e)
            }
        }
    }

    fun removeIntake(userId: Int, volume: Int) {
        viewModelScope.launch {
            try {
                if (userId <= 0) {
                    _addIntakeResult.value = Result.failure(IllegalArgumentException("Invalid user ID"))
                    return@launch
                }

                val result = repository.removeIntake(userId, volume)
                _addIntakeResult.value = result


                _todayIntake.value = result
            } catch (e: Exception) {
                _addIntakeResult.value = Result.failure(e)
            }
        }
    }

    fun loadIntakeHistory(userId: Int, days: Int = 7) {
        viewModelScope.launch {
            try {
                if (userId <= 0) {
                    _intakeHistory.value = Result.failure(IllegalArgumentException("Invalid user ID"))
                    return@launch
                }

                val result = repository.getIntakeHistory(userId, days)
                _intakeHistory.value = result
            } catch (e: Exception) {
                _intakeHistory.value = Result.failure(e)
            }
        }
    }

    fun loadCurrentStreak(userId: Int) {
        viewModelScope.launch {
            try {
                if (userId <= 0) {
                    _currentStreak.value = Result.failure(IllegalArgumentException("Invalid user ID"))
                    return@launch
                }

                val result = repository.getCurrentStreak(userId)
                _currentStreak.value = result
            } catch (e: Exception) {
                _currentStreak.value = Result.failure(e)
            }
        }
    }
}
