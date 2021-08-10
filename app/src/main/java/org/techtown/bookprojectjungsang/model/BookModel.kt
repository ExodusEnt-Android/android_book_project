package org.techtown.bookprojectjungsang.model

import com.google.gson.annotations.SerializedName

data class BookModel(
    @SerializedName("display") val display: Int,
    @SerializedName("items") val items: ArrayList<BookModelItem>,
    @SerializedName("lastBuildDate") val lastBuildDate: String,
    @SerializedName("start") val start: Int,
    @SerializedName("total") val total: Int
)