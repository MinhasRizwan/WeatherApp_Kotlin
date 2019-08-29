package com.example.loginapp_5_08.settings.vewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.loginapp_5_08.settings.Database.Entity.UserPref
import com.example.loginapp_5_08.settings.UserPrefRepository

class UserPrefViewModel(application: Application) : AndroidViewModel(application){

    private var repository: UserPrefRepository =
        UserPrefRepository(application)

    private var allPrefs: LiveData<List<UserPref>> = repository.getAllPref()

    fun insert(pref: UserPref) {
        repository.insert(pref)
    }

    fun deleteAllPref() {
        repository.deleteAllPref()
    }

    fun getAllPref(): LiveData<List<UserPref>> {
        return allPrefs
    }
}