package com.example.loginapp_5_08.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.loginapp_5_08.R
import com.example.loginapp_5_08.shared.SharedPreference
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity(){

    private lateinit var sharedPreference : SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setSupportActionBar(findViewById(R.id.toolbar))

        sharedPreference = SharedPreference(this)

        val myint = sharedPreference.getValueInt("tempScale")
        val cityChecked : Boolean = sharedPreference.getValueBoolean("currentLocation",false)

        val spinner: Spinner = dropdownScale

        ArrayAdapter.createFromResource(
            this,
            R.array.temp_scale,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // adapter to the spinner
            spinner.adapter = adapter
        }

        dropdownScale.setSelection(myint)
        checkedTextView.isChecked = cityChecked

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                sharedPreference.save("tempScale", position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.checkedTextView -> {
                    if (checked) {
                        sharedPreference.save("currentLocation",true)
                    } else {
                        sharedPreference.save("currentLocation", false)
                    }
                }
            }
        }
    }
}
