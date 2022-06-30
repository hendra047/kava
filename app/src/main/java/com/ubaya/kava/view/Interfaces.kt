package com.ubaya.kava.view

import android.view.View
import com.ubaya.kava.model.Book
import com.ubaya.kava.model.User

//interface

interface DetailBookClickListener {
    fun onButtonDetailClick(v: View)
}


interface UserSaveChangesListener{
    fun onSaveChanges(view:View, obj:User)
}

interface EditUserListener{
    fun onButtonEditClick(view:View)
}

interface BookmarkListener {
    fun onBookmarkClick(view: View, bookId: Int)
}

interface UserGenderClick{
    fun onRadioGenderClick(view: View, gender:String, obj:User)
}

interface AddUserListener{
    fun onButtonRegisterClick(v:View)
}

