package com.example.loginapp_5_08.homeScreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loginapp_5_08.R
import com.example.loginapp_5_08.data.response.response.future.FutureWeatherResponseOWM
import kotlinx.android.synthetic.main.layout_week_details.view.*

class DaysContentAdapter(private val futureWeatherResponseData: FutureWeatherResponseOWM, private val homeFragment: HomeFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class WeekDaysViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val todayDay:TextView = itemView.weekDay
        val rangeTemp:TextView = itemView.rangeTemp
        val imageOfDay: ImageView = itemView.weekdayImage
    }

    override fun getItemCount() = 5

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_WEEKDAY -> WeekDaysViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_week_details, parent, false)
        )
        else -> throw IllegalArgumentException()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            TYPE_WEEKDAY -> onBindWeekDay(holder, position)
            else -> throw IllegalArgumentException()

        }

    @SuppressLint("SetTextI18n")
    private fun onBindWeekDay(holder: RecyclerView.ViewHolder, pos: Int) {
        val dayRow = holder as WeekDaysViewHolder
        //dayRow.todayDay.text = row.dayName

        if ((pos+(pos * 7) <= futureWeatherResponseData.list.size)) {
            dayRow.todayDay.text = futureWeatherResponseData.list[pos+(pos * 7)].dtTxt.substring(6,16)
            //dayRow.rangeTemp.text = row.minmaxTemp
            dayRow.rangeTemp.text = (futureWeatherResponseData.list[pos+(pos * 7)].main.tempMax-273.85).toInt().toString() + "° C"
            //dayRow.imageOfDay.setImageResource(R.drawable.sunny)

            Glide.with(homeFragment).load("https://openweathermap.org/img/wn/"+futureWeatherResponseData.list[pos+(pos * 7)].weather[0].icon+"@2x.png").into(dayRow.imageOfDay)

        }
    }

    companion object {
        private const val TYPE_WEEKDAY = 0
    }
}