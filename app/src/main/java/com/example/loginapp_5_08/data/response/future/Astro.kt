package com.example.loginapp_5_08.data.response.future


import com.google.gson.annotations.SerializedName

data class Astro(
    val moonrise: String,
    val moonset: String,
    val sunrise: String,
    val sunset: String
)