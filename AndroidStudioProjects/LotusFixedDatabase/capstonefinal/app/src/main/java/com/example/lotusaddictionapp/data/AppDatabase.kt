package com.example.lotusaddictionapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [User::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val NAME = "App_Database"
    }
    abstract fun getUserDao(): UserDao
}