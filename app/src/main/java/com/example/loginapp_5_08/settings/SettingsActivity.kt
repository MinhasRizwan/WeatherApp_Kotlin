package com.example.loginapp_5_08.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.loginapp_5_08.R
import com.example.loginapp_5_08.login.LoginFragment
import com.example.loginapp_5_08.settings.manageCities.ManageCityDialogFragment
import com.example.loginapp_5_08.settings.vewModel.SettingsFragment
import com.example.loginapp_5_08.shared.SharedPreference
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setSupportActionBar(findViewById(R.id.toolbar))

        createFragment()
    }

    private fun createFragment()
    {
        val settingFragment = SettingsFragment.newInstance()

        val manager = supportFragmentManager
        val frag: Fragment? = manager.findFragmentById(R.id.fragment_container)

        //if (savedInstancState == null)
        if (frag == null)
        {
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.fragment_container, settingFragment)
            transaction.commit()
        }
    }
}
