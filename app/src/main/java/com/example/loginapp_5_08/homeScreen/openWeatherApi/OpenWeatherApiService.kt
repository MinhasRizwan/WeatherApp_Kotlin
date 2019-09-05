package com.example.loginapp_5_08.homeScreen.openWeatherApi

import com.example.loginapp_5_08.homeScreen.openWeatherApi.response.current.CurrentWeatherResponseOWM
import com.example.loginapp_5_08.homeScreen.openWeatherApi.response.future.FutureWeatherResponseOWM
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY2 = "5c2e9349aae04a0135de96c61ca1b68b"
//http://api.apixu.com/v1/current.json?key=cc32f893292b40f0ad5114637192108&q=London
//http://api.apixu.com/v1/forecast.json?key=cc32f893292b40f0ad5114637192108&q=London&days=7

//http://api.openweathermap.org/data/2.5/forecast?q=London,uk&APPID=5c2e9349aae04a0135de96c61ca1b68b
//http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=5c2e9349aae04a0135de96c61ca1b68b
//http://api.openweathermap.org/data/2.5/forecast?lat=31.5204&lon=74.3587&APPID=5c2e9349aae04a0135de96c61ca1b68b

interface OpenWeatherApiService {
    @GET("weather")
    fun getCurrentWeather(
        @Query("q") location: String,
        @Query("lang") languageCode: String = "en"
    ) : Call<CurrentWeatherResponseOWM>
    //Deferred<CurrentWeatherResponseOWM>

    @GET("forecast")
    fun getFutureWeather(
        @Query("q") location: String,
        @Query("lang") languageCode: String = "en"
        //@Query("days") days:Int = 7
    ) : Call<FutureWeatherResponseOWM>

    @GET("forecast")
    fun getFutureWeatherByLocation(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String
        //@Query("days") days:Int = 7
    ) : Call<FutureWeatherResponseOWM>


    companion object{
        operator fun invoke(): OpenWeatherApiService {
            val requestInterceptor = Interceptor{chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("APPID", API_KEY2)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)

            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenWeatherApiService::class.java)
        }
    }
}