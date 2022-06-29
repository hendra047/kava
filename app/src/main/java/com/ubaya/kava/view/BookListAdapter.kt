package com.ubaya.kava.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.kava.databinding.BookListItemBinding
import com.ubaya.kava.model.Book
import kotlinx.android.synthetic.main.book_list_item.view.*

class BookListAdapter(val bookList: ArrayList<Book>, val currentFrag: String): RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {
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
}