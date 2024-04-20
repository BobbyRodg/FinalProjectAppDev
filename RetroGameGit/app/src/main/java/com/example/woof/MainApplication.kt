package com.example.woof

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.woof.data.UserDao
import com.example.woof.data.AppDatabase

class MainApplication : Application() {

    companion object {
        lateinit var appDatabase: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.NAME
        ).build()
    }
}