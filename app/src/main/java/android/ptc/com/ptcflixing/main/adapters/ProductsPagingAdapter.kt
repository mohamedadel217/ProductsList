package android.ptc.com.ptcflixing.main.adapters

import android.content.Context
import android.graphics.Paint
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.utils.loadImage
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ResultsItem


class ProductsPagingAdapter(var context: Context) :
    PagingDataAdapter<ResultsItem, ProductsPagingAdapter.ProductHolder>(ProductComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(
            LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val phone = getItem(position)
        if (phone != null) {
            holder.phoneBrandTextView?.text = phone.brand
            holder.phoneNameTextView?.text = phone.name
            holder.phonePriceTextView?.text = "$ " + phone.special_price.toString()
            holder.phoneOldPriceTextView?.text = "$ " + phone.price.toString()
            holder.phoneDiscountTextView?.text = "-" + phone.max_saving_percentage.toString() + "%"
            holder.ratingBar?.rating = phone.rating_average!!
            if (phone.max_saving_percentage != 0) {
                holder.phoneOldPriceTextView?.getPaintFlags()
                    ?.or(Paint.STRIKE_THRU_TEXT_FLAG)?.let {
                        holder.phoneOldPriceTextView?.setPaintFlags(
                            it
                        )
                    }


            }
            phone.image?.let {
                holder.productImage?.loadImage(
                    it,
                    holder.progressBar
                )
            }


        }

    }


    inner class ProductHolder(v: View) : RecyclerView.ViewHolder(v) {
        var productImage: ImageView? = null
        var phoneBrandTextView: TextView? = null
        var phoneNameTextView: TextView? = null
        var phonePriceTextView: TextView? = null
        var phoneOldPriceTextView: TextView? = null
        var phoneDiscountTextView: TextView? = null
        var ratingBar: RatingBar? = null
        var progressBar: ProgressBar 


        init {
            productImage = v.findViewById(R.id.phoneImageView)
            phoneBrandTextView = v.findViewById(R.id.phoneBrandTextView)
            phoneNameTextView = v.findViewById(R.id.phoneNameTextView)
            phonePriceTextView = v.findViewById(R.id.phonePriceTextView)
            phoneOldPriceTextView = v.findViewById(R.id.phoneOldPriceTextView)
            phoneDiscountTextView = v.findViewById(R.id.phoneDiscountTextView)
            ratingBar = v.findViewById(R.id.ratingBar)
            progressBar = v.findViewById(R.id.progressBar)


        }
    }

    object ProductComparator : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem == newItem
        }
    }


}