package org.techtown.bookprojectjungsang.room

import androidx.room.*
import org.techtown.bookprojectjungsang.model.BookModel
import org.techtown.bookprojectjungsang.model.BookModelItem

@Dao
interface BookDao {

    @Insert
    fun insert(book:BookModelItem)

    @Update
    fun update(book:BookModelItem)

    @Delete
    fun delete(book:BookModelItem)

    @Query("SELECT * from bookTable")
    fun selectAllBook():List<BookModelItem>
}