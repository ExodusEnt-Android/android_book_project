package org.techtown.bookprojectjungsang

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.bookprojectjungsang.databinding.FragmentStoreBinding
import org.techtown.bookprojectjungsang.model.BookModelItem

class StoreFragment : Fragment() {

    //viewBinding
    private var _binding : FragmentStoreBinding? = null
    private val binding get() = _binding!!

    //adapter
    private lateinit var mBookMarkedAdapter: BookMarkedAdapter

    //list of stored item.
    private lateinit var bookMarkedList:ArrayList<BookModelItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("lifeCycle","StoreFragment onCreate")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentStoreBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSet(savedInstanceState)
    }

    private fun initSet(savedInstanceState: Bundle?) {

        //맨처음 시작시 디비에 저장된거 먼저 보여준다음에 어뎁터에 바로넣어주기.
        CoroutineScope(Dispatchers.IO).launch {
            MainActivity.bookDatabase.runInTransaction {
                bookMarkedList = MainActivity.bookDatabase.bookDao().selectAllBook() as ArrayList<BookModelItem>

                CoroutineScope(Dispatchers.Main).launch {
                    //setting recyclerView And Adapter.
                    mBookMarkedAdapter = BookMarkedAdapter(requireActivity(),bookMarkedList)

                    val layoutManager = LinearLayoutManager(requireActivity())
                    layoutManager.orientation = LinearLayoutManager.HORIZONTAL
                    binding.bookMarkRcy.layoutManager = layoutManager

                    binding.bookMarkRcy.adapter = mBookMarkedAdapter
                }

            }
        }
    }

    companion object {

    }
}