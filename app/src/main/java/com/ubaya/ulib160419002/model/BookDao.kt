package com.ubaya.ulib160419002.model

import androidx.room.*

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBook(vararg book:Book)

    @Query("SELECT * FROM book")
    suspend fun selectAllBook(): List<Book>

    @Update
    suspend fun updateBook(book: Book)
}