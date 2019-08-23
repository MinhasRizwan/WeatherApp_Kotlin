package com.example.loginapp_5_08.data.response.future


import com.google.gson.annotations.SerializedName

data class Forecastday(
    val astro: Astro,
    val date: String,
    @SerializedName("date_epoch")
    val dateEpoch: Int,
    val day: Day
)