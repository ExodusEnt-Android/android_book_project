package org.techtown.bookprojectjungsang.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import org.techtown.bookprojectjungsang.model.BookModelItem

@Dao
interface BookDao {

    @Insert
    fun insert(book:BookModelItem)

    @Update
    fun update(book:BookModelItem)

    @Delete
    fun delete(book:BookModelItem)
}