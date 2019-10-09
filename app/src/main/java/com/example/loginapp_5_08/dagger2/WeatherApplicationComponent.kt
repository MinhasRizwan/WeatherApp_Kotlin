package com.example.loginapp_5_08.dagger2

import dagger.Component
import android.app.Activity
import android.app.Application
import com.example.loginapp_5_08.homeScreen.HomeScreen
import com.example.loginapp_5_08.homeScreen.repo.WeatherRepository
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import dagger.android.AndroidInjector
import javax.inject.Inject

@WeatherApplicationScope
@Component(modules = [ModuleContext::class ,ModuleSharedPref::class , ModuleWeatherApiInterface::class])
interface WeatherApplicationComponent : AndroidInjector<WeatherApp> {

    fun injectSharedPref(activity: Activity)
    fun injectRepo(repo: WeatherRepository)
/*
    @Component.Builder
    interface Builder {


        @BindsInstance
        fun application(application: Application) : Builder


        fun build() : WeatherApplicationComponent


    }
*/
}
