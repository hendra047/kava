package com.ubaya.kava.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.kava.model.GlobalData
import com.ubaya.kava.model.User
import com.ubaya.kava.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val profileLiveData = MutableLiveData<User>()
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun fetch() {
        launch{
            val db = buildDb(getApplication())
            profileLiveData.value = db.userDao().selectUser(GlobalData.username)
        }
    }

    fun update(username:String, name:String, gender:String, phone:String, photoUrl:String){
        launch {
            val db = buildDb(getApplication())
            db.userDao().updateUser(username, name, gender, phone, photoUrl)
            Log.d("update",db.userDao().selectUser(username).toString())
        }
    }

    fun addUser(list:List<User>){
        launch {
            val db = buildDb(getApplication())
            db.userDao().insertAllUser(*list.toTypedArray())
        }
    }
}