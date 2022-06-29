package com.ubaya.kava.model

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.room.*
import com.ubaya.kava.util.DB_NAME
import com.ubaya.kava.util.MIGRATION_1_2
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubaya.kava.util.initDatabase
import java.util.concurrent.Executors


@Database(entities = [User::class, Book::class, Order::class, Bookmark::class], version = 2, exportSchema = false)
abstract class KavaDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun bookDao(): BookDao
    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var instance: KavaDatabase? = null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =  Room.databaseBuilder(
            context.applicationContext,
            KavaDatabase::class.java,
            DB_NAME)
            .addMigrations(MIGRATION_1_2)
            .addCallback(initDatabase)
            .build()

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