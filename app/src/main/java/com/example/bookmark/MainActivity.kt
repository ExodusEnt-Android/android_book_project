package com.example.bookmark

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmark.BuildConfig.*
import com.example.bookmark.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataSet : ArrayList<Items> = arrayListOf()

    val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_NAVER_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    val api = retrofit.create(NaverAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.searchBtn.setOnClickListener (View.OnClickListener {
            binding.bookRecycler.adapter?.notifyDataSetChanged()
            val callGetSearchNews =  api.getSearchBooks(CLIENT_ID, CLIENT_SECRET, "book", binding.search.text.toString())
            callGetSearchNews.enqueue(object : Callback<BookData> {
                override fun onResponse(
                    call: Call<BookData>,
                    response: Response<BookData>
                ) {
                    Log.d(ContentValues.TAG, "ABC성공 : ${response.body()?.items}"+" response code ->"+response.code())
                    binding.bookRecycler.adapter = BookSearchAdapter(ArrayList(response.body()?.items))
                    binding.bookRecycler.layoutManager = LinearLayoutManager(this@MainActivity)
                }

                override fun onFailure(call: Call<BookData>, t: Throwable) {
                    Log.d(ContentValues.TAG, "ABC실패 : $t")
                }
            })
        })
    }

}