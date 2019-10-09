package com.example.loginapp_5_08.homeScreen

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp_5_08.R
import com.example.loginapp_5_08.homeScreen.openWeatherApi.response.future.FutureWeatherResponseOWM
import kotlinx.android.synthetic.main.fragment_home_screen.*
import com.example.loginapp_5_08.homeScreen.viewModels.WeatherViewModel
import com.example.loginapp_5_08.settings.SettingsActivity
import com.example.loginapp_5_08.settings.roomDB.City
import com.example.loginapp_5_08.shared.SharedPreference


class HomeFragment (private val city: City, private val sharedPreference: SharedPreference): Fragment() {

    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var futureWeatherResponseOWM: FutureWeatherResponseOWM
    private lateinit var futureWeatherObserver: Observer<FutureWeatherResponseOWM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        if (isInternetAvailable(this@HomeFragment.context!!)) {

            weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
            weatherViewModel.init(city)

            //Toast.makeText(activity,city, Toast.LENGTH_SHORT).show()

            Toast.makeText(activity,city.name, Toast.LENGTH_SHORT).show()
            futureWeatherObserver = Observer { newWeather ->

                val tempScale = sharedPreference.getValueInt("tempScale")
                // Update the UI
                futureWeatherResponseOWM = newWeather
                recycler_view.adapter = CurrentStatusContentAdapter(getSampleRows(1) , futureWeatherResponseOWM, this@HomeFragment, tempScale)
                recycler_view.layoutManager = LinearLayoutManager(this@HomeFragment.context)
            }

            weatherViewModel.getFutureWeatherRepository()!!.observe(this, futureWeatherObserver)


        } else {
            Toast.makeText(context, "No Internet Available", Toast.LENGTH_SHORT).show()

            val intent = Intent(activity?.applicationContext, SettingsActivity::class.java)
            startActivity(intent)
        }

        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_home_screen,
            container, false
        )

    }

    companion object{
        //private const val inputData = "dummy"
        //fun newInstance(movie: Cities): HomeFragment{
        fun newInstance( city:City, sharedPreference: SharedPreference): HomeFragment{
            //val args = Bundle()
            //args.putSerializable(inputData, input)
            //frag.arguments = args
            return HomeFragment( city,sharedPreference)
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

    @Suppress("DEPRECATION")
    private fun isInternetAvailable(context: Context): Boolean {
        val mConMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return (mConMgr.activeNetworkInfo != null
                && mConMgr.activeNetworkInfo!!.isAvailable
                && mConMgr.activeNetworkInfo!!.isConnected)
    }

}

