package com.example.drinkup.di

import android.content.Context
import com.example.drinkup.data.AppDatabase
import com.example.drinkup.repositories.AuthRepository
import com.example.drinkup.repositories.FriendRepository
import com.example.drinkup.repositories.IntakeRepository
import com.example.drinkup.utils.PreferenceManager
import com.example.drinkup.viewmodels.AuthViewModel
import com.example.drinkup.viewmodels.FriendViewModel
import com.example.drinkup.viewmodels.IntakeViewModel

object AppModule {

    private lateinit var database: AppDatabase
    private lateinit var preferencesManager: PreferenceManager

    private lateinit var authRepository: AuthRepository
    private lateinit var intakeRepository: IntakeRepository
    private lateinit var friendRepository: FriendRepository

    fun initialize(context: Context) {
        database = AppDatabase.getDatabase(context)

        preferencesManager = PreferenceManager(context)

        // Initialize repositories
        authRepository = AuthRepository(database.userDao(), preferencesManager)
        intakeRepository = IntakeRepository(database.waterEntryDao(), database.userDao())
        friendRepository = FriendRepository(database.friendshipDao(), database.userDao(), database.waterEntryDao())
    }

    fun provideAuthViewModel(): AuthViewModel {
        return AuthViewModel(authRepository)
    }

    fun provideIntakeViewModel(): IntakeViewModel {
        return IntakeViewModel(intakeRepository)
    }

    fun provideFriendViewModel(): FriendViewModel {
        return FriendViewModel(friendRepository)
    }

    fun providePreferencesManager(): PreferenceManager {
        return preferencesManager
    }

    fun provideDatabase(): AppDatabase {
        return database
    }

    fun provideIntakeRepository(): IntakeRepository {
        return intakeRepository
    }
}