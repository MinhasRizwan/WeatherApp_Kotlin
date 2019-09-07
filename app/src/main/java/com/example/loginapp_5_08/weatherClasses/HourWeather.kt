package com.example.loginapp_5_08.weatherClasses

class HourWeather {
    private lateinit var img : String
    private lateinit var minTemp : String
    private lateinit var maxTemp : String
    private lateinit var avrgTemp : String
    private lateinit var time : String
    private lateinit var status : String

    fun init (image:String, min:String, max:String, avrg:String, tim:String, status:String)
    {
        this.img = image
        this.minTemp = min
        this.maxTemp = max
        this.avrgTemp = avrg
        this.status = status
        this.time = tim
    }

}