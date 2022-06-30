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
import com.ubaya.kava.model.Bookmark
import com.ubaya.kava.model.GlobalData
import com.ubaya.kava.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BookmarkViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val booksLiveData = MutableLiveData<List<Bookmark>>()
    val booksLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    private var job = Job()

    //Coroutine
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh(username:String) {
        loadingLiveData.value = true
        booksLoadErrorLiveData.value = false
        launch {
            val db = buildDb(getApplication())
            booksLiveData.value = db.bookmarkDao().selectBookmark(username)
        }
    }
}