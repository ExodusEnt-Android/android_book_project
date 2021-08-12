package org.techtown.bookprojectjungsang.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.techtown.bookprojectjungsang.SearchFragment
import org.techtown.bookprojectjungsang.model.BookModelItem

@Database(entities = [BookModelItem::class], version = 1)
abstract class BookDatabase:RoomDatabase() {

    abstract fun bookDao(): BookDao

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