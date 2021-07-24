package com.example.naverbookapiproject.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.naverbookapiproject.R
import com.example.naverbookapiproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSet()
        searchBook()
    }

    //초기 세팅
    private fun initSet(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.transitionToStart()
    }

    //책 검색
    private fun searchBook(){
        //검색 버튼 클릭
        binding.searchButton.setOnClickListener {
            startEditTextUp()//검색 창  상단으로 up
            Toast.makeText(this, "검색하기 ", Toast.LENGTH_SHORT).show()
        }
    }

    //검색 editText animation  up
    private fun startEditTextUp(){
        binding.root.transitionToEnd()
    }
}