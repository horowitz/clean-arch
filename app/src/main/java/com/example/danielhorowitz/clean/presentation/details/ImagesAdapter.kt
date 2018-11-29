package com.example.danielhorowitz.clean.presentation.details

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.danielhorowitz.clean.R
import com.squareup.picasso.Picasso


class ImagesAdapter(context: Context, private val images: List<String>) : PagerAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.place_image, view, false)!!
        val imageView = imageLayout.findViewById<View>(R.id.image) as ImageView


        loadCardImage(images[position], imageView)

        view.addView(imageLayout, 0)
        return imageLayout
    }

    fun loadCardImage(url: String, image: ImageView) {
        Picasso.get()
            .load(url)
            .centerCrop()
            .fit()
            .noFade()
            .into(image)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

}