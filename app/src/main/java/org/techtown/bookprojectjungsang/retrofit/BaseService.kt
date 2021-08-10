package org.techtown.bookprojectjungsang.retrofit

import android.util.Log
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.techtown.bookprojectjungsang.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException





class BaseService {

    fun getClient(baseUrl: String): Retrofit {

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor {
            val original = it.request()

            val request = original.newBuilder()
                .header("X-Naver-Client-Id", "7AcZjpBkEtY6I9AW9dqw")
                .header("X-Naver-Client-Secret", "Ys0vYi3VHb")
                .build()

            return@addInterceptor it.proceed(request)
        }

        val interceptor = httpLoggingInterceptor()

        val client = httpClient.addNetworkInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("MyGitHubData :", message + "")
            }
        })
        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}


object BookSearchService {
    private const val BOOK_SEARCH_URL = "https://openapi.naver.com"

    val client: BookSearchApi = BaseService().getClient(BOOK_SEARCH_URL).create(BookSearchApi::class.java)
}