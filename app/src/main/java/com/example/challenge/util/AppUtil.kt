package com.example.challenge.util


import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import com.example.challenge.R
import com.example.challenge.room.entities.Weather
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


object AppUtil {
    /**
     * Set icon to imageView according to weather code status
     *
     * @param context     instance of [Context]
     * @param imageView   instance of [android.widget.ImageView]
     * @param weatherCode code of weather status
     */
    fun setWeatherIcon(imageView: AppCompatImageView, weatherCode: Int) {
        if (weatherCode / 100 == 2) {
            imageView.setImageResource(R.drawable.ic_storm_weather)
            imageView.tag = R.drawable.ic_storm_weather
        } else if (weatherCode / 100 == 3) {
            imageView.setImageResource(R.drawable.ic_rainy_weather)
            imageView.tag = R.drawable.ic_rainy_weather
        } else if (weatherCode / 100 == 5) {
            imageView.setImageResource(R.drawable.ic_rainy_weather)
            imageView.tag = R.drawable.ic_rainy_weather
        } else if (weatherCode / 100 == 6) {
            imageView.setImageResource(R.drawable.ic_snow_weather)
            imageView.tag = R.drawable.ic_snow_weather
        } else if (weatherCode / 100 == 7) {
            imageView.setImageResource(R.drawable.ic_unknown)
            imageView.tag = R.drawable.ic_unknown
        } else if (weatherCode == 800) {
            imageView.setImageResource(R.drawable.ic_clear_day)
            imageView.tag = R.drawable.ic_clear_day
        } else if (weatherCode == 801) {
            imageView.setImageResource(R.drawable.ic_few_clouds)
            imageView.tag = R.drawable.ic_few_clouds
        } else if (weatherCode == 803) {
            imageView.setImageResource(R.drawable.ic_broken_clouds)
            imageView.tag = R.drawable.ic_broken_clouds
        } else if (weatherCode / 100 == 8) {
            imageView.setImageResource(R.drawable.ic_cloudy_weather)
            imageView.tag = R.drawable.ic_cloudy_weather
        }
    }

    val dayFormat = SimpleDateFormat("EEE")
    val dateFormat = SimpleDateFormat("dd/MM")
    val timeFormat = SimpleDateFormat("HH:mm")

    fun processResponse(response: String?): ArrayList<Weather> {
        val jsonObject = JSONObject(response)
        val jsonArray = jsonObject.getJSONArray("list")
        var weatherList = ArrayList<Weather>()

        for (index in 0 until jsonArray.length()) {
            val weather = jsonArray.getJSONObject(index)
            val dateTime = Date(weather.getLong("dt") * 1000L)
            val weatherObj = Weather(
                id = 0,
                timeInMillis = weather.getLong("dt"),
                dateTime = timeFormat.format(dateTime),
                day = dayFormat.format(dateTime),
                date = dateFormat.format(dateTime),
                weather = weather.getJSONArray("weather").getJSONObject(0).getInt("id"),
                weatherDescription = weather.getJSONArray("weather").getJSONObject(0)
                    .getString("description"),
                maxTemp = weather.getJSONObject("main").getDouble("temp_max").toInt().toString(),
                minTemp = weather.getJSONObject("main").getDouble("temp_min").toInt().toString(),
                humidity = weather.getJSONObject("main").getDouble("humidity").toString(),
                pressure = weather.getJSONObject("main").getDouble("pressure").toString(),
                wind = weather.getJSONObject("wind").getDouble("speed").toString()
            )
            weatherList.add(weatherObj)
        }
        return weatherList
    }

}