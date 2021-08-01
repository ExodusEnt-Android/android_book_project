package com.example.bookmark

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookmark.databinding.ItemBookSearchBinding

class BookSearchAdapter(private val data: ArrayList<Items>): RecyclerView.Adapter<BookSearchAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemBookSearchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBookSearchBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var title2 :String
        var description2 : String
        title2 = data[position].title.replace("<b>","")
        title2 = title2.replace("</b>","")
        holder.binding.bookTitle.text = title2

        description2 = data[position].description.replace("<b>","")
        description2 = description2.replace("</b>","")
        holder.binding.bookDetail.text = data[position].description
        Glide.with(holder.itemView.context).load(data[position].image).into(holder.binding.bookImg)
    }



}
