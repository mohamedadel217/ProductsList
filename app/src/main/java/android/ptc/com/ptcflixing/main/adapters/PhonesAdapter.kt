package android.ptc.com.ptcflixing.main.adapters

import android.graphics.Paint
import android.ptc.com.ptcflixing.databinding.ItemProductBinding
import android.ptc.com.ptcflixing.utils.loadImage
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ResultsItem

class PhonesAdapter : RecyclerView.Adapter<PhonesAdapter.PhonesViewHolder>() {

    var data: ArrayList<ResultsItem>? = null

    class PhonesViewHolder(var viewBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: ResultsItem) {
            viewBinding.phone = item
            viewBinding.invalidateAll()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhonesViewHolder {
        val viewBinding: ItemProductBinding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return PhonesViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: PhonesViewHolder, position: Int) {
        val phone = data?.get(position)
        phone?.let { holder.bind(it) }
        holder.viewBinding.progressBar.visibility = View.VISIBLE
        if (phone?.max_saving_percentage != 0) {
            holder.viewBinding.phoneOldPriceTextView.setPaintFlags(holder.viewBinding.phoneOldPriceTextView.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)


        }
        phone?.image?.let {
            holder.viewBinding.phoneImageView.loadImage(
                it,
                holder.viewBinding.progressBar
            )
        }


    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    fun getData(commingData: ArrayList<ResultsItem>?) {
        data = commingData
        notifyDataSetChanged()
    }
}