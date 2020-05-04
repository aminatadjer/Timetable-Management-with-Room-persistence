package com.example.exo6_7

import android.content.Context
import androidx.room.Database
import androidx.room.Room

import androidx.room.RoomDatabase


@Database(entities =arrayOf(Seance::class),version = 1)
abstract class SeanceDB:RoomDatabase() {
    abstract fun seanceDao(): SeanceDao
    companion object {
        @Volatile
    private var INSTANCE: SeanceDB? = null
        //Singleton pattern with synchonization

    fun getDB(context: Context): SeanceDB {
            if (INSTANCE != null) {
                return INSTANCE as SeanceDB
            }
            synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext, SeanceDB::class.java, "database_calendar"
                ).allowMainThreadQueries().build()
                INSTANCE = db
                return db
            }
        }}
}