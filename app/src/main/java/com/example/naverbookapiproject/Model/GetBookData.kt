package com.example.naverbookapiproject.Model

import com.google.gson.annotations.SerializedName

//naver api  책 정보를 받아  아래와 같이 직렬화 해줌.
class GetBookData {

    data class SearchedBooks(
        @SerializedName("items")
        val bookLists: List<BookData>?
    )

    data class BookData(
        @SerializedName("title")
        val bookTitle: String?,
        @SerializedName("image")
        val bookImageUrl: String?,
        @SerializedName("link")
        val bookLink: String?,
        @SerializedName("author")
        val bookAuthor: String?
    )
}
