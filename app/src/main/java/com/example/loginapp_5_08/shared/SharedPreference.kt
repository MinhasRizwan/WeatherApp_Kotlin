package com.example.loginapp_5_08.shared

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import android.preference.PreferenceManager
import com.google.gson.reflect.TypeToken


class SharedPreference (context: Context){
    private val PREFS_NAME = "settings"
    //private var addedCities  =ArrayList<String>()
    private val sharedPref : SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    //to store Int data
    fun save(KEY_NAME : String , value : Int)
    {
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putInt(KEY_NAME, value)
        editor.apply()
    }

    //to store list of cities
    fun saveCity(KEY_NAME : String , value : String)
    {

    }

    //to store String data
    fun save(KEY_NAME: String , text : String)
    {
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putString(KEY_NAME, text)
        editor.apply()
    }

    //to store Boolean
    fun save(KEY_NAME: String , status : Boolean)
    {
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putBoolean(KEY_NAME, status)
        editor.apply()
    }

    //to get Int
    fun getValueInt(KEY_NAME: String): Int{
        return sharedPref.getInt(KEY_NAME, 0)
    }

    //to get String
    fun getValueString(KEY_NAME: String): String?{
        return sharedPref.getString(KEY_NAME, null)
    }

    //to get Boolean
    fun getValueBoolean(KEY_NAME: String, defaultValue : Boolean): Boolean{
        return sharedPref.getBoolean(KEY_NAME, defaultValue)
    }

    fun clearSharedPreference(){
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.clear()
        editor.apply()
    }

    fun removeValue(KEY_NAME: String){
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.remove(KEY_NAME)
        editor.apply()
    }

    fun saveArrayList( key: String, list: ArrayList<String>) {
        //val prefs = PreferenceManager.getDefaultSharedPreferences(activity)
        val editor = sharedPref.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()     // This line is IMPORTANT !!!
    }

    fun getArrayList(key: String): ArrayList<String> {
        val gson = Gson()
        val json = sharedPref.getString(key, null)
        val type = object : TypeToken<ArrayList<String>>() {

        }.getType()
        return gson.fromJson<ArrayList<String>>(json, type)
    }
}