package com.example.naverbookapiproject.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.naverbookapiproject.Adapter.SearchedListRecyclerViewAdapter
import com.example.naverbookapiproject.Model.GetBookData
import com.example.naverbookapiproject.R
import com.example.naverbookapiproject.databinding.FragmentSearchBookBinding
import com.example.naverbookapiproject.retrofit.RetrofitHelper
import com.example.naverbookapiproject.util.Logger
import com.example.naverbookapiproject.util.hideKeyboard
import com.example.naverbookapiproject.util.search
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchBookFragment : Fragment(R.layout.fragment_search_book) {
    private var mBinding: FragmentSearchBookBinding? = null
    private val binding get() = mBinding!!
    private lateinit var recyclerViewAdapter: SearchedListRecyclerViewAdapter

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
        initSet()
        initEventListeners()
    }


    //초기 세팅
    private fun initSet() {
        recyclerViewAdapter = SearchedListRecyclerViewAdapter()
        binding.rcySearchedList.apply {
            adapter = recyclerViewAdapter
        }

         //editext 키보드 search 버튼 클릭시  search 버튼 클릭 하도록
        binding.editInputSearchText.search(binding.searchButton)
    }


    private fun updateSearchedBookData(searchedBooks:List<GetBookData.BookData>){
        recyclerViewAdapter.updateBookData(searchedBooks)
    }


    //이벤트  관련 모음
    private fun initEventListeners() {

        //검색 버튼 클릭
        binding.searchButton.setOnClickListener {
            binding.root.transitionToEnd()//검색 창  상단으로 up
            hideKeyBoard()
            //editext에 적힌 text로  naver api 에서  책 검색  실행
            searchBook(binding.editInputSearchText.text.toString())
        }
    }


    //검색된 데이터가 없을때
    fun noSearchData(){
        hideKeyBoard()
        binding.editInputSearchText.text.clear()
        Toast.makeText(requireActivity(), "검색 결과가 없음", Toast.LENGTH_SHORT).show()
        startEditTextMove()
    }


    //책 검색
    private fun searchBook(query:String){
        RetrofitHelper.apiServices.getBookSearchResult(query)
            .enqueue(object : Callback<GetBookData.SearchedBooks> {
                override fun onResponse(
                    call: Call<GetBookData.SearchedBooks>,
                    response: Response<GetBookData.SearchedBooks>
                ) {
                    val searchedBooks = response.body()?.bookLists

                    if (!searchedBooks.isNullOrEmpty()) {//검색된  책들이 있을때
                        updateSearchedBookData(searchedBooks = searchedBooks)
                    } else {//아무것도 검색이 안될떄 -> 다시  motionlayout start때로  editext 돌림
                        noSearchData()
                    }
                }

                override fun onFailure(call: Call<GetBookData.SearchedBooks>, t: Throwable) {
                    Logger.v(
                        "network fail",
                        "error message -> " + t.message + "  cause ->" + t.cause
                    )
                }
            })
    }

    //키보드 숨기기
    fun hideKeyBoard(){
        binding.editInputSearchText.hideKeyboard()
    }

    //검색 editText animation  up
    private fun startEditTextMove() {
        binding.root.transitionToStart()
        binding.root.transitionToEnd()//검색 창  상단으로 up
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

}