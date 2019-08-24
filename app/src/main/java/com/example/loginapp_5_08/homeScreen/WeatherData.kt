package com.example.loginapp_5_08.homeScreen

import com.example.loginapp_5_08.R

class WeatherData {

    val daysRows: List<DaysContentAdapter.WeekDaysRow> = getSampleRowForDays(1)
    val hoursRows: List<HoursContentAdapter.HoursRow> = getSampleRowForHou(1)

    private fun getSampleRowForDays(numSections: Int): List<DaysContentAdapter.WeekDaysRow> {
        val rows = mutableListOf<DaysContentAdapter.WeekDaysRow>()
        for (i in 1..numSections) {
            rows.add(
                DaysContentAdapter.WeekDaysRow(
                    "Fri",
                    "24° C - 32° C",
                    R.drawable.sunny
                )
            )
            rows.add(
                DaysContentAdapter.WeekDaysRow(
                    "Sat",
                    "27° C - 35° C",
                    R.drawable.partlysunny
                )
            )
            rows.add(
                DaysContentAdapter.WeekDaysRow(
                    "Sun",
                    "29° C - 39° C",
                    R.drawable.sunny
                )
            )
            rows.add(
                DaysContentAdapter.WeekDaysRow(
                    "Mon",
                    "21° C - 22° C",
                    R.drawable.cloudy
                )
            )
            rows.add(
                DaysContentAdapter.WeekDaysRow(
                    "Tue",
                    "24° C - 32° C",
                    R.drawable.nightcloudy
                )
            )
        }
        return rows
    }

    private fun getSampleRowForHou(numSections: Int): List<HoursContentAdapter.HoursRow> {
        val rows = mutableListOf<HoursContentAdapter.HoursRow>()
        for (i in 1..numSections) {
            rows.add(
                HoursContentAdapter.HoursRow(
                    "01 AM",
                    "24° C",
                    R.drawable.nightcloudy
                )
            )
            rows.add(
                HoursContentAdapter.HoursRow(
                    "05 AM",
                    "21° C",
                    R.drawable.cloudy
                )
            )
            rows.add(
                HoursContentAdapter.HoursRow(
                    "09 AM",
                    "23° C",
                    R.drawable.sunny
                )
            )
            rows.add(
                HoursContentAdapter.HoursRow(
                    "01 PM",
                    "29° C",
                    R.drawable.sunny
                )
            )
            rows.add(
                HoursContentAdapter.HoursRow(
                    "05 PM",
                    "22° C",
                    R.drawable.partlysunny
                )
            )
        }
        return rows
    }
}