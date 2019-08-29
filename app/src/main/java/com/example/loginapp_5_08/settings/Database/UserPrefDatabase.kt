package com.example.loginapp_5_08.settings.Database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.loginapp_5_08.settings.Database.Dao.UserPrefDao
import com.example.loginapp_5_08.settings.Database.Entity.UserPref

@Database(entities = [UserPref::class], version = 1)
abstract class UserPrefDatabase : RoomDatabase() {
    abstract fun userPrefDao():     UserPrefDao


    companion object {
        private var instance: UserPrefDatabase? = null

        fun getInstance(context: Context): UserPrefDatabase? {
            if (instance == null) {
                synchronized(UserPrefDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserPrefDatabase::class.java, "user_pref_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            }
        }
    }

    class PopulateDbAsyncTask(db: UserPrefDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val prefDao = db?.userPrefDao()

        override fun doInBackground(vararg p0: Unit?) {
            prefDao?.insert(UserPref("F", true))
            prefDao?.insert(UserPref("C", true))
            prefDao?.insert(UserPref("F", true))
        }
    }
}
