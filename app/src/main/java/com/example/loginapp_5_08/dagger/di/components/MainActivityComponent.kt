package com.example.loginapp_5_08.dagger.di.components

import android.content.Context
import com.example.loginapp_5_08.dagger.di.modules.RetrofitModule
import com.example.loginapp_5_08.dagger.di.modules.SharedPrefModule
import com.example.loginapp_5_08.dagger.di.qualifiers.ActivityContext
import com.example.loginapp_5_08.dagger.di.scope.ActivityScope
import com.example.loginapp_5_08.dagger.di.scope.ApplicationScope
import com.example.loginapp_5_08.homeScreen.HomeScreen
import dagger.Component
import dagger.Provides

@ActivityScope
@Component(modules = [SharedPrefModule::class], dependencies = [ApplicationComponent::class])
interface MainActivityComponent {

    @ActivityContext
    fun getContext(): Context{
        val context = this.getContext()
        return context
    }


    fun injectMainActivity(mainActivity: HomeScreen)
}
