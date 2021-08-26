package org.techtown.bookprojectjungsang.model

import org.json.JSONObject

interface BookDataSource {

    interface LoadBookCallback{
        fun onBookLoaded(book: BookModelItem?)
        fun onDataNotAvailable()
    }

    fun getBook(callback: LoadBookCallback, isbn:String?)

    fun saveBook(book: BookModelItem)

    fun deleteBook(book: BookModelItem)
}