package org.techtown.bookprojectjungsang.model

import android.content.Context
import org.json.JSONObject
import org.techtown.bookprojectjungsang.room.BookDatabase

//Local에있는 DB접근용 remote는 따로 만들어주기.
class BookLocalDataSource(context: Context) : BookDataSource {

    val bookDatabase = BookDatabase.getInstance(context = context)!!

    override fun getBook(callback: BookDataSource.LoadBookCallback, isbn: String?) {

        bookDatabase.runInTransaction {
            val selectedBook = bookDatabase.bookDao().selectBook(isbn = isbn)
            if(isbn !=null){ //로컬에 저장되있을시.
                callback.onBookLoaded(selectedBook)
            } else { //안되있을떄.
                callback.onDataNotAvailable()
            }
        }
    }

    override fun saveBook(book: BookModelItem) {
        bookDatabase.runInTransaction {
            bookDatabase.bookDao().insert(book)
        }
    }

    override fun deleteBook(book: BookModelItem) {
        bookDatabase.runInTransaction {
            bookDatabase.bookDao().delete(book = book)
        }
    }
}