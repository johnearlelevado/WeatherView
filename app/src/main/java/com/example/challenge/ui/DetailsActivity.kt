package com.example.challenge.ui

import android.os.Bundle
import com.example.challenge.databinding.ActivityDetailsBinding
import com.example.challenge.room.entities.Weather
import com.example.challenge.util.AppUtil

class DetailsActivity : BaseActivity() {
    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgClose.setOnClickListener {
            finish()
        }
        val item = intent.extras?.getParcelable<Weather>("item")
        AppUtil.setWeatherIcon(binding.imgWeatherIcon,item?.weather ?: 0)
        binding.tvCompleteDateTime.text = "${item?.day}, ${item?.date} ${item?.dateTime}"
        binding.tvWeatherDescription.text = "${item?.weatherDescription?.capitalizeWords()}"
        binding.tvHumidity.text = "${item?.humidity}"
        binding.tvPressure.text = "${item?.pressure}"
        binding.tvWind.text = "${item?.wind}"
        binding.tvTemp.text = "${item?.maxTemp}° / ${item?.minTemp}°"
    }
}