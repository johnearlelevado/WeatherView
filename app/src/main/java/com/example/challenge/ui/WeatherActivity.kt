package com.example.challenge.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge.R
import com.example.challenge.api.common.ApiResponse
import com.example.challenge.api.common.Status
import com.example.challenge.databinding.ActivityWeatherBinding
import com.example.challenge.room.entities.Weather
import com.example.challenge.ui.adapters.WeatherForecastAdapter
import com.example.challenge.util.AppUtil
import com.example.challenge.viewmodels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class WeatherActivity : BaseActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()

    lateinit var binding: ActivityWeatherBinding
    lateinit var adapter: WeatherForecastAdapter
    val dateFormat = SimpleDateFormat("dd/MM")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeViewModel()
        initializeRecyclerView()

        weatherViewModel.getWeatherItems(place = "Manila",
            unit = "metric",
            appId = getString(R.string.app_id),
            context = this)
    }

    /**
     * setup recyclerview with layoutmanager and adapter
     * */
    private fun initializeRecyclerView() {
        binding.rcvList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = WeatherForecastAdapter(ResultClickListener { result ->
            val i = Intent(this@WeatherActivity, DetailsActivity::class.java)
            i.putExtra("item", result)
            startActivity(i)
        })
        binding.rcvList.adapter = adapter
    }

    /**
     * initialize handling for observable/flowable data from Network and DB
     * */
    private fun initializeViewModel() {
        // handle network response
        weatherViewModel.weatherItemsObservable.observe(this, androidx.lifecycle.Observer {
            handleResponse(it)
        })
        // update the adapter with new data inserted into DB
        weatherViewModel.repository.getWeatherItemsResults().subscribe { weatherList ->
            lifecycleScope.launch {

                binding.rcvList.adapter = adapter
                adapter.resultModelList = weatherList.filter { weather -> weather.date != dateFormat.format(Date()) }
                adapter.notifyDataSetChanged()

                val weather = weatherList.filter { weather -> weather.date == dateFormat.format(Date()) }.firstOrNull()

                setCurrentWeather(weather)
            }
        }
    }

    /**
     * set current weather data
     * */
    private fun setCurrentWeather(weather: Weather?) {
        val cityName = "Manila"
        val temp = weather?.maxTemp
        val tempMin = weather?.minTemp
        val tempMax = weather?.maxTemp
        val weatherDescription = weather?.weatherDescription
        binding.address.text = "$cityName"
        binding.temp.text = "${temp?.toInt() ?: 0}°C"
        binding.tempMin.text = "Low ${tempMin?.toInt()}°C"
        binding.tempMax.text = "Hi ${tempMax?.toInt()}°C"
        binding.status.text = weatherDescription?.capitalizeWords()
        AppUtil.setWeatherIcon(binding.imgWeather, weather?.weather ?: 0)
    }

    private fun handleResponse(apiResponse: ApiResponse<List<Weather>>?) {
        when (apiResponse?.status) {
            Status.FAIL -> {
                showToast(apiResponse?.throwable?.message!!)
            }
            Status.LOADING -> {}
        }
    }


}