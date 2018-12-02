package com.example.danielhorowitz.clean.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.danielhorowitz.clean.R
import com.example.danielhorowitz.clean.domain.model.Place
import kotlinx.android.synthetic.main.place_extra_info.view.*

class PlaceExtraInfo @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    init {
        LayoutInflater.from(context).inflate(R.layout.place_extra_info, this, true)
    }

    fun bind(place: Place) {
        tvItemRating.text = place.rating.toString()
        ivOpenClosed.setImageResource(if (place.openNow) R.drawable.ic_open else R.drawable.ic_closed)
    }
}