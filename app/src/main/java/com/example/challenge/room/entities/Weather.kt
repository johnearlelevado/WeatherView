package com.example.challenge.room.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "weather")
@Parcelize
data class Weather (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "timeInMillis")
    var timeInMillis: Long,
    @ColumnInfo(name = "dateTime")
    var dateTime: String,
    @ColumnInfo(name = "day")
    var day: String,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "weather")
    var weather: Int,
    @ColumnInfo(name = "weatherDescription")
    var weatherDescription: String,
    @ColumnInfo(name = "minTemp")
    var minTemp: String,
    @ColumnInfo(name = "maxTemp")
    var maxTemp: String,
    @ColumnInfo(name = "pressure")
    var pressure: String,
    @ColumnInfo(name = "humidity")
    var humidity: String,
    @ColumnInfo(name = "wind")
    var wind: String
): Parcelable