package com.example.loginapp_5_08.dagger.di.modules

import com.example.loginapp_5_08.dagger.di.scope.ApplicationScope
import com.example.loginapp_5_08.homeScreen.openWeatherApi.OpenWeatherApiService
import okhttp3.logging.HttpLoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

@Module
class RetrofitModule {

    internal val httpLoggingInterceptor: HttpLoggingInterceptor
        @Provides
        @ApplicationScope
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            return httpLoggingInterceptor
        }

    @Provides
    @ApplicationScope
    internal fun getApiInterface(retroFit: Retrofit): OpenWeatherApiService{
        return retroFit.create(OpenWeatherApiService::class.java)
    }

    @Provides
    @ApplicationScope
    internal fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @ApplicationScope
    internal fun getOkHttpCleint(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}
