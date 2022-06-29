package com.ubaya.kava.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.kava.databinding.BookListItemBinding
import com.ubaya.kava.databinding.BookmarkListItemBinding
import com.ubaya.kava.model.Bookmark

class BookmarkListAdapter(val bookmarkList: ArrayList<Bookmark>, val currentFrag: String): RecyclerView.Adapter<BookmarkListAdapter.BookmarkViewHolder>() {
    class BookmarkViewHolder(var view: BookmarkListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = BookmarkListItemBinding.inflate(inflater, parent, false)
        return BookmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val bookmark = bookmarkList[position]
        with(holder.view) {
            this.bookmark = bookmark

        }
    }

    override fun getItemCount() = bookmarkList.size

    fun updateBookList(newBookList: List<Bookmark>) {
        bookmarkList.clear()
        bookmarkList.addAll(newBookList)
        Log.d("coba", bookmarkList.count().toString())
        notifyDataSetChanged()
    }
}