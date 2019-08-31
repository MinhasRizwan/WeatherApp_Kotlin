package com.example.loginapp_5_08.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.loginapp_5_08.settings.Database.UserPref
import com.example.loginapp_5_08.shared.SharedPreference

class UserPrefRepository(private val sharedPreference: SharedPreference) {

    //val pref: MutableLiveData<UserPref> = UserPref()

    private var pref = MutableLiveData<UserPref>()

    fun getPreference() : LiveData<UserPref>{
        pref.value?.tempScale = sharedPreference.getValueInt("tempScale")
        pref.value?.locationCheck = sharedPreference.getValueBoolean("currentLocation",false)

        return pref
    }

    fun setPreference(scaleTemp:Int, currentLocationCheck:Boolean){
        sharedPreference.save("currentLocation",currentLocationCheck)
        sharedPreference.save("tempScale", scaleTemp)
    }

}







/*
class UserPrefRepository(private val userPrefDao: UserPrefDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allPref: LiveData<List<UserPref>> = userPrefDao.getAllPref()

    // The suspend modifier tells the compiler that this must be called from a
    // coroutine or another suspend function.
    // This ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.

    suspend fun insert(userPref: UserPref) {
        deleteAllPref()
        userPrefDao.insert(userPref)
    }

    suspend fun deleteAllPref() {
        userPrefDao.deleteAllPref()
    }
}
*/