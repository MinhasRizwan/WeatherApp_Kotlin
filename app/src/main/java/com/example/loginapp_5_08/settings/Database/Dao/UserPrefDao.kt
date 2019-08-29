package com.example.loginapp_5_08.settings.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.loginapp_5_08.settings.Database.Entity.UserPref

@Dao
interface UserPrefDao {
    @Insert
    fun insert(userPref: UserPref)

    @Query("DELETE FROM user_pref_table")
    fun deleteAllPref()

    @Query("SELECT * FROM user_pref_table ")
    fun getAllPref(): LiveData<List<UserPref>>
}