package com.example.loginapp_5_08.data.response.response2.future


import com.google.gson.annotations.SerializedName

data class X(
    val dt: Int,
    @SerializedName("dt_txt")
    val dtTxt: String,
    val main: Main,
    val weather: List<Weather>
)