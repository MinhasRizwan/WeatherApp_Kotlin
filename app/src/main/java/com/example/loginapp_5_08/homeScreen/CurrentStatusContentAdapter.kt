package com.example.loginapp_5_08.homeScreen


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp_5_08.R
import kotlinx.android.synthetic.main.content_hour_details.view.*
import kotlinx.android.synthetic.main.layout_current_status.view.*
import kotlinx.android.synthetic.main.layout_week_details.view.*

class CurrentStatusContentAdapter(private val rows: List<WeatherReports>, private val allWeatherData : WeatherData) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

    override fun getItemCount() = rows.count()

    override fun getItemViewType(position: Int): Int =
        when (rows[position]) {
            is WeatherReports.StatusRow -> TYPE_STATUS
            is WeatherReports.TodayRow -> TYPE_TODAY
            is WeatherReports.WeekdaysRow -> TYPE_WEEKDAYS
            else -> throw IllegalArgumentException()
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
                .inflate(R.layout.layout_week_details, parent, false)
        )
        else -> throw IllegalArgumentException()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {

            TYPE_STATUS -> onBindStatus(holder, rows[position] as WeatherReports.StatusRow)
            TYPE_TODAY -> onBindToday(holder)
            TYPE_WEEKDAYS -> onBindDays(holder)
            else -> throw IllegalArgumentException()
        }

    private fun onBindStatus(holder: RecyclerView.ViewHolder, row: WeatherReports.StatusRow) {
        val rowCurrentStatus = holder as StatusViewHolder
        rowCurrentStatus.currentTemp.text = row.cTemp
        rowCurrentStatus.currentCity.text = row.cCity
        rowCurrentStatus.currentStatus.text = row.cStatus
        rowCurrentStatus.currentImage.setImageResource(row.cImage)
    }

    private fun onBindToday(holder: RecyclerView.ViewHolder) {
        (holder as TodayViewHolder).hourdayRecyclerView.adapter =
            HoursContentAdapter(allWeatherData.hoursRows)
        holder.hourdayRecyclerView.layoutManager = LinearLayoutManager(holder.hourdayRecyclerView.context,LinearLayout.HORIZONTAL, false)

    }

    private fun onBindDays(holder: RecyclerView.ViewHolder) {
        (holder as WeekdayViewHolder).weekdayRecyclerView.adapter =
            DaysContentAdapter(allWeatherData.daysRows)
        holder.weekdayRecyclerView.layoutManager = LinearLayoutManager(holder.weekdayRecyclerView.context,LinearLayout.HORIZONTAL, false)
    }

    companion object {
        private const val TYPE_STATUS = 0
        private const val TYPE_TODAY = 1
        private const val TYPE_WEEKDAYS = 2
    }


    sealed class WeatherReports{
        class StatusRow(val cTemp: String, val cCity: String, val cStatus: String, val cImage: Int) : WeatherReports()
        object TodayRow : WeatherReports()
        object WeekdaysRow : WeatherReports()
    }
}