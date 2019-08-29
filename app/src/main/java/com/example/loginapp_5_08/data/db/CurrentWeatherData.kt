package com.example.loginapp_5_08.data.db

import androidx.room.Entity

@Entity(tableName = "currentWeatherData")
class CurrentWeatherData(
    temper: Double,
    date: String,
    icon: String,
    name: String,
    description: String
) {

    //@PrimaryKey(autoGenerate = true)
    //@ColumnInfo(name = "dataId")
    //@NonNull
    private var dataId = 0
    private var temp = temper
    private var time = date
    private var iconUrl = icon
    private var city = name
    private var status = description

    fun CurrentWeatherData(temp:Double, time:String, icon:String, city:String, status:String)
    {
        this.temp = temp
        this.time = time
        this.iconUrl = icon
        this.city = city
        this.status = status
    }

    fun setDataId(dataId: Int)
    {
        this.dataId = dataId
    }

    fun getDataId():Int{
        return this.dataId
    }

    fun setTemp(temp:Double)
    {
        this.temp = temp
    }

    fun getTemp():Double{
        return this.temp
    }

    fun setTime(time: String)
    {
        this.time = time
    }

    fun getTime():String{
        return this.time
    }

    fun setIconUrl(icon: String)
    {
        this.iconUrl = icon
    }

    fun getIconUrl():String{
        return this.iconUrl
    }

    fun setCity(city: String)
    {
        this.city = city
    }

    fun getCity():String{
        return this.city
    }

    fun setStatus(status: String)
    {
        this.status = status
    }

    fun getStatus():String{
        return this.status
    }

}