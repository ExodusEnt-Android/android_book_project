package com.example.naverbookapiproject.Fragment

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.naverbookapiproject.R
import com.example.naverbookapiproject.databinding.FragmentSearchBookBinding
import com.example.naverbookapiproject.util.hideKeyboard

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
            startEditTextMove()
            Toast.makeText(requireActivity(), "검색하기 ", Toast.LENGTH_SHORT).show()
        }
    }

    //검색 editText animation  up
    private fun startEditTextMove() {
        if(binding.root.currentState == R.id.start){
            binding.root.transitionToEnd()//검색 창  상단으로 up
        }else{
            binding.root.transitionToStart()
        }
        binding.editInputSearchText.hideKeyboard()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

}