package org.techtown.bookprojectjungsang.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey val id:Int,
    @ColumnInfo(name = "content") val content:String
)
