package com.ubaya.ulib160419002.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllOrder(vararg order:Order)

    @Update
    suspend fun updateOrder(order: Order)
}