package com.example.loginapp_5_08.dagger.di.modules

import android.content.Context
import com.example.loginapp_5_08.dagger.di.qualifiers.ApplicationContext
import com.example.loginapp_5_08.dagger.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides


@Module
class ContextModule(private val context: Context) {

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun provideContext(): Context {
        return context
    }
}