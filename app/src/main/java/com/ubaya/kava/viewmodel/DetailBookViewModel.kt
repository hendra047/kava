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
import com.ubaya.kava.model.Order
import com.ubaya.kava.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.*
import kotlin.coroutines.CoroutineContext

class DetailBookViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val bookLD = MutableLiveData<Book>()
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun fetch(bookID: Int) {
        launch {
            val db = buildDb(getApplication())
            bookLD.value =  db.bookDao().selectBook(bookID)
        }
    }
}