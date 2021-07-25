package com.example.naverbookapiproject.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    //검색 창에 검색어로 넣은 string을 query로 보내서
    //관련된 책 목록을 가지고 온다.
    @GET("book.json")
    fun getBookSearchResult(
        @Query("query") searchedBookName:String
    ): Call<ResponseBody>

}