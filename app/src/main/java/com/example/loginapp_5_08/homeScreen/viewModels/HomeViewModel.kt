package com.example.loginapp_5_08.homeScreen.viewModels

import androidx.lifecycle.ViewModel
import com.example.loginapp_5_08.shared.SharedPreference

class HomeViewModel : ViewModel() {

    var currentLocCheck = false
    lateinit var sharedPreferences: SharedPreference
    var addedCity= ArrayList<String>()

    fun getCurrentLocationCheck():Boolean{
        currentLocCheck = sharedPreferences.getValueBoolean("currentLocation",false)
        return currentLocCheck
    }

    fun getAddedCities():ArrayList<String>{

        addedCity = sharedPreferences.getArrayList("addedCitiesList")

        return addedCity
    }

    fun addCurrentCity():ArrayList<String>{
        addedCity.add(0,"current")
        return addedCity
    }

    fun init(sharedPreference: SharedPreference) {
        sharedPreferences = sharedPreference
    }
}