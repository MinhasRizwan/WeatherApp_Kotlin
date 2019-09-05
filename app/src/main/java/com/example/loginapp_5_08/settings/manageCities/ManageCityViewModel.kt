package com.example.loginapp_5_08.settings.manageCities

import android.R
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import com.example.loginapp_5_08.homeScreen.ViewPager.Cities
import com.example.loginapp_5_08.homeScreen.ViewPager.CityHelper
import com.example.loginapp_5_08.shared.SharedPreference

class ManageCityViewModel: ViewModel() {

    private lateinit var sharedPreferences: SharedPreference
    private lateinit var view: View

    lateinit var cities:ArrayList<Cities>
    var citiesSize = 0

    fun getAllCities(): ArrayList<Cities>{
        cities = CityHelper.getCitiesFromJson("cities.json", view.context)
        citiesSize = cities.size

        return cities
    }

    fun getListAdapterAllCities(): ArrayAdapter<String>{
        val listViewAdapterContent:ArrayList<String> = ArrayList()

        for (i in 0..citiesSize-1) {
            listViewAdapterContent.add(cities[i].name)
        }

        val listAdapter = ArrayAdapter(view.context, R.layout.simple_list_item_1, R.id.text1, listViewAdapterContent)
        return listAdapter
    }

    fun getListAdapterAddedCities(): ArrayAdapter<String>{
        val added = sharedPreferences.getArrayList("addedCitiesList")

        return ArrayAdapter<String>(view.context, R.layout.simple_list_item_1, R.id.text1, added)
    }

    fun addCityIntoUserPreference(cityName:String){
        val adde = sharedPreferences.getArrayList("addedCitiesList")
        adde.add(0,cityName)

        sharedPreferences.saveArrayList("addedCitiesList",adde)
    }

    fun deleteCityFromUserPreference(cityPosition:Int){
        val adde = sharedPreferences.getArrayList("addedCitiesList")
        adde.removeAt(cityPosition)

        sharedPreferences.saveArrayList("addedCitiesList",adde)
    }

    fun init(sharedPreference: SharedPreference, view: View) {
        sharedPreferences = sharedPreference
        this.view = view
    }

}