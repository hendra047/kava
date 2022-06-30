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

    @Query("SELECT * FROM book WHERE bookmarked = 1")
    suspend fun selectAllBookmark(): List<Book>

    @Query("UPDATE book SET bookmarked= :bookmarked WHERE id= :id")
    suspend fun updateBookmark(id:Int, bookmarked: Int)

    @Delete
    suspend fun deleteBook(book: Book)
}