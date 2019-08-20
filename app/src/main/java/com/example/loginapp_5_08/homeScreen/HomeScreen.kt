package com.example.loginapp_5_08.homeScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.loginapp_5_08.R
import com.example.loginapp_5_08.settings.SettingsActivity

class HomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        setSupportActionBar(findViewById(R.id.toolbar))

        createHomeFragment()
    }

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