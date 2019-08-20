package com.example.loginapp_5_08.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginapp_5_08.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginFragment = LoginFragment()

        val manager = supportFragmentManager

        val transaction = manager.beginTransaction()

        transaction.replace(R.id.fragment_container, loginFragment)
        transaction.addToBackStack(null)

        transaction.commit()
    }
}