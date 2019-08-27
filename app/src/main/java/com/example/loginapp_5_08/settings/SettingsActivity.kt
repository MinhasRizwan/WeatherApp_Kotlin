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
    private var tempUnit : Int = 0
    private var cityChecked : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setSupportActionBar(findViewById(R.id.toolbar))

        sharedPreference = SharedPreference(this)

        getSavedValues()

        val tempScaleSpinner = setAdapter()

        dropdownScale.setSelection(tempUnit)
        checkedTextView.isChecked = cityChecked

        tempScaleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                sharedPreference.save("tempScale", position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun setAdapter() : Spinner
    {
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

        return spinner
    }

    private fun getSavedValues()
    {
        tempUnit = sharedPreference.getValueInt("tempScale")
        cityChecked = sharedPreference.getValueBoolean("currentLocation",false)
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

    fun checkWeatherStatusThoughAPI(view: View){

    }
}
