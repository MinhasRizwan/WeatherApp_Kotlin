package com.example.loginapp_5_08.homeScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp_5_08.R


class DaysContentAdapter(private val rows: List<DayRow>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    interface DayRow
    class WeekDaysRow (val dayName:String , val minmaxTemp:String, val imageDay: Int) :
        DayRow

    class WeekDaysViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val todayDay:TextView = itemView.findViewById(R.id.weekDay)
        val rangeTemp:TextView = itemView.findViewById(R.id.rangeTemp)
        val imageOfDay: ImageView = itemView.findViewById(R.id.weekdayImage)
    }

    override fun getItemCount() = rows.count()

    override fun getItemViewType(position: Int): Int =
        when (rows[position]) {
            is WeekDaysRow -> TYPE_WEEKDAY
            else -> throw IllegalArgumentException()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_WEEKDAY -> WeekDaysViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.week_status, parent, false)
        )
        else -> throw IllegalArgumentException()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            TYPE_WEEKDAY -> onBindWeekDay(holder, rows[position] as WeekDaysRow)
            else -> throw IllegalArgumentException()
        }

    private fun onBindWeekDay(holder: RecyclerView.ViewHolder, row: WeekDaysRow) {
        val dayRow = holder as WeekDaysViewHolder
        dayRow.todayDay.text = row.dayName
        dayRow.rangeTemp.text = row.minmaxTemp
        dayRow.imageOfDay.setImageResource(row.imageDay)
    }

    companion object {
        private const val TYPE_WEEKDAY = 0
    }
}