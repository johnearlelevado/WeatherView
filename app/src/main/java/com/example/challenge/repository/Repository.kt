package com.example.challenge.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.challenge.api.common.ApiResponse
import com.example.challenge.room.entities.Weather
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

interface Repository {

    fun getCompositeDisposableObject(): CompositeDisposable

    fun getForecastData(place:String,units:String,appid:String,context: Context)

    fun getWeatherListLiveData(): MutableLiveData<ApiResponse<List<Weather>>>

    fun getWeatherItemsResults(): Flowable<List<Weather>>
}