package org.techtown.bookprojectjungsang

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.techtown.bookprojectjungsang.model.BookModel
import org.techtown.bookprojectjungsang.model.BookModelItem
import org.techtown.bookprojectjungsang.viewmodel.BookSearchRepository
import org.techtown.bookprojectjungsang.viewmodel.BookSearchRepositoryDiffCallBack

class BookRcyAdapter(private val mContext: Context,
                     private var mItems: ArrayList<BookModelItem>
) : RecyclerView.Adapter<BookRcyAdapter.ViewHolder>() {

    interface OnBookSearchRepositoryClickListener {
        fun onItemClick(position: Int)
    }

    var listener: OnBookSearchRepositoryClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.book_item, parent, false)
        return BookViewHolder(view, listener = listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mItems[position], position)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun cleartItems(){
        mItems.clear()
    }

    fun getItem(position: Int) = mItems[position]

    fun update(updated : ArrayList<BookModelItem>) {
        CoroutineScope(Dispatchers.Main).launch {
            val difResult = async(Dispatchers.IO){
                getDifResult(updated)
            }
            mItems = updated
            difResult.await().dispatchUpdatesTo(this@BookRcyAdapter)
        }
    }

    private fun getDifResult(updated: List<BookModelItem>): DiffUtil.DiffResult{
        val difCallBack = BookSearchRepositoryDiffCallBack(mItems, updated)
        return DiffUtil.calculateDiff(difCallBack)
    }


    inner class BookViewHolder(view: View, listener: OnBookSearchRepositoryClickListener?) : ViewHolder(view) {

        private val bookImageView: AppCompatImageView = view.findViewById(R.id.book_iv)
        private val bookTitleTextView: AppCompatTextView = view.findViewById(R.id.name_book_tv)

        init {
            view.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }

        override fun bind(item: BookModelItem, position: Int) {

            item.run {
                Glide.with(mContext)
                    .load(image)
                    .into(bookImageView)
                bookTitleTextView.text = Html.fromHtml(title)
            }
        }

    }

    abstract inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal abstract fun bind(item: BookModelItem,
                                   position: Int)
    }

}