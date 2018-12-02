package com.example.danielhorowitz.clean.presentation.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.danielhorowitz.clean.R
import com.example.danielhorowitz.clean.domain.model.Place
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_place_details.*
import javax.inject.Inject

class PlaceDetailsActivity : AppCompatActivity(), PlaceDetailsContract.View {
    @Inject
    lateinit var presenter: PlaceDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_place_details)

        val place = getPlaceFromExtras()

        presenter.fetchPlaceDetails(place?.id)
    }

    override fun showPlaceInfo(place: Place) {
        bindPlaceInfo(place.images, place)
    }

    override fun showError(throwable: Throwable, tag: String, message: Int) {

    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    private fun bindPlaceInfo(images: MutableList<String>?, place: Place?) {
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
