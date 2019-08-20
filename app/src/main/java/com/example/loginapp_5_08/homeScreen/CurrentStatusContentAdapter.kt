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
import kotlinx.android.synthetic.main.current_status_layout.view.*
import kotlinx.android.synthetic.main.today_status_layout.view.*
import kotlinx.android.synthetic.main.weekly_status_layout.view.*

class CurrentStatusContentAdapter(private val rows: List<WeatherReportsRow>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface WeatherReportsRow
    class StatusRow(val cTemp: String, val cCity: String, val cStatus: String, val cImage: Int) : WeatherReportsRow
    class TodayRow : WeatherReportsRow
    class WeekdaysRow : WeatherReportsRow

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
            is StatusRow -> TYPE_STATUS
            is TodayRow -> TYPE_TODAY
            is WeekdaysRow -> TYPE_WEEKDAYS
            else -> throw IllegalArgumentException()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {

        TYPE_STATUS -> StatusViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.current_status_layout, parent, false)
        )

        TYPE_TODAY -> TodayViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.today_status_layout, parent, false)
        )

        TYPE_WEEKDAYS -> WeekdayViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.weekly_status_layout, parent, false)
        )
        else -> throw IllegalArgumentException()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {

            TYPE_STATUS -> onBindStatus(holder, rows[position] as StatusRow)
            TYPE_TODAY -> onBindToday(holder)
            TYPE_WEEKDAYS -> onBindDays(holder)
            else -> throw IllegalArgumentException()
        }

    private fun onBindStatus(holder: RecyclerView.ViewHolder, row: StatusRow) {
        val headerRow = holder as StatusViewHolder
        headerRow.currentTemp.text = row.cTemp
        headerRow.currentCity.text = row.cCity
        headerRow.currentStatus.text = row.cStatus
        headerRow.currentImage.setImageResource(row.cImage)
    }

    private fun onBindToday(holder: RecyclerView.ViewHolder) {
        (holder as TodayViewHolder).hourdayRecyclerView.adapter =
            HoursContentAdapter(getSampleRowForHou(1))

        holder.hourdayRecyclerView.layoutManager = LinearLayoutManager(holder.hourdayRecyclerView.context,LinearLayout.HORIZONTAL, false)

    }

    private fun onBindDays(holder: RecyclerView.ViewHolder) {
        (holder as WeekdayViewHolder).weekdayRecyclerView.adapter =
            DaysContentAdapter(getSampleRow(1))
        holder.weekdayRecyclerView.layoutManager = LinearLayoutManager(holder.weekdayRecyclerView.context,LinearLayout.HORIZONTAL, false)
    }

    companion object {
        private const val TYPE_STATUS = 0
        private const val TYPE_TODAY = 1
        private const val TYPE_WEEKDAYS = 2
    }

    private fun getSampleRow(numSections: Int): List<DaysContentAdapter.WeekDaysRow> {
        val rows = mutableListOf<DaysContentAdapter.WeekDaysRow>()
        for (i in 1..numSections) {
            rows.add(
                DaysContentAdapter.WeekDaysRow(
                    "Fri",
                    "24° C - 32° C",
                    R.drawable.sunny
                )
            )
            rows.add(
                DaysContentAdapter.WeekDaysRow(
                    "Sat",
                    "27° C - 35° C",
                    R.drawable.partlysunny
                )
            )
            rows.add(
                DaysContentAdapter.WeekDaysRow(
                    "Sun",
                    "29° C - 39° C",
                    R.drawable.sunny
                )
            )
            rows.add(
                DaysContentAdapter.WeekDaysRow(
                    "Mon",
                    "21° C - 22° C",
                    R.drawable.cloudy
                )
            )
            rows.add(
                DaysContentAdapter.WeekDaysRow(
                    "Tue",
                    "24° C - 32° C",
                    R.drawable.nightcloudy
                )
            )
            rows.add(
                DaysContentAdapter.WeekDaysRow(
                    "Wed",
                    "29° C - 32° C",
                    R.drawable.cloudy
                )
            )
            rows.add(
                DaysContentAdapter.WeekDaysRow(
                    "Thu",
                    "34° C - 39° C",
                    R.drawable.sunny
                )
            )
            rows.add(
                DaysContentAdapter.WeekDaysRow(
                    "Fri",
                    "38° C - 39° C",
                    R.drawable.sunny
                )
            )
        }
        return rows
    }

    private fun getSampleRowForHou(numSections: Int): List<HoursContentAdapter.HoursRow> {
        val rows = mutableListOf<HoursContentAdapter.HoursRow>()
        for (i in 1..numSections) {
            rows.add(
                HoursContentAdapter.HoursRow(
                    "01 AM",
                    "24° C",
                    R.drawable.nightcloudy
                )
            )
            rows.add(
                HoursContentAdapter.HoursRow(
                    "05 AM",
                    "21° C",
                    R.drawable.cloudy
                )
            )
            rows.add(
                HoursContentAdapter.HoursRow(
                    "09 AM",
                    "23° C",
                    R.drawable.sunny
                )
            )
            rows.add(
                HoursContentAdapter.HoursRow(
                    "01 PM",
                    "29° C",
                    R.drawable.sunny
                )
            )
            rows.add(
                HoursContentAdapter.HoursRow(
                    "05 PM",
                    "22° C",
                    R.drawable.partlysunny
                )
            )
        }
        return rows
    }
}