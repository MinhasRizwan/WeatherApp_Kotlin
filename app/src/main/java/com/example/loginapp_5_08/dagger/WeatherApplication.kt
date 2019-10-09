package com.example.loginapp_5_08.dagger

import android.app.Activity
import android.app.Application
import com.example.loginapp_5_08.dagger.di.components.ApplicationComponent
import com.example.loginapp_5_08.dagger.di.components.DaggerApplicationComponent
import com.example.loginapp_5_08.dagger.di.modules.ContextModule

class WeatherApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder().contextModule(ContextModule(this)).build()
        applicationComponent.injectApplication(this)

    }

    companion object {

        operator fun get(activity: Activity): WeatherApplication {
            return activity.application as WeatherApplication
        }
    }
}

