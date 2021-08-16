package org.techtown.bookprojectjungsang

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.bookprojectjungsang.databinding.FragmentSearchBinding

import org.techtown.bookprojectjungsang.model.BookModelItem
import org.techtown.bookprojectjungsang.recyclerview.BookSearchRepositoryDecoration
import org.techtown.bookprojectjungsang.retrofit.BookSearchApi
import org.techtown.bookprojectjungsang.retrofit.BookSearchService
import org.techtown.bookprojectjungsang.room.BookDatabase
import org.techtown.bookprojectjungsang.viewmodel.BookSearchRepository
import org.techtown.bookprojectjungsang.viewmodel.MainViewModel
import org.techtown.bookprojectjungsang.viewmodel.MainViewModelFactory

class SearchFragment : Fragment(),BookRcyAdapter.onBookMarkCheckListener {

    //viewBinding
    private var _binding : FragmentSearchBinding? = null
    private val binding get() = _binding!!

    //viewModel
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var mBookSearchRepositoryAdapter: BookRcyAdapter

    //okhttp,retrofit
    private lateinit var bookServiceApi: BookSearchApi

    //list
    private lateinit var bookList:ArrayList<BookModelItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSet(savedInstanceState)
//        initViewModel()

    }

    //set searchButton
    private fun initSearchButton() {
        binding.searchButton.setOnClickListener {
            onSearchClick(binding.searchInput.text.toString())
        }
    }

    private fun initViewModel() {

        viewModelFactory = MainViewModelFactory(BookSearchRepository())
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(MainViewModel::class.java)

        viewModel.bookSearchRepositories.observe(requireActivity()){
//            updateRepositories(it)
        }

    }

    private fun initSet(savedInstanceState: Bundle?) {

        // API 셋해주기.
        bookServiceApi = BookSearchService.client
        bookList = ArrayList<BookModelItem>()
        mBookSearchRepositoryAdapter =
            BookRcyAdapter(requireActivity(), bookList)
            { v: View, position: Int, item:BookModelItem ->
                onBookMarkCheck(v, position, item)
            }

        binding.bookRcy.run {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = mBookSearchRepositoryAdapter
            addItemDecoration(BookSearchRepositoryDecoration(6, 6))
        }

        initSearchButton()

    }

    private fun updateRepositories(repos: ArrayList<BookModelItem>){
//        if(::mBookSearchRepositoryAdapter.isInitialized){
//            mBookSearchRepositoryAdapter.update(repos)
//        } else {
//            mBookSearchRepositoryAdapter = BookRcyAdapter(requireActivity(), repos).apply {
//                listener = object : BookRcyAdapter.OnBookSearchRepositoryClickListener{
//                    override fun onItemClick(position: Int) {
//                        mBookSearchRepositoryAdapter.getItem(position).run {
//                            openNaverBookUrl(image)
//                        }
//                    }
//                }
//            }
//
//            binding.bookRcy.run {
//                setHasFixedSize(true)
//                layoutManager = LinearLayoutManager(requireActivity())
//                adapter = mBookSearchRepositoryAdapter
//                addItemDecoration(BookSearchRepositoryDecoration(6, 6))
//
//            }
//        }
    }

    private fun openNaverBookUrl(imageUrl: String) {
        try {
            val uri = Uri.parse(imageUrl)
            Intent(Intent.ACTION_VIEW, uri).run {
                startActivity(this)
            }
        } catch (e: Exception) {

        }
    }

    private fun onSearchClick(query: String) {
//        viewModel.requestBookSearchRepositories(binding.searchToolbar.searchInput.text.toString())

        Log.d("response","onSearchClick")

        CoroutineScope(Dispatchers.IO).launch {
            Log.d("response","launch")
            bookServiceApi.getBookList(query = query).let { response ->
                Log.d("response",response.toString())
                if (response.isSuccessful) {
                    Log.d("response",response.isSuccessful.toString())
                    response.body()?.let {
                        Log.d("response",it.items.toString())
                        mBookSearchRepositoryAdapter.cleartItems()

                        bookList.clear()
                        bookList.addAll(it.items)
                        //notifydatasetChange on mainThread.
                        CoroutineScope(Dispatchers.Main).launch {
                            mBookSearchRepositoryAdapter.notifyDataSetChanged()
                        }
                    }
                } else {
                    Log.d("response",response.isSuccessful.toString())
                }
            }
        }

        binding.searchInput.text!!.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

    }

    override fun onBookMarkCheck(v: View?, position: Int, item:BookModelItem) {
        when(v?.id){
            R.id.book_mark_cb -> {
                //해당 아이템 추가.
                if((v as CheckBox).isChecked){
                    CoroutineScope(Dispatchers.IO).launch {
                        MainActivity.bookDatabase.runInTransaction {
                            MainActivity.bookDatabase.bookDao().insert(item)
                        }
                    }
                } else {//해당 아이템 삭제.
                    CoroutineScope(Dispatchers.IO).launch {
                        MainActivity.bookDatabase.runInTransaction {
                            MainActivity.bookDatabase.bookDao().delete(item)
                        }
                    }
                }
            }
        }
    }
}