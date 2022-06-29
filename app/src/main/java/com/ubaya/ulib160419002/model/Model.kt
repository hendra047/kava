package com.ubaya.ulib160419002.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @ColumnInfo(name="password")
    var password: String?,
    @ColumnInfo(name="name")
    var name: String?,
    @ColumnInfo(name="gender")
    var gender: String?,
    @ColumnInfo(name="phone")
    var phone: String?,
    @ColumnInfo(name="photo_url")
    var photoURL: String?
){
    @PrimaryKey(autoGenerate = false)
    var username: String = ""
}

@Entity
data class Book(
    @ColumnInfo(name="title")
    var title: String?,
    @ColumnInfo(name="subtitle")
    var subtitle: String?,
    @ColumnInfo(name="book_no")
    var bookNumber: String?,
    @ColumnInfo(name="pages")
    var pages: Int?,
    @ColumnInfo(name="language")
    var language: String?,
    @ColumnInfo(name="author")
    var author: String?,
    @ColumnInfo(name="publisher")
    var publisher: String?,
    @ColumnInfo(name="description")
    var description: String?,
    @ColumnInfo(name="rating")
    var rating: Double?,
    @ColumnInfo(name="cover_url")
    var coverUrl: String?,
    @ColumnInfo(name="bookmarked")
    var bookmarked: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Entity
data class Order(
    @ColumnInfo(name="reserved_date")
    var reserved_date: String?,
    @ColumnInfo(name="end_date")
    var end_date: String?,
    @ColumnInfo(name="is_paid")
    var is_paid: Int,
    @ColumnInfo(name="user_id")
    var user_id: Int,
    @ColumnInfo(name="book_id")
    var book_id: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

data class Notification(
    @SerializedName("id")
    var bookID: Int?,
    var title: String?,
    @SerializedName("remaining_days")
    var remainDays:  Int?
)