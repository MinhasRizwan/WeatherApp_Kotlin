package com.example.loginapp_5_08.settings.roomDB

import android.app.Application
import android.widget.ArrayAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.loginapp_5_08.homeScreen.ViewPager.Cities
import com.example.loginapp_5_08.homeScreen.ViewPager.CityHelper
import android.R
import kotlinx.coroutines.launch

// Class extends AndroidViewModel & application as a parameter.
class CityViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: CityRepository
    // LiveData gives us updated cities when they change.
    var allCities: LiveData<List<City>>? = null

    lateinit var cities:ArrayList<Cities>
    private var citiesSize = 0

    init {
        // Gets reference to CityDao from CityRoomDatabase to construct
        // the correct CityRepository.
        val citiesDao = CityRoomDatabase.getDatabase(application, viewModelScope).cityDao()
        repository = CityRepository(citiesDao)
        allCities = repository.allCities
    }

    fun getAllCities(): ArrayList<Cities>{
        //reading data from cities.json
        cities = CityHelper.getCitiesFromJson("cities.json", this.getApplication())
        citiesSize = cities.size

        return cities
    }

    fun getListAdapterAllCities(): ArrayAdapter<String> {
        val listViewAdapterContent:ArrayList<String> = ArrayList()

        for (i in 0 until citiesSize) {
            listViewAdapterContent.add(cities[i].name)
        }

        val listAdapter = ArrayAdapter(this.getApplication(), R.layout.simple_list_item_1, R.id.text1, listViewAdapterContent)
        return listAdapter
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

}