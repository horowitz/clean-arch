package com.example.danielhorowitz.clean.presentation.details

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.danielhorowitz.clean.R
import com.example.danielhorowitz.clean.domain.model.Place
import com.example.danielhorowitz.clean.domain.model.PlaceDetails
import com.example.danielhorowitz.clean.domain.model.Reviews
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_place_details.*
import kotlinx.android.synthetic.main.place_extra_info.view.*
import org.jetbrains.anko.contentView
import org.jetbrains.anko.design.indefiniteSnackbar
import org.jetbrains.anko.textColor
import javax.inject.Inject

class PlaceDetailsActivity : AppCompatActivity(), PlaceDetailsContract.View {
    @Inject
    lateinit var presenter: PlaceDetailsContract.Presenter

    private var reviewsAdapter: PlaceReviewsAdapter? = null

    private val reviews = mutableListOf<Reviews>()

    private var place: Place? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_place_details)

        place = getPlaceFromExtras()

        presenter.fetchPlaceDetails(place)

        reviewsAdapter = PlaceReviewsAdapter(reviews)
        val snapHelper = StartSnapHelper()
        snapHelper.attachToRecyclerView(reviewsRecyclerView)
        reviewsRecyclerView.adapter = reviewsAdapter
    }

    override fun showPlaceInfo(placeDetails: PlaceDetails) {
        content.visibility = View.VISIBLE
        bindPlaceInfo(placeDetails.place.images, placeDetails.place)
        bindReviews(placeDetails.reviews)
    }

    override fun showError(throwable: Throwable, tag: String, message: Int) {
        contentView?.indefiniteSnackbar(R.string.unexpected_error, R.string.retry) { presenter.fetchPlaceDetails(place) }
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    private fun bindReviews(reviews: List<Reviews>) {
        this.reviews.clear()

        this.reviews.addAll(reviews)
        this.reviewsAdapter?.notifyDataSetChanged()
    }

    private fun bindPlaceInfo(images: MutableList<String>?, place: Place) {
        images?.let {
            viewPager.adapter = ImagesAdapter(this, it)
        }

        tvTitle.text = place.name
        tvSubtitle.text = place.vicinity
        placeExtraInfoView.tvItemRating.textColor = ContextCompat.getColor(this, android.R.color.darker_gray)
        placeExtraInfoView.tvDistance.textColor = ContextCompat.getColor(this, android.R.color.darker_gray)
        placeExtraInfoView.bind(place)
    }

    private fun getPlaceFromExtras(): Place? = intent.getParcelableExtra(PLACE_EXTRA)

    companion object {
        const val PLACE_EXTRA = "PLACE_EXTRA"
    }
}
