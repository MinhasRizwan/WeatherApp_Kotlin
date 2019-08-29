package com.example.loginapp_5_08.settings.Database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_pref_table")
class UserPref(
    var tempScale: String,

    var locationCheck: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}