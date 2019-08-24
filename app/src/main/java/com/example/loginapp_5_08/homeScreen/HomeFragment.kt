package com.example.loginapp_5_08.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp_5_08.R
import com.example.loginapp_5_08.data.ApixuWeatherApiService
import com.example.loginapp_5_08.data.OpenWeatherApiService
import com.example.loginapp_5_08.data.response.future.FutureWeatherResponseData
import com.example.loginapp_5_08.data.response.response2.current.CurrentWeatherResponseOWM
import com.example.loginapp_5_08.data.response.response2.future.FutureWeatherResponseOWM
import kotlinx.android.synthetic.main.fragment_home_screen.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.android.Main
import kotlinx.coroutines.launch
import kotlin.String as String1

class HomeFragment : Fragment() {

    //lateinit var currentWeatherResponseData: CurrentWeatherResponseData
    private lateinit var futureWeatherResponseOWM: FutureWeatherResponseOWM

    private lateinit var currentWeatherResponseOWM: CurrentWeatherResponseOWM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val weatherData = WeatherData()

        val apiServiceOWM = OpenWeatherApiService()
        //layoutInflater.inflate(R.layout.fragment_home_screen, constraintLayout)

        GlobalScope.launch(Dispatchers.Main) {

            //currentWeatherResponseData = apiService.getCurrentWeather("London").await()
            futureWeatherResponseOWM = apiServiceOWM.getWeekWeather("London,uk").await()
            currentWeatherResponseOWM = apiServiceOWM.getCurrentWeather("London,uk").await()

            recycler_view.adapter = CurrentStatusContentAdapter(getSampleRows(1) ,  weatherData, futureWeatherResponseOWM, currentWeatherResponseOWM, this@HomeFragment)

            recycler_view.layoutManager = LinearLayoutManager(this@HomeFragment.context)

            //apiResponse.text = "\n Weather Condition : " + currentWeatherResponseData.currentWeatherData.condition.toString() +
            //        "\n Temperature (C) : " + currentWeatherResponseData.currentWeatherData.tempC.toString() +
            //        "\n Last Updated : " + currentWeatherResponseData.currentWeatherData.lastUpdated
        }

        return inflater.inflate(
            R.layout.fragment_home_screen,
            container, false
        )

    }

    companion object{
        //private const val inputData = "dummy"

        fun newInstance(input: String1): HomeFragment{
            //val args = Bundle()
            //args.putSerializable(inputData, input)

            val frag = HomeFragment()
            //frag.arguments = args

            return frag
        }
    }

    private fun getSampleRows(numSections: Int): List<CurrentStatusContentAdapter.WeatherReports> {
        val rows = mutableListOf<CurrentStatusContentAdapter.WeatherReports>()

        for (i in 1..numSections) {
            //val img: ImageView =

            rows.add(CurrentStatusContentAdapter.WeatherReports.StatusRow)
            rows.add(CurrentStatusContentAdapter.WeatherReports.TodayRow)
            rows.add(CurrentStatusContentAdapter.WeatherReports.WeekdaysRow)
            //Glide.with(this).load("https://s3.amazonaws.com/appsdeveloperblog/Micky.jpg").into(currentStatusLayout.weatherNowImage)
            //rows.add(ContentAdapter.DummyRow("Sunday","11/08/2019"))
        }
        return rows
    }
}