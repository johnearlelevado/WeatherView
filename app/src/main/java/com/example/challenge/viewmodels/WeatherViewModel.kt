package com.example.challenge.viewmodels

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import com.example.challenge.repository.Repository

open class WeatherViewModel @ViewModelInject constructor(
    val repository: Repository) : BaseViewModel() {

    val weatherItemsObservable
        get() = repository.getWeatherListLiveData()

    /**
     * Fetch weather items
     **/
    fun getWeatherItems(place:String,unit:String,appId:String,context: Context) {
        repository.getForecastData(place,unit,appId,context)
    }

}