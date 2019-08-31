package com.example.loginapp_5_08.settings.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [UserPref::class], version = 2)
abstract class UserPrefDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: UserPrefDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): UserPrefDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserPrefDatabase::class.java,
                    "user_pref_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(UserPrefDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class UserPrefDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch {
                        //populateDatabase(database.userPrefDao())
                    }
                }
            }
        }

    }
}


// @Database(entities = arrayOf(UserPref::class), version = 1)
// abstract class UserPrefDatabase : RoomDatabase() {
// abstract fun userPrefDao():     UserPrefDao
//
//
// companion object {
// private var instance: UserPrefDatabase? = null
//
// fun getInstance(context: Context): UserPrefDatabase? {
// if (instance == null) {
// synchronized(UserPrefDatabase::class) {
// instance = Room.databaseBuilder(
// context.applicationContext,
// UserPrefDatabase::class.java, "user_pref_database"
// )
// .fallbackToDestructiveMigration()
// .addCallback(roomCallback)
// .build()
// }
// }
// return instance
// }
//
// fun destroyInstance() {
// instance = null
// }
//
// private val roomCallback = object : RoomDatabase.Callback() {
// override fun onCreate(db: SupportSQLiteDatabase) {
// super.onCreate(db)
// PopulateDbAsyncTask(instance)
// .execute()
// }
// }
// }
//
// class PopulateDbAsyncTask(db: UserPrefDatabase?) : AsyncTask<Unit, Unit, Unit>() {
// private val prefDao = db?.userPrefDao()
//
// override fun doInBackground(vararg p0: Unit?) {
// prefDao?.insert(UserPref("F", true))
// prefDao?.insert(UserPref("C", true))
// prefDao?.insert(UserPref("F", true))
// }
// }
// }