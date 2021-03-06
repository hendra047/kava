package com.ubaya.kava.model

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUser(vararg user: User)

    @Query("SELECT * FROM user WHERE username= :username")
    suspend fun selectUser(username:String): User

    @Query("SELECT * FROM user WHERE username= :username AND password= :password")
    suspend fun selectUserLogin(username:String, password:String): User

    @Query("UPDATE user set name = :name, password = :password, gender = :gender, phone = :phone, photo_url = :photoUrl where username = :username")
    suspend fun updateUser(username:String, name:String, password: String, gender:String, phone:String, photoUrl:String)

    @Delete
    suspend fun deleteUser(user: User)
}