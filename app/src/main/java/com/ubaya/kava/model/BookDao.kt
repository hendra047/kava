package com.ubaya.kava.model

import androidx.room.*

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBook(vararg book:Book)

    @Query("SELECT * FROM book")
    suspend fun selectAllBook(): List<Book>

    @Query("SELECT * FROM book WHERE id= :id")
    suspend fun selectBook(id:Int): Book

    @Update
    suspend fun updateBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)
}