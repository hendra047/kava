package com.ubaya.kava.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.kava.model.Book
import com.ubaya.kava.model.GlobalData
import com.ubaya.kava.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

class MyBooksViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val booksLiveData = MutableLiveData<List<Book>>()
    val booksLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh() {
        booksLoadErrorLiveData.value = false
        loadingLiveData.value = true

        launch {
            val db = buildDb(getApplication())
            val getTodayDate = Calendar.getInstance().time
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            val today = formatter.format(getTodayDate)

            val data = db.orderDao().selectMyBooks(GlobalData.username, today)
            var listBook = listOf<Book>()
            for (raw in data) {
                listBook += Book(
                    id = raw.bookId,
                    title = raw.bookTitle,
                    subtitle = "",
                    bookNumber = "",
                    pages = 0,
                    language ="",
                    author = raw.bookAuthor,
                    publisher= "",
                    description = "",
                    rating = raw.bookRating,
                    coverUrl = raw.bookCoverUrl,
                    bookmarked = 0)
            }

            booksLiveData.value = listBook
            loadingLiveData.value = false
        }
    }
}