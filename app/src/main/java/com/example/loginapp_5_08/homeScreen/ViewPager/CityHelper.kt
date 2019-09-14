package com.example.loginapp_5_08.homeScreen.ViewPager

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

object CityHelper {

    val KEY_TITLE = "id"
    val KEY_RATING = "name"
    val KEY_POSTER_URI = "country"
    val KEY_OVERVIEW = "coord"

    fun getCitiesFromJson(fileName: String, context: Context): ArrayList<Cities> {

        val cities = ArrayList<Cities>()

        try {
            // Load the JSONArray from the file
            val jsonString = loadJsonFromFile(fileName, context)
            val json = JSONObject(jsonString)
            val jsonCities = json.getJSONArray("cities")

            // Create the list of Cities
            for (index in 0 until jsonCities.length()) {
                val cityId = jsonCities.getJSONObject(index).getString(KEY_TITLE)
                val cityname = jsonCities.getJSONObject(index).getString(KEY_RATING)
                val country = jsonCities.getJSONObject(index).getString(KEY_POSTER_URI)
                val coord = jsonCities.getJSONObject(index).getJSONObject(KEY_OVERVIEW)

                val longi = coord.getString("lon")
                val lati = coord.getString("lat")

                cities.add(Cities(cityId, cityname, country, longi, lati))
            }
        } catch (e: JSONException) {
            return cities
        }

        return cities
    }

    private fun loadJsonFromFile(filename: String, context: Context): String {
        var json = ""

        try {
            val input = context.assets.open(filename)
            val size = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            json = buffer.toString(Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return json
    }
}