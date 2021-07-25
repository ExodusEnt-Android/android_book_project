package com.example.naverbookapiproject.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val apiServices:ApiServices by lazy {
        retrofit.create(ApiServices::class.java)
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(retrofitClient)
            .baseUrl(ServerIp.naverOpenSearchUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val retrofitClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(NetWorkInterceptor())
            .build()
    }

}