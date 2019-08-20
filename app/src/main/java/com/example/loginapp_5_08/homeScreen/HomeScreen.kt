package com.example.loginapp_5_08.homeScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp_5_08.R
import com.example.loginapp_5_08.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        setSupportActionBar(findViewById(R.id.toolbar))

        //set adapter
        recycler_view.adapter = CurrentStatusContentAdapter(getSampleRows(1))

        //create layout manager
        recycler_view.layoutManager = LinearLayoutManager(this)
        //recycler_view.layoutManager

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.tool_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        return when (item.itemId){
            R.id.setting ->{
                val clickintent = Intent(this, SettingsActivity::class.java)
                startActivity(clickintent)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getSampleRows(numSections: Int): List<CurrentStatusContentAdapter.IRow> {
        val rows = mutableListOf<CurrentStatusContentAdapter.IRow>()
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
