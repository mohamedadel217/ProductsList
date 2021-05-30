package android.ptc.com.ptcflixing.utils

import android.ptc.com.ptcflixing.R
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
 import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


fun ImageView.loadImage(url: String, progressBar: ProgressBar) =
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.product)
        .into(this, object : Callback {

            override fun onError(e: java.lang.Exception?) {
                e?.printStackTrace()
            }

            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }
        })
fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}