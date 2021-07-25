package com.example.naverbookapiproject.retrofit

import com.example.naverbookapiproject.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NetWorkInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("X-Naver-Client-Id", BuildConfig.NAVER_SEARCH_BOOK_CLIENT_ID)
            .addHeader("X-Naver-Client-Secret",BuildConfig.NAVER_SEARCH_BOOK_CLIENT_SECRET)
            .build()

        return chain.proceed(newRequest)
    }
}