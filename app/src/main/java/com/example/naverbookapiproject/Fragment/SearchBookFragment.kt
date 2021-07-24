package com.example.naverbookapiproject.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.naverbookapiproject.R
import com.example.naverbookapiproject.databinding.FragmentSearchBookBinding

class SearchBookFragment : Fragment(R.layout.fragment_search_book) {
    private var mBinding: FragmentSearchBookBinding? = null
    private val binding get() = mBinding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentSearchBookBinding.inflate(inflater, container, false)
        binding.root.transitionToStart()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchBook()
    }


    //책 검색
    private fun searchBook() {
        //검색 버튼 클릭
        binding.searchButton.setOnClickListener {
            startEditTextUp()//검색 창  상단으로 up
            Toast.makeText(requireActivity(), "검색하기 ", Toast.LENGTH_SHORT).show()
        }
    }

    //검색 editText animation  up
    private fun startEditTextUp() {
        binding.root.transitionToEnd()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

}