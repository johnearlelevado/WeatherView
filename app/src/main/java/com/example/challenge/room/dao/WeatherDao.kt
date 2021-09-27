package com.example.challenge.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.challenge.room.entities.Weather
import io.reactivex.Flowable

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    suspend fun insertWeatherResults(list: List<Weather>)

    @Query("SELECT * FROM weather ORDER BY id ASC")
    fun getWeatherResults(): Flowable<List<Weather>>


    @Query("DELETE FROM weather")
    suspend fun deleteWeatherResults()
}