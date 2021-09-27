package com.example.challenge.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.challenge.room.dao.WeatherDao
import com.example.challenge.room.entities.Weather

@Database(entities = [Weather::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}