package com.example.drinkup.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.drinkup.data.dao.FriendshipDao
import com.example.drinkup.data.dao.UserDao
import com.example.drinkup.data.dao.WaterEntryDao
import com.example.drinkup.data.entities.Friendship
import com.example.drinkup.data.entities.User
import com.example.drinkup.data.entities.WaterEntry

@Database(
    entities = [User::class, WaterEntry::class, Friendship::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun waterEntryDao(): WaterEntryDao
    abstract fun friendshipDao(): FriendshipDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "drinkup_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}