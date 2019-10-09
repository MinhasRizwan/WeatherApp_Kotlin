package com.example.loginapp_5_08.dagger2

import android.content.Context
import com.example.loginapp_5_08.shared.SharedPreference
import dagger.Module
import dagger.Provides



@Module
class ModuleContext {

    lateinit var context: Context


    @Provides
    @WeatherApplicationScope
    fun context(): Context {
        return this.context
    }

    constructor(context: Context)  {
        this.context = context
    }


}