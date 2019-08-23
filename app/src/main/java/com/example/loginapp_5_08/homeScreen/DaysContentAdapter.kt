package com.example.loginapp_5_08.homeScreen

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loginapp_5_08.R
import com.example.loginapp_5_08.data.response.future.FutureWeatherResponseData
import kotlinx.android.synthetic.main.content_week_details.view.*
import kotlinx.android.synthetic.main.layout_current_status.view.*

class DaysContentAdapter(private val rows: List<WeekDaysRow>, private val futureWeatherResponseData: FutureWeatherResponseData, private val homeFragment: HomeFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class WeekDaysViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val todayDay:TextView = itemView.weekDay
        val rangeTemp:TextView = itemView.rangeTemp
        val imageOfDay: ImageView = itemView.weekdayImage
    }

    override fun getItemCount() = rows.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_WEEKDAY -> WeekDaysViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.content_week_details, parent, false)
        )
        else -> throw IllegalArgumentException()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            TYPE_WEEKDAY -> onBindWeekDay(holder, position)
            else -> throw IllegalArgumentException()

        }

    private fun onBindWeekDay(holder: RecyclerView.ViewHolder, pos: Int) {
        val dayRow = holder as WeekDaysViewHolder
        //dayRow.todayDay.text = row.dayName

        if ((pos+1) <= futureWeatherResponseData.futureWeatherData.forecastday.size) {
            dayRow.todayDay.text = futureWeatherResponseData.futureWeatherData.forecastday[pos].day.avgtempC.toString()
            //dayRow.rangeTemp.text = row.minmaxTemp
            dayRow.rangeTemp.text = futureWeatherResponseData.futureWeatherData.forecastday[pos].date.toString()
            //dayRow.imageOfDay.setImageResource(R.drawable.sunny)

            Glide.with(homeFragment).load("https:"+futureWeatherResponseData.futureWeatherData.forecastday[pos].day.condition.icon).into(dayRow.imageOfDay)

        }
    }

    companion object {
        private const val TYPE_WEEKDAY = 0
    }

    class WeekDaysRow (val dayName:String , val minmaxTemp:String, val imageDay: Int)
}