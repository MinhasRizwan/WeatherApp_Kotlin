package com.example.loginapp_5_08

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HoursContentAdapter(private val rows: List<hourRow>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    interface hourRow
    class HoursRow (val time:String , val temp:String, val hourlyImage: Int) : hourRow

    class hourDaysViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val timeDay: TextView = itemView.findViewById(R.id.timetv)
        val timeTemp: TextView = itemView.findViewById(R.id.timetemp)
        val timeImage: ImageView = itemView.findViewById(R.id.timeImage)
    }

    override fun getItemCount() = rows.count()

    override fun getItemViewType(position: Int): Int =
        when (rows[position]) {
            is HoursContentAdapter.HoursRow -> HoursContentAdapter.TYPE_HOUR
            else -> throw IllegalArgumentException()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        HoursContentAdapter.TYPE_HOUR -> HoursContentAdapter.hourDaysViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.hour_status_itemlayout, parent, false)
        )
        else -> throw IllegalArgumentException()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            HoursContentAdapter.TYPE_HOUR -> onBindHourDay(holder, rows[position] as HoursContentAdapter.HoursRow)
            else -> throw IllegalArgumentException()
        }

    private fun onBindHourDay(holder: RecyclerView.ViewHolder, row: HoursContentAdapter.HoursRow) {
        val itemRow = holder as HoursContentAdapter.hourDaysViewHolder
        itemRow.timeDay.text = row.time
        itemRow.timeTemp.text = row.temp
        itemRow.timeImage.setImageResource(row.hourlyImage)
    }

    companion object {
        private const val TYPE_HOUR = 0
    }
}