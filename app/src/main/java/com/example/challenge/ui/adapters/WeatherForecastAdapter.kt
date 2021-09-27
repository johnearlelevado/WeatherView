package com.example.challenge.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Space
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge.R
import com.example.challenge.ui.ResultClickListener
import com.example.challenge.room.entities.Weather
import com.example.challenge.util.AppUtil

class WeatherForecastAdapter(private val resultClickListener: ResultClickListener?) :
    RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder>() {

    var resultModelList: List<Weather>? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var img_weather: AppCompatImageView
        var tv_time: TextView
        var tv_day: TextView
        var tv_date: TextView
        var tv_hi_temp: TextView
        var tv_low_temp: TextView
        var spc_hidden_daily_divider: Space
        var tv_complete_date: TextView


        fun bindData(resultModel: Weather) {
            AppUtil.setWeatherIcon(img_weather,resultModel.weather)
            tv_day.text = resultModel.day
            tv_date.text = resultModel.date
            tv_time.text = resultModel.dateTime
            tv_hi_temp.text = "${resultModel.maxTemp}°"
            tv_low_temp.text = "/ ${resultModel.minTemp}°"
            tv_complete_date.isVisible = resultModel.dateTime == "02:00"
            tv_complete_date.text = "${resultModel.day}, ${resultModel.date}"
            spc_hidden_daily_divider.isVisible = resultModel.dateTime == "23:00"
        }

        override fun onClick(v: View) {
            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION && resultClickListener != null) {
                resultClickListener.onResultItemClick(resultModelList!![pos])
            }
        }

        init {
            img_weather = itemView.findViewById(R.id.img_weather)
            tv_day = itemView.findViewById(R.id.tv_day)
            tv_date = itemView.findViewById(R.id.tv_date)
            tv_hi_temp = itemView.findViewById(R.id.tv_hi_temp)
            tv_low_temp = itemView.findViewById(R.id.tv_low_temp)
            tv_time = itemView.findViewById(R.id.tv_time)
            tv_complete_date = itemView.findViewById(R.id.tv_complete_date)
            spc_hidden_daily_divider = itemView.findViewById(R.id.spc_hidden_daily_divider)
            itemView.setOnClickListener(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_forecast_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(resultModelList!![position])
    }

    override fun getItemCount(): Int {
        return if (resultModelList != null) resultModelList!!.size else 0
    }
}