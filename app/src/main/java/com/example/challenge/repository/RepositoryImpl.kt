package com.example.challenge.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.challenge.BuildConfig
import com.example.challenge.api.common.ApiResponse
import com.example.challenge.room.AppDatabase
import com.example.challenge.room.entities.Weather
import com.example.challenge.util.AppUtil
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RepositoryImpl constructor(
    private val db: AppDatabase
): Repository {
    val compositeDisposable = CompositeDisposable()
    val weatherItemsResults = BehaviorSubject.create<List<Weather>>()

    val weatherResponseLiveData = MutableLiveData<ApiResponse<List<Weather>>>()

    init {
        db.weatherDao().getWeatherResults().subscribe {
            weatherItemsResults.onNext(it)
        }
    }

    override fun getCompositeDisposableObject(): CompositeDisposable {
        return compositeDisposable
    }

    override fun getWeatherItemsResults(): Flowable<List<Weather>> {
        return weatherItemsResults.toFlowable(BackpressureStrategy.LATEST)
    }

    override fun getForecastData(place:String,units:String,appid:String,context: Context)  {
        val queue = Volley.newRequestQueue(context)
        val url = "${BuildConfig.API_URL}/data/2.5/forecast?q=$place&units=$units&appid=$appid"

        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                weatherResponseLiveData.value = ApiResponse.success(response = AppUtil.processResponse(response))
                GlobalScope.launch {
                    AppUtil.processResponse(response)?.let {
                        db.weatherDao().deleteWeatherResults()
                        db.weatherDao().insertWeatherResults(it)
                    }
                }
            },
            Response.ErrorListener {
                weatherResponseLiveData.value = ApiResponse.fail(it)
            })

        queue.add(stringRequest)
    }

    override fun getWeatherListLiveData(): MutableLiveData<ApiResponse<List<Weather>>> {
        return weatherResponseLiveData
    }

}