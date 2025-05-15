package com.example.drinkup.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinkup.data.entities.LeaderboardEntry
import com.example.drinkup.data.entities.UserProfile
import com.example.drinkup.repositories.FriendRepository
import kotlinx.coroutines.launch

class FriendViewModel(private val repository: FriendRepository) : ViewModel() {

    private val _friends = MutableLiveData<Result<List<UserProfile>>>()
    val friends: LiveData<Result<List<UserProfile>>> = _friends

    private val _addFriendResult = MutableLiveData<Result<Boolean>>()
    val addFriendResult: LiveData<Result<Boolean>> = _addFriendResult

    private val _removeFriendResult = MutableLiveData<Result<Boolean>>()
    val removeFriendResult: LiveData<Result<Boolean>> = _removeFriendResult

    private val _leaderboard = MutableLiveData<Result<List<LeaderboardEntry>>>()
    val leaderboard: LiveData<Result<List<LeaderboardEntry>>> = _leaderboard

    fun loadFriends(userId: Int) {
        viewModelScope.launch {
            val result = repository.getFriends(userId)
            _friends.value = result
        }
    }

    fun addFriend(userId: Int, friendUsername: String) {
        viewModelScope.launch {
            val result = repository.addFriend(userId, friendUsername)
            _addFriendResult.value = result
        }
    }

    fun removeFriend(userId: Int, friendId: Int) {
        viewModelScope.launch {
            val result = repository.removeFriend(userId, friendId)
            _removeFriendResult.value = result
        }
    }

    fun loadLeaderboard(userId: Int) {
        viewModelScope.launch {
            val result = repository.getLeaderboard(userId)
            _leaderboard.value = result
        }
    }
}