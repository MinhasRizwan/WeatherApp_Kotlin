package com.example.loginapp_5_08.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp_5_08.R
import com.example.loginapp_5_08.data.ApixuWeatherApiService
import com.example.loginapp_5_08.data.response.future.FutureWeatherResponseData
import kotlinx.android.synthetic.main.fragment_home_screen.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.android.Main
import kotlinx.coroutines.launch
import kotlin.String as String1

class HomeFragment : Fragment() {

    //lateinit var currentWeatherResponseData: CurrentWeatherResponseData
    private lateinit var futureWeatherResponseData: FutureWeatherResponseData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val weatherData = WeatherData()
        val apiService = ApixuWeatherApiService()

        //layoutInflater.inflate(R.layout.fragment_home_screen, constraintLayout)

        GlobalScope.launch(Dispatchers.Main) {

            //currentWeatherResponseData = apiService.getCurrentWeather("London").await()
            futureWeatherResponseData = apiService.getWeekWeather("London").await()

            recycler_view.adapter = CurrentStatusContentAdapter(getSampleRows(1, futureWeatherResponseData) ,  weatherData, futureWeatherResponseData, this@HomeFragment)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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

    private fun getSampleRows(numSections: Int, futureData: FutureWeatherResponseData): List<CurrentStatusContentAdapter.WeatherReports> {
        val rows = mutableListOf<CurrentStatusContentAdapter.WeatherReports>()

        for (i in 1..numSections) {
            //val img: ImageView =

            rows.add(
                CurrentStatusContentAdapter.WeatherReports.StatusRow(
                    futureData.current.tempC.toInt().toString() + " C",
                    futureData.location.name,
                    futureData.current.condition.text,
                    R.drawable.sunny
                )
            )
            rows.add(CurrentStatusContentAdapter.WeatherReports.TodayRow)
            rows.add(CurrentStatusContentAdapter.WeatherReports.WeekdaysRow)
            //Glide.with(this).load("https://s3.amazonaws.com/appsdeveloperblog/Micky.jpg").into(currentStatusLayout.weatherNowImage)
            //rows.add(ContentAdapter.DummyRow("Sunday","11/08/2019"))
        }
        return rows
    }
}