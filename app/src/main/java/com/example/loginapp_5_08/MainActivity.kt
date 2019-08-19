package com.example.loginapp_5_08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.SharedPreferences

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginFragment = MyFragment()

        val manager = supportFragmentManager

        val transaction = manager.beginTransaction()

        transaction.replace(R.id.fragment_container, loginFragment)
        transaction.addToBackStack(null)

        transaction.commit()
    }
}