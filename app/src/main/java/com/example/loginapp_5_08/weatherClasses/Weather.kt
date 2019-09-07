package com.example.loginapp_5_08.weatherClasses

class Weather {
    private lateinit var img : String
    private lateinit var minTemp : String
    private lateinit var maxTemp : String
    private lateinit var avrgTemp : String
    private lateinit var date : String
    private lateinit var status : String
    private lateinit var cityName : String
    private lateinit var day : String
    private lateinit var weekWeather: WeekWeather
    private lateinit var hourWeather: HourWeather

    fun init(image:String, min:String, max:String, avrg:String, dat:String, status:String, city:String, day:String)
    {
        this.img = image
        this.minTemp = min
        this.maxTemp = max
        this.avrgTemp = avrg
        this.date = dat
        this.status = status
        this.cityName = city
        this.day = day
        weekWeather = WeekWeather()
        hourWeather = HourWeather()
    }


}