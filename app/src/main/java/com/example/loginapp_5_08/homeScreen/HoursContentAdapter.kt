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
import com.example.loginapp_5_08.homeScreen.openWeatherApi.response.future.FutureWeatherResponseOWM
import kotlinx.android.synthetic.main.layout_hour_details.view.*

class HoursContentAdapter( private val futureWeatherResponseData: FutureWeatherResponseOWM, private val homeFragment: HomeFragment, private val tempScale:Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class HourDaysViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val timeDay: TextView = itemView.timetv
        val timeTemp: TextView = itemView.timetemp
        val timeImage: ImageView = itemView.timeImage
    }

    override fun getItemCount() = 5

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_HOUR -> HourDaysViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_hour_details, parent, false)
        )
        else -> throw IllegalArgumentException()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            TYPE_HOUR -> onBindHourDay(holder, position)
            else -> throw IllegalArgumentException()
        }

    @SuppressLint("SetTextI18n")
    private fun onBindHourDay(holder: RecyclerView.ViewHolder, pos:Int) {
        val itemRow = holder as HourDaysViewHolder
        itemRow.timeDay.text = futureWeatherResponseData.list[pos].dtTxt.substring(11,16)

        if (tempScale == 0)
        {
            itemRow.timeTemp.text = ((futureWeatherResponseData.list[pos].main.tempMax.toInt()-273).toString()) + "° C"
        }
        else{
            //1.8*((futureWeatherResponseData.list[0].main.temp) - 273.15) + 32
            itemRow.timeTemp.text = (1.8*(futureWeatherResponseData.list[pos].main.tempMax-273)+32).toInt().toString() + "° F"
        }

        Glide.with(homeFragment).load("https://openweathermap.org/img/wn/"+futureWeatherResponseData.list[pos+(pos)].weather[0].icon+"@2x.png").into(itemRow.timeImage)
    }

    companion object {
        private const val TYPE_HOUR = 0
    }

    //class HoursRow (val time:String , val temp:String, val hourlyImage: Int)
}