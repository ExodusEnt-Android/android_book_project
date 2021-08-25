package org.techtown.bookprojectjungsang.model

import android.content.Context

class BookRepository(context: Context) : BookDataSource{

    private val bookLocalDataSource = BookLocalDataSource(context)
    override fun getBook(callback: BookDataSource.LoadBookCallback, isbn: String?) {
        bookLocalDataSource.getBook(callback = callback, isbn = isbn)
    }

    override fun saveBook(book: BookModelItem) {
        bookLocalDataSource.saveBook(book = book)
    }

    override fun deleteBook(book: BookModelItem) {
        bookLocalDataSource.deleteBook(book = book)
    }


}