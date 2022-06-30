package com.ubaya.kava.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubaya.kava.model.GlobalData
import com.ubaya.kava.model.User
import com.ubaya.kava.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val loginLiveData = MutableLiveData<User>()
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun checkLogin(user: User) {
        launch{
            val db = buildDb(getApplication())
            loginLiveData.value = db.userDao().selectUserLogin(
                user.username,
                if (user.password != null) user.password!! else ""
            )
        }
    }
}