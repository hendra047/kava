package com.ubaya.ulib160419002.model

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class BookDatabase:RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var instance: BookDatabase? = null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =  Room.databaseBuilder(
            context.applicationContext,
            BookDatabase::class.java,
            "newtododb").build()
        operator fun invoke(context: Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}