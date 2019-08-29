package com.example.loginapp_5_08

class Weather {
    private lateinit var img : String
    private lateinit var minTemp : String
    private lateinit var maxTemp : String
    private lateinit var avrgTemp : String
    private lateinit var date : String
    private lateinit var status : String
    private lateinit var cityName : String
    private lateinit var day : String

    fun setAllValues(image:String, min:String, max:String, avrg:String, dat:String, status:String, city:String, day:String)
    {
        this.img = image
        this.minTemp = min
        this.maxTemp = max
        this.avrgTemp = avrg
        this.date = dat
        this.status = status
        this.cityName = city
        this.day = day
    }

    fun setImg(imgUrl:String){
        this.img = imgUrl
    }

    fun setMinTemp(minimum:String){
        this.minTemp = minimum
    }

    fun setMaxTemp(maximum:String){
        this.maxTemp = maximum
    }

    fun setAvrgTemp(averg:String){
        this.avrgTemp = averg
    }

    fun setDate(date:String){
        this.date = date
    }

    fun setStatus(stat:String){
        this.status = stat
    }

    fun setCity(cit:String){
        this.cityName = cit
    }

    fun setDay(dat:String){
        this.day = day
    }


    fun getImg() : String{
        return img
    }

    fun getMinTemp() : String{
        return minTemp
    }

    fun getMaxTemp() : String{
        return maxTemp
    }

    fun getAvrgTemp() : String{
        return avrgTemp
    }

    fun getDate() : String{
        return date
    }

    fun getStatus() : String{
        return status
    }

    fun getCity() : String{
        return cityName
    }

    fun getDay() : String{
        return day
    }
}