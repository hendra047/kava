package com.ubaya.kava.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    var username: String = "",
    @ColumnInfo(name="password")
    var password: String? = "",
    @ColumnInfo(name="name")
    var name: String? = "",
    @ColumnInfo(name="gender")
    var gender: String? = "",
    @ColumnInfo(name="phone")
    var phone: String? = "",
    @ColumnInfo(name="photo_url")
    var photoURL: String? = ""
)

@Entity
data class Book(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name="title")
    var title: String = "",
    @ColumnInfo(name="subtitle")
    var subtitle: String? = "",
    @ColumnInfo(name="book_no")
    var bookNumber: String = "",
    @ColumnInfo(name="pages")
    var pages: Int = 0,
    @ColumnInfo(name="language")
    var language: String = "",
    @ColumnInfo(name="author")
    var author: String = "",
    @ColumnInfo(name="publisher")
    var publisher: String = "",
    @ColumnInfo(name="description")
    var description: String = "",
    @ColumnInfo(name="rating")
    var rating: Double = 0.0,
    @ColumnInfo(name="cover_url")
    var coverUrl: String = "",
    @ColumnInfo(name="bookmarked", defaultValue = "0")
    var bookmarked: Int = 0
)

@Entity
data class Order(
    @ColumnInfo(name="reserved_date")
    var reservedDate: String?,
    @ColumnInfo(name="end_date")
    var endDate: String?,
    @ColumnInfo(name="is_paid")
    var isPaid: Int?,
    @ColumnInfo(name="username")
    var username: String?,
    @ColumnInfo(name="book_id")
    var bookId: Int?,
    @ColumnInfo(name="book_title")
    var bookTitle: String?,
    @ColumnInfo(name="book_author")
    var bookAuthor: String?,
    @ColumnInfo(name="book_rating")
    var bookRating: Double?,
    @ColumnInfo(name="book_cover_url")
    var bookCoverUrl: String?
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