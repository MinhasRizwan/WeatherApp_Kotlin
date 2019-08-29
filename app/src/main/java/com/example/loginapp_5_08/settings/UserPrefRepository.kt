package com.example.loginapp_5_08.settings

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.loginapp_5_08.settings.Database.Dao.UserPrefDao
import com.example.loginapp_5_08.settings.Database.Entity.UserPref
import com.example.loginapp_5_08.settings.Database.UserPrefDatabase

class UserPrefRepository (application: Application) {
    lateinit private var userPrefDao: UserPrefDao

    lateinit private var allPref: LiveData<List<UserPref>>

    init {
        val database: UserPrefDatabase = UserPrefDatabase.getInstance(
            application.applicationContext
        )!!
        userPrefDao = database.userPrefDao()
        allPref = userPrefDao.getAllPref()
    }

    fun insert(pre: UserPref) {
        val insertPrefAsyncTask = InsertPrefAsyncTask(userPrefDao).execute(pre)
    }

    fun deleteAllPref() {
        val deleteAllPrefAsyncTask = DeleteAllPrefAsyncTask(
            userPrefDao
        ).execute()
    }

    fun getAllPref(): LiveData<List<UserPref>> {
        return allPref
    }

    private class InsertPrefAsyncTask(preferDao: UserPrefDao) : AsyncTask<UserPref, Unit, Unit>() {
        val prefDao = preferDao

        override fun doInBackground(vararg p0: UserPref?) {
            prefDao.insert(p0[0]!!)
        }
    }


    private class DeleteAllPrefAsyncTask(val preferDao: UserPrefDao) : AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg p0: Unit?) {
            preferDao.deleteAllPref()
        }
    }

}