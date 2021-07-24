package com.example.naverbookapiproject.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.naverbookapiproject.Fragment.BookMarkFragment
import com.example.naverbookapiproject.Fragment.SearchBookFragment
import com.example.naverbookapiproject.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSet()
    }

    //초기 세팅
    private fun initSet() {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.vpSearchBook.apply {
            adapter = PagerAdapter(context as FragmentActivity)
        }

        TabLayoutMediator(binding.tabSearchBook, binding.vpSearchBook) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "책 검색"
                }
                1 -> {
                    tab.text = "북마크 "
                }
            }
        }.attach()
    }

}


private class PagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    //        companion object{
//             var NUM_PAGES = 0
//        }
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchBookFragment()
            else -> BookMarkFragment()
        }
    }

    override fun getItemCount(): Int = 2

}