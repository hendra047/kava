package com.ubaya.kava.model

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUser(vararg user: User)

    @Query("SELECT * FROM user WHERE username= :username")
    suspend fun selectUser(username:String): User

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}