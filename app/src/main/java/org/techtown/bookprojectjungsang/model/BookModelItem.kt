package org.techtown.bookprojectjungsang.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "bookTable")
data class BookModelItem(
    @ColumnInfo(name = "author")@SerializedName("author") val author: String,
    @ColumnInfo(name = "description")@SerializedName("description") val description: String,
    @ColumnInfo(name = "discount")@SerializedName("discount") val discount: String,
    @ColumnInfo(name = "image")@SerializedName("image") val image: String,
    @PrimaryKey @ColumnInfo(name = "isbn")@SerializedName("isbn") val isbn: String,
    @ColumnInfo(name = "link")@SerializedName("link") val link: String,
    @ColumnInfo(name = "price")@SerializedName("price") val price: String,
    @ColumnInfo(name = "pubdate")@SerializedName("pubdate") val pubdate: String,
    @ColumnInfo(name = "publisher")@SerializedName("publisher") val publisher: String,
    @ColumnInfo(name = "title")@SerializedName("title") val title: String
)