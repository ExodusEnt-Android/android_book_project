package org.techtown.bookprojectjungsang.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.techtown.bookprojectjungsang.model.Note
import org.techtown.bookprojectjungsang.room.BookDatabase

class NotesViewModel(application:Application): AndroidViewModel(application) {

    private val db = Room.databaseBuilder( application.applicationContext, BookDatabase::class.java, "book_database").build()


    var notes by mutableStateOf(listOf<Note>())
        private set

    init {
        GlobalScope.launch {
            val items = db.notesDao().getAll()
            viewModelScope.launch { notes = items }
        }
    }

    fun addNote(note:String){
        val noteObj = Note((System.currentTimeMillis() % Int.MAX_VALUE).toInt(), note)
        notes = notes + listOf(noteObj)
        GlobalScope.launch { db.notesDao().insert(note = noteObj) }
    }

    fun remoteNote(note:Note){
        notes = notes - listOf(note)
        GlobalScope.launch { db.notesDao().delete(note) }
    }
}