package br.com.aisdigital.androidchallenge.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

const val thumbnailMultiplier = 0.1f

@BindingAdapter("imageUrl")
fun ImageView.setImage(url: String?) =
    if (!url.isNullOrEmpty())
        Glide.with(this)
            .load(url)
            .centerCrop()
            .into(this)
            .let { Unit }
    else
        Unit

@BindingAdapter("thumbnailUrl")
fun ImageView.setThumbnail(url: String?) =
    if (!url.isNullOrEmpty())
        Glide.with(this)
            .load(url)
            .centerCrop()
            .thumbnail(thumbnailMultiplier)
            .into(this)
            .let { Unit }
    else Unit