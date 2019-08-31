package com.example.loginapp_5_08.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.loginapp_5_08.R
import com.example.loginapp_5_08.settings.Database.UserPref
import com.example.loginapp_5_08.settings.vewModel.UserPrefViewModel
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity(){

    private var tempUnit : Int = 0
    private var cityChecked : Boolean = false
    private lateinit var userPrefViewModel: UserPrefViewModel
    private lateinit var prefObserver: Observer<UserPref>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setSupportActionBar(findViewById(R.id.toolbar))

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        val tempScaleSpinner = setAdapter()

        // Get a new or existing ViewModel from the ViewModelProvider.
        userPrefViewModel = ViewModelProviders.of(this).get(UserPrefViewModel::class.java)

        prefObserver = Observer { newPref ->
            // Update the UI
            cityChecked = newPref.locationCheck
            tempUnit = newPref.tempScale

        }



        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        userPrefViewModel.pref.observe(this, prefObserver)

        dropdownScale.setSelection(tempUnit)
        checkedTextView.isChecked = cityChecked


        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        //getSavedValues()



        tempScaleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                //sharedPreference.save("tempScale", position)
                userPrefViewModel.insertPref(position,cityChecked)
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

/*    private fun getSavedValues()
    {
        tempUnit = sharedPreference.getValueInt("tempScale")
        cityChecked = sharedPreference.getValueBoolean("currentLocation",false)
    }
*/
    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.checkedTextView -> {
                    if (checked) {
                        userPrefViewModel.insertPref(tempUnit,true)
                        //sharedPreference.save("currentLocation",true)
                    } else {
                        //sharedPreference.save("currentLocation", false)
                    }
                }
            }
        }
    }

    fun checkWeatherStatusThoughAPI(view: View){

    }
}
