package com.example.danielhorowitz.clean.presentation.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.danielhorowitz.clean.R
import com.example.danielhorowitz.clean.domain.model.Place
import kotlinx.android.synthetic.main.activity_place_details.*

class PlaceDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_details)

        val place = getPlaceFromExtras()
        val images = place?.images

        bindPlaceInfo(images, place)
    }

    private fun bindPlaceInfo(
        images: MutableList<String>?,
        place: Place?
    ) {
        images?.let {
            viewPager.adapter = ImagesAdapter(this, it)
        }

        tvTitle.text = place?.name
        tvSubtitle.text = place?.vicinity
    }

    private fun getPlaceFromExtras(): Place? = intent.getParcelableExtra(PLACE_EXTRA)

    companion object {
        const val PLACE_EXTRA = "PLACE_EXTRA"
    }
}
