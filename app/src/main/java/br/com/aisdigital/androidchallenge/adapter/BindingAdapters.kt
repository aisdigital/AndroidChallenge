package br.com.aisdigital.androidchallenge.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class BindingAdapters {

    companion object {

        @BindingAdapter("srcFromUrl")
        @JvmStatic
        fun srcFromUrl(view: ImageView, url: String) {
            Glide.with(view.context)
                .load(url)
                .into(view)
        }
    }
}