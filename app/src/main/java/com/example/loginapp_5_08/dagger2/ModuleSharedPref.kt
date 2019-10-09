package com.example.loginapp_5_08.dagger2

import android.content.Context
import dagger.Module
import android.content.SharedPreferences
import com.example.loginapp_5_08.shared.SharedPreference
import dagger.Provides



@Module
class ModuleSharedPref {

    @Provides
    @WeatherApplicationScope
    fun sharedPreferences(context: Context): SharedPreference {
        //return context.getSharedPreferences("settings", Context.MODE_PRIVATE)

        val sharedPref = SharedPreference(context)
        return sharedPref
    }
}