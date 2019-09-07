package com.example.loginapp_5_08.weatherClasses

class WeekWeather {
    private lateinit var img : String
    private lateinit var minTemp : String
    private lateinit var maxTemp : String
    private lateinit var avrgTemp : String
    private lateinit var date : String
    private lateinit var day : String
    private lateinit var status : String

    fun init(image:String, min:String, max:String, avrg:String, dat:String, day:String, status:String)
    {
        this.img = image
        this.minTemp = min
        this.maxTemp = max
        this.avrgTemp = avrg
        this.status = status
        this.date = dat
        this.day = day
    }
}