package com.example.loginapp_5_08.dagger.di.components

import android.content.Context
import com.example.loginapp_5_08.dagger.WeatherApplication
import com.example.loginapp_5_08.dagger.di.modules.ContextModule
import com.example.loginapp_5_08.dagger.di.modules.RetrofitModule
import com.example.loginapp_5_08.dagger.di.qualifiers.ApplicationContext
import com.example.loginapp_5_08.dagger.di.scope.ApplicationScope
import com.example.loginapp_5_08.homeScreen.openWeatherApi.OpenWeatherApiService
import dagger.Component

@ApplicationScope
@Component(modules = [ContextModule::class, RetrofitModule::class])
interface ApplicationComponent {

    val apiInterface: OpenWeatherApiService

    @get:ApplicationContext
    val context: Context

    fun injectApplication(myApplication: WeatherApplication)
}
