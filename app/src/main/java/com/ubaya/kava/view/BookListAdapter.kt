package com.ubaya.kava.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.kava.databinding.BookListItemBinding
import com.ubaya.kava.model.Book
import kotlinx.android.synthetic.main.book_list_item.view.*

class BookListAdapter(val bookList: ArrayList<Book>, val currentFrag: String): RecyclerView.Adapter<BookListAdapter.BookViewHolder>(), DetailBookClickListener {
    class BookViewHolder(var view: BookListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = BookListItemBinding.inflate(inflater, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        with(holder.view) {
            this.book = book
            detailListener = this@BookListAdapter
//            textShowTitleBook.text = book.title
//            textShowAuthor.text = book.author
//            textShowRating.text = book.rating.toString()
//            imageCoverBook.loadImage(300, 400, book.coverUrl, progressLoadImageCover)
//            this.setOnClickListener {
//                var action = HomeFragmentDirections.actionBookDetailFragment(book.id!!)
//                when(currentFrag) {
//                    "My Books" -> action = MyBooksFragmentDirections.actionMyBooksDetailFragment(book.id!!)
//                    "Bookmark" -> action = BookmarkFragmentDirections.actionBookmarkDetailFragment(book.id!!)
//                    "Cart" -> action = CartFragmentDirections.actionCartDetailFragment(book.id!!)
//                    "History" -> action = HistoryFragmentDirections.actionHistoryDetailFragment(book.id!!)
//                }
//                Navigation.findNavController(it).navigate(action)
//            }
        }
    }

    override fun getItemCount() = bookList.size

    fun updateBookList(newBookList: List<Book>) {
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailClick(v: View) {
        var action = HomeFragmentDirections.actionBookDetailFragment(v.tag.toString().toInt())
        when(currentFrag) {
            "My Books" -> action = MyBooksFragmentDirections.actionMyBooksDetailFragment(v.tag.toString().toInt())
            "Bookmark" -> action = BookmarkFragmentDirections.actionBookmarkDetailFragment(v.tag.toString().toInt())
            "Cart" -> action = CartFragmentDirections.actionCartDetailFragment(v.tag.toString().toInt())
            "History" -> action = HistoryFragmentDirections.actionHistoryDetailFragment(v.tag.toString().toInt())
        }
        Navigation.findNavController(v).navigate(action)
    }
}