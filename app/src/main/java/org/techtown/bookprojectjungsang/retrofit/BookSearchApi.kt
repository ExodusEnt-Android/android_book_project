package org.techtown.bookprojectjungsang.retrofit

import org.techtown.bookprojectjungsang.model.BookModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BookSearchApi {

    @GET("v1/search/book.json")
    suspend fun getBookList(@Query("query") query: String): Response<BookModel>

}