package com.example.loginapp_5_08.homeScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp_5_08.R
import kotlinx.android.synthetic.main.content_week_details.view.*

class DaysContentAdapter(private val rows: List<WeekDaysRow>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
            TYPE_WEEKDAY -> onBindWeekDay(holder, rows[position])
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
    class WeekDaysRow (val dayName:String , val minmaxTemp:String, val imageDay: Int)
}