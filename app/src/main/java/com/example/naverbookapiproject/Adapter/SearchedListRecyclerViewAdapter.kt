package com.example.naverbookapiproject.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naverbookapiproject.Model.GetBookData
import com.example.naverbookapiproject.R
import com.example.naverbookapiproject.databinding.RcyItemSearchedBookBinding
import com.example.naverbookapiproject.util.loadImage

class SearchedListRecyclerViewAdapter():RecyclerView.Adapter<SearchedListRecyclerViewAdapter.BookItemViewHolder>() {

    private val bookList = ArrayList<GetBookData.BookData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        val binding = RcyItemSearchedBookBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BookItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
       holder.bind(bookList[position])
    }

    override fun getItemCount(): Int = bookList.size


    //검색으로 받아온  책 데이터를 가지고온다.
    fun updateBookData(searchedBooks:List<GetBookData.BookData>){
       this.bookList.clear()
       this.bookList.addAll(searchedBooks)
       notifyDataSetChanged()
    }



    inner class BookItemViewHolder(private val binding: RcyItemSearchedBookBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(bookData: GetBookData.BookData){

            //작가 이름
            binding.tvSearchedBookAuthor.text = "작가 : "+bookData.bookAuthor

            //책 이미지
            binding.imgSearchedBook.loadImage(bookData.bookImageUrl)
        }
    }

}