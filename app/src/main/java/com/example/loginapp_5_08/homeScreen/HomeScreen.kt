package com.example.loginapp_5_08.homeScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.example.loginapp_5_08.R
import com.example.loginapp_5_08.ViewPager.CityHelper
import com.example.loginapp_5_08.ViewPager.WeatherPagerAdapter
import com.example.loginapp_5_08.settings.SettingsActivity
import com.example.loginapp_5_08.shared.SharedPreference

class HomeScreen : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: WeatherPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        //???
        val movies = CityHelper.getMoviesFromJson("cities.json", this)

        val cities = ArrayList<String>()
        cities.add("London")
        cities.add("East London")
        cities.add("Hurzuf")

        val movieSize = movies.size

        viewPager = findViewById(R.id.viewPager)

        //?
        pagerAdapter = WeatherPagerAdapter(supportFragmentManager, movies, movieSize)
        viewPager.adapter = pagerAdapter

        viewPager.currentItem = pagerAdapter.count / 2

        setSupportActionBar(findViewById(R.id.toolbar))

        //createHomeFragment()
    }
/*
    private fun createHomeFragment(){

        val homeFragment = HomeFragment.newInstance("Hello")

        val manager = supportFragmentManager
        val fragm = manager.findFragmentById(R.id.rv_fragment_container)

        if (fragm == null)
        {
            val transaction = manager.beginTransaction()

            transaction.replace(R.id.rv_fragment_container, homeFragment)
            transaction.commit()
        }
    }
*/
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
}