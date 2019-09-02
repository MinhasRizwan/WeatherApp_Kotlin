package com.example.loginapp_5_08.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp_5_08.R
import com.example.loginapp_5_08.data.response.response.future.FutureWeatherResponseOWM
import kotlinx.android.synthetic.main.fragment_home_screen.*
import com.example.loginapp_5_08.data.WeatherViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.loginapp_5_08.ViewPager.Cities
import com.example.loginapp_5_08.ViewPager.CityHelper


class HomeFragment (private val arraySize:Int): Fragment() {

    private lateinit var weatherViewModel: WeatherViewModel

    private lateinit var futureWeatherResponseOWM: FutureWeatherResponseOWM

    private lateinit var futureWeatherObserver: Observer<FutureWeatherResponseOWM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        weatherViewModel.init()

        Toast.makeText(activity,arraySize.toString(), Toast.LENGTH_SHORT).show()

        futureWeatherObserver = Observer { newWeather ->
            // Update the UI
            futureWeatherResponseOWM = newWeather

            recycler_view.adapter = CurrentStatusContentAdapter(getSampleRows(1) , futureWeatherResponseOWM, this@HomeFragment)

            recycler_view.layoutManager = LinearLayoutManager(this@HomeFragment.context)

        }

        weatherViewModel.getFutureWeatherRepository()!!.observe(this, futureWeatherObserver)

        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_home_screen,
            container, false
        )
    }

    companion object{
        //private const val inputData = "dummy"

        //fun newInstance(movie: Cities): HomeFragment{
        fun newInstance(str : String , size:Int): HomeFragment{
            //val args = Bundle()
            //args.putSerializable(inputData, input)

            val frag = HomeFragment(size)
            //frag.arguments = args

            return frag
        }
    }

    private fun getSampleRows(numSections: Int): List<CurrentStatusContentAdapter.WeatherReports> {
        val rows = mutableListOf<CurrentStatusContentAdapter.WeatherReports>()

        for (i in 1..numSections) {

            rows.add(CurrentStatusContentAdapter.WeatherReports.StatusRow)
            rows.add(CurrentStatusContentAdapter.WeatherReports.TodayRow)
            rows.add(CurrentStatusContentAdapter.WeatherReports.WeekdaysRow)
        }
        return rows
    }
}

