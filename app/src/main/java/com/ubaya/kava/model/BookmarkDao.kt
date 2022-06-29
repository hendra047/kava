package com.ubaya.kava.model

import androidx.room.*

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBookmark(vararg bookmark: Bookmark)

    @Query("SELECT * FROM bookmark WHERE username= :username")
    suspend fun selectBookmark(username:String): List<Bookmark>

    @Update
    suspend fun updateBookmark(bookmark: Bookmark)

    @Delete
    suspend fun deleteBookmark(bookmark: Bookmark)
}