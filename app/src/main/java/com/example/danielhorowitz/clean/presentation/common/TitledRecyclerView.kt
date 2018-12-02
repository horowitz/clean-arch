package com.example.danielhorowitz.clean.presentation.common

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.danielhorowitz.clean.R
import kotlinx.android.synthetic.main.titled_recycler_view.view.*
import org.jetbrains.anko.textSizeDimen

class TitledRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
    val recyclerView: RecyclerView

    init {
        LayoutInflater.from(context).inflate(R.layout.titled_recycler_view, this, true)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.addItemDecoration(EqualSpacingItemDecoration(10))
        obtainAttributes(attrs)
    }

    fun setTitle(text: String) {
        tvTitle.text = text
    }

    fun setTitleDrawable(@DrawableRes id: Int) {
        val drawable = ContextCompat.getDrawable(context, id)
        tvTitle.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
    }

    private fun obtainAttributes(attrs: AttributeSet?) {
        if (attrs != null) {
            val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.TitledRecyclerView, 0, 0)
            try {
                val titleText = typedArray.getString(R.styleable.TitledRecyclerView_title)
                    ?: ""
                val textSize = typedArray.getResourceId(
                    R.styleable.TitledRecyclerView_textSize,
                    R.dimen.default_titled_recycler_text_size
                )
                tvTitle.text = titleText
                tvTitle.textSizeDimen = textSize
            } finally {
                typedArray.recycle()
            }
        }
    }
}