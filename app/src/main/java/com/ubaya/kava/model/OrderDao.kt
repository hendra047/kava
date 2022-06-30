package com.ubaya.kava.model

import androidx.room.*

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllOrder(vararg order:Order)

    @Query("SELECT * FROM `Order` WHERE username = :username AND is_paid = 1 AND end_date >= :today")
    suspend fun selectMyBooks(username: String, today: String): List<Order>

    @Query("SELECT * FROM `Order` WHERE username = :username AND is_paid = 1")
    suspend fun selectCarts(username: String): List<Order>

    @Query("SELECT * FROM `Order` WHERE username = :username AND is_paid = 1 AND end_date < :today")
    suspend fun selectHistories(username: String, today: String): List<Order>

    @Update
    suspend fun updateOrder(order: Order)

    @Delete
    suspend fun deleteOrder(order: Order)
}