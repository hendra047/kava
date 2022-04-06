package com.ubaya.ulib160419002.model

import com.google.gson.annotations.SerializedName

data class User(
    var username: String?,
    var password: String?,
    var name: String?,
    var gender: String?,
    var phone: String?,
    @SerializedName("photo_url")
    var photoURL: String?
)

data class Book(
    var id: Int?,
    var title: String?,
    var subtitle: String?,
    @SerializedName("book_no")
    var bookNumber: String?,
    var pages: Int?,
    var language: String?,
    var author: String?,
    var publisher: String?,
    var description: String?,
    var rating: Double?,
    @SerializedName("cover_url")
    var coverUrl: String?,
    var bookmarked: Int
)

data class Notification(
    @SerializedName("id")
    var bookID: Int?,
    var title: String?,
    @SerializedName("remaining_days")
    var remainDays:  Int?
)