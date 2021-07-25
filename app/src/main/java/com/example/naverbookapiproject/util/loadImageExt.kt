package com.example.naverbookapiproject.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import com.bumptech.glide.Glide
import com.example.naverbookapiproject.R


//glide 에  이미지 로드용  확장 함수
fun ImageView.loadImage(imageUrl:String?){
    Glide.with(context)
        .load(imageUrl)
        .error(R.drawable.ic_launcher_background)
        .placeholder(R.drawable.ic_launcher_background)
        .centerCrop()
        .into(this)
}