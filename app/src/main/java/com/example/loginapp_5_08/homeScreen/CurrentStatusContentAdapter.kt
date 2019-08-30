package com.example.loginapp_5_08.homeScreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.content_hour_details.view.*
import kotlinx.android.synthetic.main.layout_current_status.view.*
import com.example.loginapp_5_08.R
import com.example.loginapp_5_08.data.response.response.current.CurrentWeatherResponseOWM
import com.example.loginapp_5_08.data.response.response.future.FutureWeatherResponseOWM
import kotlinx.android.synthetic.main.content_week_details.view.*


class CurrentStatusContentAdapter(
    private val sampleRows: List<WeatherReports>,
    private val futureWeatherResponseData: FutureWeatherResponseOWM,
    private val homeFragment: HomeFragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class StatusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currentImage: ImageView = itemView.weatherNowImage
        val currentTemp: TextView = itemView.currentTempText
        val currentCity: TextView = itemView.currentCityText
        val currentStatus: TextView = itemView.currentStatusText
    }

    class TodayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hourdayRecyclerView: RecyclerView = itemView.HourslistView
    }

    class WeekdayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val weekdayRecyclerView: RecyclerView = itemView.week_nested_rv
    }

    override fun getItemCount() = sampleRows.count()

    override fun getItemViewType(position: Int): Int =
        when (sampleRows[position]) {
            is WeatherReports.StatusRow -> TYPE_STATUS
            is WeatherReports.TodayRow -> TYPE_TODAY
            is WeatherReports.WeekdaysRow -> TYPE_WEEKDAYS
            //else -> throw IllegalArgumentException()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {

        TYPE_STATUS -> StatusViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_current_status, parent, false)
        )

        TYPE_TODAY -> TodayViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.content_hour_details, parent, false)
        )

        TYPE_WEEKDAYS -> WeekdayViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.content_week_details, parent, false)
        )
        else -> throw IllegalArgumentException()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {

            TYPE_STATUS -> onBindStatus(holder)
            TYPE_TODAY -> onBindToday(holder)
            TYPE_WEEKDAYS -> onBindDays(holder)
            else -> throw IllegalArgumentException()
        }

    @SuppressLint("SetTextI18n")
    private fun onBindStatus(holder: RecyclerView.ViewHolder) {
        val rowCurrentStatus = holder as StatusViewHolder
        rowCurrentStatus.currentTemp.text = ((futureWeatherResponseData.list[0].main.temp) - 273.15).toInt().toString()+ "° C"
        rowCurrentStatus.currentCity.text = futureWeatherResponseData.city.name
        rowCurrentStatus.currentStatus.text = futureWeatherResponseData.list[0].weather[0].description

        Glide.with(homeFragment).load("https://openweathermap.org/img/wn/"+futureWeatherResponseData.list[0].weather[0].icon+"@2x.png").into(rowCurrentStatus.currentImage)
    }

    private fun onBindToday(holder: RecyclerView.ViewHolder) {
        (holder as TodayViewHolder).hourdayRecyclerView.adapter =
            HoursContentAdapter(futureWeatherResponseData, homeFragment)
        holder.hourdayRecyclerView.layoutManager =
            LinearLayoutManager(holder.hourdayRecyclerView.context,LinearLayout.HORIZONTAL, false) as RecyclerView.LayoutManager?
    }

    private fun onBindDays(holder: RecyclerView.ViewHolder) {
        (holder as WeekdayViewHolder).weekdayRecyclerView.adapter =
            DaysContentAdapter(futureWeatherResponseData, homeFragment)
        holder.weekdayRecyclerView.layoutManager = LinearLayoutManager(holder.weekdayRecyclerView.context,LinearLayout.HORIZONTAL, false)
    }

    companion object {
        private const val TYPE_STATUS = 0
        private const val TYPE_TODAY = 1
        private const val TYPE_WEEKDAYS = 2
    }

    sealed class WeatherReports{
        object StatusRow : WeatherReports()
        object TodayRow : WeatherReports()
        object WeekdaysRow : WeatherReports()
    }
}