package com.example.loginapp_5_08.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp_5_08.R
import kotlinx.android.synthetic.main.home_screen_fragment.*
import kotlin.String as String1

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.home_screen_fragment,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.adapter = CurrentStatusContentAdapter(getSampleRows(1))

        recycler_view.layoutManager = LinearLayoutManager(this.context)
    }

    companion object{
        private const val inputData = "dummy"

        fun newInstance(input: String1): HomeFragment{

            val args = Bundle()
            args.putSerializable(inputData, input)

            val frag = HomeFragment()
            frag.arguments = args

            return frag
        }
    }

    private fun getSampleRows(numSections: Int): List<CurrentStatusContentAdapter.WeatherReportsRow> {
        val rows = mutableListOf<CurrentStatusContentAdapter.WeatherReportsRow>()
        for (i in 1..numSections) {
            rows.add(
                CurrentStatusContentAdapter.StatusRow(
                    "24° C",
                    "Lahore",
                    "Partly Cloudy",
                    R.drawable.partlysunny
                )
            )
            rows.add(CurrentStatusContentAdapter.TodayRow())
            rows.add(CurrentStatusContentAdapter.WeekdaysRow())
            //rows.add(ContentAdapter.DummyRow("Sunday","11/08/2019"))
        }
        return rows
    }
}