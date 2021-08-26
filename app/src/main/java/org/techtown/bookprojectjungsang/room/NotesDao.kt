package org.techtown.bookprojectjungsang.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.techtown.bookprojectjungsang.model.Note

@Dao
interface NotesDao {

    @Query("SELECT * FROM note_table")
    fun getAll(): List<Note>

    @Insert
    fun insert(note:Note)

    @Delete
    fun delete(note:Note)

}