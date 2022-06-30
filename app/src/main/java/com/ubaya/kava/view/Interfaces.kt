package com.ubaya.kava.view

import android.view.View
import com.ubaya.kava.model.Book
import com.ubaya.kava.model.User

//interface
interface AddBookClickListener {
    fun onButtonAddBook(v: View)
}

interface EditBookClickListener {
    fun onButtonEditClick(view: View)
}

interface BookSaveChangesListener{
    fun onSaveChanges(view:View, obj:Book)
}

interface UserSaveChangesListener{
    fun onSaveChanges(view:View, obj:User)
}

interface EditUserListener{
    fun onButtonEditClick(view:View)
}