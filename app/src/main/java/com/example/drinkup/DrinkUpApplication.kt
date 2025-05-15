package com.example.drinkup

import android.app.Application
import com.example.drinkup.di.AppModule

class DrinkUpApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppModule.initialize(this)
    }
}