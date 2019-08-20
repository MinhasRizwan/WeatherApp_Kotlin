package com.example.loginapp_5_08.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.loginapp_5_08.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity(){

    private lateinit var sharedPreferences : SharedPreferences
    lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setSupportActionBar(findViewById(R.id.toolbar))

        sharedPreferences =  getSharedPreferences("TEMP_INFO", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.apply()


        val myint = sharedPreferences.getInt("temp scale",0)
        val cityChecked : Boolean = sharedPreferences.getBoolean("city",false)

        if (savedInstanceState != null)
        {
            //dropdownScale.setSelection(sharedPreferences.getInt("temp scale",0))
        }
        //dropdownScale.setSelection(sharedPreferences.getInt("temp scale",0))

        val spinner: Spinner = findViewById(R.id.dropdownScale)


        // ArrayAdapter (string array and a default spinner layout)
        ArrayAdapter.createFromResource(
            this,
            R.array.temp_scale,
            android.R.layout.simple_spinner_item
        ).also { adapter ->

            // layout (use when the list of choices appears)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // adapter to the spinner
            spinner.adapter = adapter
        }

        dropdownScale.setSelection(myint)
        checkedTextView.isChecked = cityChecked

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                editor.putInt("temp scale",position)
                editor.apply()

                //Toast.makeText(this@Settings_Screen, "Temperature Scale:" + parent.getItemAtPosition(position) , Toast.LENGTH_SHORT).show()
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
                        editor.putBoolean("city",true)
                        editor.apply()

                        Toast.makeText(this, "Location : Checked", Toast.LENGTH_SHORT).show()
                    } else {
                        editor.putBoolean("city",false)
                        editor.apply()

                        Toast.makeText(this, "Location : Un-Checked", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}
