package com.example.loginapp_5_08.dagger2

import com.example.loginapp_5_08.homeScreen.openWeatherApi.OpenWeatherApiService
import dagger.Module
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import dagger.Provides



@Module
class ModuleWeatherApiInterface {

    val apiKey = "5c2e9349aae04a0135de96c61ca1b68b"
    private val baseUrl = "https://api.openweathermap.org/data/2.5/"

    @Provides
    fun weatherApiInterface(retrofit: Retrofit): OpenWeatherApiService {

        return retrofit.create<OpenWeatherApiService>(OpenWeatherApiService::class.java)

    }

    @Provides
    fun retrofit(): Retrofit {

        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
}