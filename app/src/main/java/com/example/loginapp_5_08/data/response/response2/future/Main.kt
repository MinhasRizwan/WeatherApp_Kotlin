package com.example.loginapp_5_08.data.response.response2.future


import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("grnd_level")
    val grndLevel: Double,
    val humidity: Int,
    val pressure: Double,
    @SerializedName("sea_level")
    val seaLevel: Double,
    val temp: Double,
    @SerializedName("temp_kf")
    val tempKf: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double
)