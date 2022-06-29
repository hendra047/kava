package com.ubaya.kava.model

import android.content.Context
import androidx.room.*
import com.ubaya.kava.util.DB_NAME

@Database(entities = [User::class, Book::class, Order::class], version = 1, exportSchema = false)
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
            DB_NAME).build()

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