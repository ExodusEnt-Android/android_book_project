package org.techtown.bookprojectjungsang.model

import com.google.gson.annotations.SerializedName

data class BookModelItem(
    @SerializedName("author") val author: String,
    @SerializedName("description") val description: String,
    @SerializedName("discount") val discount: String,
    @SerializedName("image") val image: String,
    @SerializedName("isbn") val isbn: String,
    @SerializedName("val") val link: String,
    @SerializedName("price") val price: String,
    @SerializedName("pubdate") val pubdate: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("title") val title: String
)