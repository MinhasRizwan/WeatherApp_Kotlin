package com.example.loginapp_5_08.data
import com.example.loginapp_5_08.data.response.current.CurrentWeatherResponseData
import com.example.loginapp_5_08.data.response.future.FutureWeatherResponseData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_KEY = "cc32f893292b40f0ad5114637192108"
//http://api.apixu.com/v1/current.json?key=cc32f893292b40f0ad5114637192108&q=London
//http://api.apixu.com/v1/forecast.json?key=cc32f893292b40f0ad5114637192108&q=London&days=7

interface ApixuWeatherApiService {

    @GET("current.json")
    fun getCurrentWeather(
        @Query("q") location: String,
        @Query("lang") languageCode: String = "en"
    ) : Deferred<CurrentWeatherResponseData>

    @GET("forecast.json")
    fun getWeekWeather(
        @Query("q") location: String,
        @Query("lang") languageCode: String = "en",
        @Query("days") days:Int = 7
    ) : Deferred<FutureWeatherResponseData>


    companion object{
        operator fun invoke(): ApixuWeatherApiService{
            val requestInterceptor = Interceptor{chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
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
                .baseUrl("https://api.apixu.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherApiService::class.java)
        }
    }
}