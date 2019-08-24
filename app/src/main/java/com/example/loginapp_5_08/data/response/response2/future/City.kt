package com.example.loginapp_5_08.data.response.response2.future


import com.google.gson.annotations.SerializedName

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)