package org.techtown.bookprojectjungsang

import android.content.Context
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatCheckBox
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

class BookMarkedAdapter(private val mContext: Context,
                        private var mItems: ArrayList<BookModelItem>
) : RecyclerView.Adapter<BookMarkedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.book_marked_item, parent, false)
        return BookMarkedViewHolder(view)
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

    private fun getDifResult(updated: List<BookModelItem>): DiffUtil.DiffResult{
        val difCallBack = BookSearchRepositoryDiffCallBack(mItems, updated)
        return DiffUtil.calculateDiff(difCallBack)
    }


    inner class BookMarkedViewHolder(view: View) : ViewHolder(view) {

        private val bookImageView: AppCompatImageView = view.findViewById(R.id.book_iv)
        private val bookTitleTextView: AppCompatTextView = view.findViewById(R.id.name_book_tv)

        init {

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