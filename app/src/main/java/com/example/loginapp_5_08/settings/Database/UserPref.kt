package com.example.loginapp_5_08.settings.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserPref(var tempScale:Int, var locationCheck:Boolean){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}

/*
@Entity(tableName = "user_pref_table")
class UserPref(

    @ColumnInfo(name = "tempScale")
    var tempScale: Int,

    @ColumnInfo(name = "locationCheck")
    var locationCheck: Boolean
)
{
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
*/