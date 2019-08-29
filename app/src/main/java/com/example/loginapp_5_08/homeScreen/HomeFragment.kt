package com.example.loginapp_5_08.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp_5_08.R
import com.example.loginapp_5_08.data.OpenWeatherApiService
import com.example.loginapp_5_08.data.response.response.current.CurrentWeatherResponseOWM
import com.example.loginapp_5_08.data.response.response.future.FutureWeatherResponseOWM
import kotlinx.android.synthetic.main.fragment_home_screen.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.loginapp_5_08.data.WeatherViewModel
import androidx.lifecycle.ViewModelProviders


class HomeFragment : Fragment() {

    lateinit var weatherViewModel: WeatherViewModel

    private lateinit var futureWeatherResponseOWM: FutureWeatherResponseOWM

    private lateinit var currentWeatherResponseOWM: CurrentWeatherResponseOWM

    private lateinit var currentWeatherObserver: Observer<CurrentWeatherResponseOWM>

    private lateinit var futureWeatherObserver: Observer<FutureWeatherResponseOWM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        weatherViewModel.init()

        // Create the observer which updates the UI.
        currentWeatherObserver = Observer<CurrentWeatherResponseOWM> { newWeather ->
            // Update the UI
            currentWeatherResponseOWM = newWeather
        }

        futureWeatherObserver = Observer<FutureWeatherResponseOWM> { newwWeather ->
            // Update the UI
            futureWeatherResponseOWM = newwWeather
        }

        weatherViewModel.getCurrentWeatherRepository()!!.observe(this, currentWeatherObserver)
        //weatherViewModel.getFutureWeatherRepository()!!.observe(this, futureWeatherObserver)


        val apiServiceOWM = OpenWeatherApiService()

        GlobalScope.launch(Dispatchers.Main) {

            futureWeatherResponseOWM = apiServiceOWM.getWeekWeather("London,uk").await()

            recycler_view.adapter = CurrentStatusContentAdapter(getSampleRows(1) , futureWeatherResponseOWM, currentWeatherResponseOWM, this@HomeFragment)

            recycler_view.layoutManager = LinearLayoutManager(this@HomeFragment.context)

        }

        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_home_screen,
            container, false
        )
    }

    companion object{
        //private const val inputData = "dummy"

        fun newInstance(input: String): HomeFragment{
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

            rows.add(CurrentStatusContentAdapter.WeatherReports.StatusRow)
            rows.add(CurrentStatusContentAdapter.WeatherReports.TodayRow)
            rows.add(CurrentStatusContentAdapter.WeatherReports.WeekdaysRow)
        }
        return rows
    }
}

