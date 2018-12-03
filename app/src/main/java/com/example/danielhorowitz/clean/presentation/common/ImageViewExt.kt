package com.example.danielhorowitz.clean.presentation.common

import android.widget.ImageView
import com.example.danielhorowitz.clean.R
import com.squareup.picasso.Picasso

fun ImageView.loadCircular(url: String, placeholder: Int? = null) {
    if (url.isNotBlank()) {
        val picasso = Picasso.get().load(url).transform(CircleTransform())
        placeholder?.let { picasso.placeholder(it).into(this) } ?: run { picasso.into(this) }
    } else {
        placeholder?.let { Picasso.get().load(R.drawable.ic_image).into(this) }
    }
}

fun ImageView.load(url: String, placeholder: Int? = null) {
    if (url.isNotBlank()) {
        val picasso = Picasso.get().load(url)
        placeholder?.let { picasso.placeholder(it).into(this) } ?: run { picasso.into(this) }
    } else {
        placeholder?.let { Picasso.get().load(R.drawable.ic_image).into(this) }
    }
}