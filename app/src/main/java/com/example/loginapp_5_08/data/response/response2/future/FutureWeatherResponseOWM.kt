package com.example.loginapp_5_08.data.response.response2.future


import com.google.gson.annotations.SerializedName

data class FutureWeatherResponseOWM(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<X>,
    val message: Double
)