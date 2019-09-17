package com.example.loginapp_5_08.homeScreen.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.loginapp_5_08.settings.roomDB.City
import com.example.loginapp_5_08.settings.roomDB.CityRepository
import com.example.loginapp_5_08.settings.roomDB.CityRoomDatabase
import com.example.loginapp_5_08.shared.SharedPreference
import kotlinx.coroutines.launch

//View Model for Home Activity

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    var currentLocCheck = false
    var addedCity= ArrayList<String>()
    private val repository: CityRepository
    var allCities: LiveData<List<City>>? = null

    fun getCurrentLocationCheck(sharedPreferences:SharedPreference):Boolean{
        currentLocCheck = sharedPreferences.getValueBoolean("currentLocation",false)
        return currentLocCheck
    }

    fun getAddedCities(sharedPreferences: SharedPreference):ArrayList<City>{

        addedCity = sharedPreferences.getArrayList("addedCitiesList")

        val addedCities = ArrayList<City>()
        for (i in addedCity)
        {
            addedCities.add(City(0, i, "Pk", 1.1, 2.2 ))
        }

        return addedCities
    }

    // The implementation of insert() is completely hidden from the UI.
    // We don't want insert to block the main thread, so we're launching a new
    // coroutine. ViewModels have a coroutine scope based on their lifecycle called
    // viewModelScope which we can use here.
    fun insert(city: City) = viewModelScope.launch {
        repository.insert(city)

    }

    fun delete(city: City) = viewModelScope.launch {
        repository.delete(city)

    }

    init {
        val citiesDao = CityRoomDatabase.getDatabase(application, viewModelScope).cityDao()
        repository = CityRepository(citiesDao)
        allCities = repository.allCities

    }


    //Function for current Location
    fun connectGoogleApi(){
        //repository.connectGoogleApiClient()
    }

    fun disconnectGoogleApi(){
        //repository.disconnectGoogleApiClient()
    }
}