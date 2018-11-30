package com.example.danielhorowitz.clean.presentation.places

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.danielhorowitz.clean.R
import com.example.danielhorowitz.clean.domain.model.Location
import com.example.danielhorowitz.clean.domain.model.NearbyPlaces
import com.example.danielhorowitz.clean.domain.model.Place
import com.example.danielhorowitz.clean.presentation.common.LocationHandler
import dagger.android.AndroidInjection
import io.reactivex.Single
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class PlacesActivity : AppCompatActivity(), PlacesContract.View {
    @Inject
    lateinit var presenter: PlacesContract.Presenter

    @Inject
    lateinit var locationHandler: LocationHandler

    private var barsAdapter: PlacesAdapter? = null
    private var cafesAdapter: PlacesAdapter? = null
    private var restaurantsAdapter: PlacesAdapter? = null

    private val bars: MutableList<Place> = mutableListOf()
    private val cafes: MutableList<Place> = mutableListOf()
    private val restaurants: MutableList<Place> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)
        presenter.onViewReady()

        barsAdapter = PlacesAdapter(bars) { presenter.onPlaceClicked(it) }
        cafesAdapter = PlacesAdapter(cafes) { presenter.onPlaceClicked(it) }
        restaurantsAdapter = PlacesAdapter(restaurants) { presenter.onPlaceClicked(it) }

        barsRecyclerView.recyclerView.adapter = barsAdapter
        cafesRecyclerView.recyclerView.adapter = cafesAdapter
        restaurantsRecyclerView.recyclerView.adapter = restaurantsAdapter
    }

    override fun getCurrentLocation(): Single<Location> = locationHandler.getCurrentLocation()

    override fun showPlaces(places: NearbyPlaces) {
        this.bars.clear()
        this.cafes.clear()
        this.restaurants.clear()

        this.bars.addAll(places.bars)
        this.cafes.addAll(places.cafes)
        this.restaurants.addAll(places.restaurants)

        barsAdapter?.notifyDataSetChanged()
        cafesAdapter?.notifyDataSetChanged()
        restaurantsAdapter?.notifyDataSetChanged()
    }

    override fun showError(throwable: Throwable, tag: String, message: Int) {
        Toast.makeText(this.applicationContext, R.string.unexpected_error, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun onPause() {
        super.onPause()
        presenter.pause()
    }
}
