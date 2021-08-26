package org.techtown.bookprojectjungsang.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.techtown.bookprojectjungsang.SearchFragment
import org.techtown.bookprojectjungsang.model.BookModelItem
import org.techtown.bookprojectjungsang.model.Note

@Database(entities = [BookModelItem::class, Note::class], version = 1)
abstract class BookDatabase:RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun notesDao(): NotesDao

    companion object{
        private var instance: BookDatabase? = null

        @Synchronized
        fun getInstance(context: Context): BookDatabase?{
            if(instance == null){
                synchronized(RoomDatabase::class){
                    instance = Room.databaseBuilder(context.applicationContext, BookDatabase::class.java, "book_database").build()
                }
            }
            return instance
        }
    }
}