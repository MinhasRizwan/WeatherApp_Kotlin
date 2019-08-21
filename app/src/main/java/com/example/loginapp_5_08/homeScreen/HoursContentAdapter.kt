package com.example.loginapp_5_08.homeScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp_5_08.R
import kotlinx.android.synthetic.main.layout_hour_details.view.*

class HoursContentAdapter(private val rows: List<HoursRow>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class HourDaysViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val timeDay: TextView = itemView.timetv
        val timeTemp: TextView = itemView.timetemp
        val timeImage: ImageView = itemView.timeImage
    }

    override fun getItemCount() = rows.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_HOUR -> HourDaysViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_hour_details, parent, false)
        )
        else -> throw IllegalArgumentException()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            TYPE_HOUR -> onBindHourDay(holder, rows[position])
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

    class HoursRow (val time:String , val temp:String, val hourlyImage: Int)

}