package com.ubaya.ulib160419002.viewmodel

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
import com.ubaya.ulib160419002.model.Book

class ListBookViewModel(application: Application) : AndroidViewModel(application) {
    val booksLiveData = MutableLiveData<ArrayList<Book>>()
    val booksLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "listBookTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        booksLoadErrorLiveData.value = false
        loadingLiveData.value = true

        queue = Volley.newRequestQueue(getApplication())

        val url = "https://ubaya.fun/native/160419002/ulib/books.php"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val sType = object : TypeToken<ArrayList<Book>>() {}.type
                val result = Gson().fromJson<ArrayList<Book>>(it, sType)

                booksLiveData.value = result
                loadingLiveData.value = false
                Log.d("list_book", result.toString())
            },
            {
                loadingLiveData.value = false
                booksLoadErrorLiveData.value = true
                Log.d("error_list_book", it.message.toString())
            }
        ).apply {
            tag = TAG
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}