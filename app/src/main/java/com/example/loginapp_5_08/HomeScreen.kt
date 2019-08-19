package com.example.loginapp_5_08

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        setSupportActionBar(findViewById(R.id.toolbar))

        //set adapter
        recycler_view.adapter = ContentAdapter(getSampleRows(1))

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
                var clickintent = Intent(this, Settings_Screen::class.java)
                startActivity(clickintent)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getSampleRows(numSections: Int): List<ContentAdapter.IRow> {
        val rows = mutableListOf<ContentAdapter.IRow>()
        for (i in 1..numSections) {
            rows.add(ContentAdapter.statusRow("24° C","Lahore","Partly Cloudy", R.drawable.partlysunny))
            rows.add(ContentAdapter.todayRow())
            rows.add(ContentAdapter.weekdaysRow())
            //rows.add(ContentAdapter.DummyRow("Sunday","11/08/2019"))
        }
        return rows
    }
}
