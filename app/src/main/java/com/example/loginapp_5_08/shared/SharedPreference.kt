package com.example.loginapp_5_08.shared

import android.content.Context
import android.content.SharedPreferences

class SharedPreference (val context: Context){
    private val PREFS_NAME = "settings"
    private val sharedPref : SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    //to store Int data
    fun save(KEY_NAME : String , value : Int)
    {
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putInt(KEY_NAME, value)
        editor.apply()
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

}