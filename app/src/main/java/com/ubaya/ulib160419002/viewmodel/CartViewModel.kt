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
import com.ubaya.ulib160419002.model.GlobalData

class CartViewModel(application: Application) : AndroidViewModel(application) {
    val booksLiveData = MutableLiveData<ArrayList<Book>>()
    val booksLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "cartTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        booksLoadErrorLiveData.value = false
        loadingLiveData.value = true

        queue = Volley.newRequestQueue(getApplication())

        val url = "https://ubaya.fun/native/160419002/ulib/carts.php?user_id=${GlobalData.userID}"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val sType = object : TypeToken<ArrayList<Book>>() {}.type
                val result = Gson().fromJson<ArrayList<Book>>(it, sType)

                booksLiveData.value = result
                loadingLiveData.value = false
                Log.d("cart", result.toString())
            },
            {
                loadingLiveData.value = false
                booksLoadErrorLiveData.value = true
                Log.d("error_cart", it.message.toString())
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