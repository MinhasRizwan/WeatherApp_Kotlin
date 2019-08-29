package com.example.loginapp_5_08.data.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "hourlyWeatherData")
class HourlyWeatherData {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "dataId")
    @NonNull
    private var dataId = 0
    private var temp = 0.0
    private var time = ""
    private var iconUrl = ""

    fun HourlyWeatherData(temp:Double, time:String, icon:String)
    {
        this.temp = temp
        this.time = time
        this.iconUrl = icon
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



}