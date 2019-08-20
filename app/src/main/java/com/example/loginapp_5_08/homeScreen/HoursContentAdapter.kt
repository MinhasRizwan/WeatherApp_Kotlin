package com.example.loginapp_5_08.homeScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp_5_08.R

class HoursContentAdapter(private val rows: List<HourRow>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    interface HourRow
    class HoursRow (val time:String , val temp:String, val hourlyImage: Int) :
        HourRow

    class HourDaysViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val timeDay: TextView = itemView.findViewById(R.id.timetv)
        val timeTemp: TextView = itemView.findViewById(R.id.timetemp)
        val timeImage: ImageView = itemView.findViewById(R.id.timeImage)
    }

    override fun getItemCount() = rows.count()

    override fun getItemViewType(position: Int): Int =
        when (rows[position]) {
            is HoursRow -> TYPE_HOUR
            else -> throw IllegalArgumentException()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_HOUR -> HourDaysViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.hour_status_itemlayout, parent, false)
        )
        else -> throw IllegalArgumentException()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            TYPE_HOUR -> onBindHourDay(holder, rows[position] as HoursRow)
            else -> throw IllegalArgumentException()
        }

    private fun onBindHourDay(holder: RecyclerView.ViewHolder, row: HoursRow) {
        val itemRow = holder as HourDaysViewHolder
        itemRow.timeDay.text = row.time
        itemRow.timeTemp.text = row.temp
        itemRow.timeImage.setImageResource(row.hourlyImage)
    }

    companion object {
        private const val TYPE_HOUR = 0
    }
}