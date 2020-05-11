package br.com.aisdigital.androidchallenge.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapterUtils {

    @JvmStatic
    @BindingAdapter("loadImageFromUrl")
    fun loadImageFromUrl(imageView: ImageView?, imageUrl: String?) {
        imageView?.run {
            Glide
                .with(this.context)
                .load(imageUrl.orEmpty())
                .centerCrop()
                .into(this)
        }
    }
}