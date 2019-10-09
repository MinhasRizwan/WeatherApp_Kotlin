package com.example.loginapp_5_08.dagger.di.modules

import android.content.Context
import com.example.loginapp_5_08.dagger.di.scope.ActivityScope
import com.example.loginapp_5_08.dagger.di.scope.ApplicationScope
import com.example.loginapp_5_08.shared.SharedPreference
import dagger.Module
import dagger.Provides


@Module
class SharedPrefModule {
    @Provides
    @ActivityScope
    fun provideSharedPreference(context: Context): SharedPreference {
        //return context.getSharedPreferences("settings", Context.MODE_PRIVATE)

        val sharedPref = SharedPreference(context)
        return sharedPref
    }

}