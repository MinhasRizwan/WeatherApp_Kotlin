package com.example.loginapp_5_08.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp_5_08.R
import kotlinx.android.synthetic.main.fragment_home_screen.*
import kotlin.String as String1

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_home_screen,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weatherData = WeatherData()

        recycler_view.adapter = CurrentStatusContentAdapter(getSampleRows(1), weatherData)

        recycler_view.layoutManager = LinearLayoutManager(this.context)
    }

    companion object{
        //private const val inputData = "dummy"

        fun newInstance(input: String1): HomeFragment{
            val args = Bundle()
            //args.putSerializable(inputData, input)

            val frag = HomeFragment()
            //frag.arguments = args

            return frag
        }
    }

    private fun getSampleRows(numSections: Int): List<CurrentStatusContentAdapter.WeatherReports> {
        val rows = mutableListOf<CurrentStatusContentAdapter.WeatherReports>()
        for (i in 1..numSections) {
            rows.add(
                CurrentStatusContentAdapter.WeatherReports.StatusRow(
                    "24° C",
                    "Lahore",
                    "Partly Cloudy",
                    R.drawable.partlysunny
                )
            )
            rows.add(CurrentStatusContentAdapter.WeatherReports.TodayRow)
            rows.add(CurrentStatusContentAdapter.WeatherReports.WeekdaysRow)
            //rows.add(ContentAdapter.DummyRow("Sunday","11/08/2019"))
        }
        return rows
    }
}