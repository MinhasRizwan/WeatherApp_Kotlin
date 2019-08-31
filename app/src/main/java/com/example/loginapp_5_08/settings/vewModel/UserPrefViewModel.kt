package com.example.loginapp_5_08.settings.vewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapp_5_08.settings.Database.UserPref
import com.example.loginapp_5_08.settings.UserPrefRepository
import com.example.loginapp_5_08.shared.SharedPreference
import kotlinx.coroutines.launch

class UserPrefViewModel(application: Application) : AndroidViewModel(application) {

    private var sharedPreference : SharedPreference = SharedPreference(application.applicationContext)

    private val repository: UserPrefRepository
    val pref: LiveData<UserPref>

    init {

        repository = UserPrefRepository(sharedPreference)
        pref = repository.getPreference()
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertPref(tempUnit:Int, cityChecked:Boolean) = viewModelScope.launch {
        repository.setPreference(tempUnit,cityChecked)
    }
}

/*
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
}*/