package br.com.aisdigital.androidchallenge.utils

import android.view.View
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

    @JvmStatic
    @BindingAdapter("backgroundFromResource")
    fun backgroundFromResource(view: View?, color: Int) {
        view?.apply {
            background = context.getDrawable(color)
        }
    }

    @JvmStatic
    @BindingAdapter("sourceFromId")
    fun sourceFromId(imageView: ImageView?, id: Int) {
        imageView?.apply {
            setImageResource(id)
        }
    }
}
