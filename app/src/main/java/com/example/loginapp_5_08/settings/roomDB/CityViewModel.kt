package com.example.loginapp_5_08.settings.roomDB

import android.R
import android.app.Application
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.loginapp_5_08.homeScreen.ViewPager.Cities
import com.example.loginapp_5_08.homeScreen.ViewPager.CityHelper
import com.example.loginapp_5_08.shared.SharedPreference
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class CityViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: CityRepository
    // LiveData gives us updated words when they change.
    var allCities: LiveData<List<City>>? = null

    lateinit var cities:ArrayList<Cities>
    var citiesSize = 0


    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val citiesDao = CityRoomDatabase.getDatabase(application, viewModelScope).cityDao()
        repository = CityRepository(citiesDao)
        allCities = repository.allCities
    }

    //
    fun getAllCities(): ArrayList<Cities>{
        cities = CityHelper.getCitiesFromJson("cities.json", this.getApplication())
        citiesSize = cities.size

        return cities
    }

    fun getListAdapterAllCities(): ArrayAdapter<String> {
        val listViewAdapterContent:ArrayList<String> = ArrayList()

        for (i in 0..citiesSize-1) {
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