package com.example.loginapp_5_08.dagger.di.modules

import android.content.Context
import com.example.loginapp_5_08.dagger.di.qualifiers.ActivityContext
import com.example.loginapp_5_08.dagger.di.scope.ActivityScope
import com.example.loginapp_5_08.homeScreen.HomeScreen
import dagger.Module
import dagger.Provides

@Module
class MainActivityContextModule(private val mainActivity: HomeScreen) {

    var context: Context

    init {
        context = mainActivity
    }

    @Provides
    @ActivityScope
    fun providesMainActivity(): HomeScreen {
        return mainActivity
    }

    @Provides
    @ActivityScope
    @ActivityContext
    fun provideContext(): Context {
        return context
    }

}
