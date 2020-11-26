package com.onesimo.nyathi.hogwarts.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.onesimo.nyathi.hogwarts.R

fun setCircularPictureFromUrl(imageUrl: String, imageView: ImageView, context: Context?) {
    context?.let {
        Glide.with(it)
            .load(imageUrl)
            .placeholder(R.drawable.ic_hp)
            .error(R.drawable.ic_hp_error)
            .circleCrop()
            .into(imageView)
    }
}

fun setImageFromUrl(imageUrl: String, imageView: ImageView, context: Context?) {
    context?.let {
        Glide.with(it)
            .load(imageUrl)
            .placeholder(R.drawable.ic_hp)
            .error(R.drawable.ic_hp_error)
            .into(imageView)
    }
}