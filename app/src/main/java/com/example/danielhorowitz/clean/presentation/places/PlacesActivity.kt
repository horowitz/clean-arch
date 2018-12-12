package com.example.danielhorowitz.clean.presentation.places

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.danielhorowitz.clean.Navigator
import com.example.danielhorowitz.clean.R
import com.example.danielhorowitz.clean.domain.model.Location
import com.example.danielhorowitz.clean.domain.model.NearbyPlaces
import com.example.danielhorowitz.clean.domain.model.Place
import com.example.danielhorowitz.clean.presentation.common.LocationHandler
import dagger.android.AndroidInjection
import io.reactivex.Single
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.contentView
import org.jetbrains.anko.design.indefiniteSnackbar
import javax.inject.Inject

class PlacesActivity : AppCompatActivity(), PlacesContract.View {
    @Inject
    lateinit var presenter: PlacesContract.Presenter

    @Inject
    lateinit var navigator: Navigator

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
        setupRecyclers()
        swipeRefreshLayout.setOnRefreshListener { presenter.fetchNearbyPlaces() }

        presenter.fetchNearbyPlaces()
    }

    override fun navigateToPlaceDetails(place: Place) {
        navigator.navigateToPlaceDetails(place)
    }

    override fun getCurrentLocation(): Single<Location> = locationHandler.getCurrentLocation()

    override fun showPlaces(places: NearbyPlaces) {
        mainContent.visibility = View.VISIBLE

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
        contentView?.indefiniteSnackbar(R.string.unexpected_error, R.string.retry) { presenter.fetchNearbyPlaces() }
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    private fun setupRecyclers() {
        barsAdapter = PlacesAdapter(bars) { presenter.onPlaceClicked(it) }
        cafesAdapter = PlacesAdapter(cafes) { presenter.onPlaceClicked(it) }
        restaurantsAdapter = PlacesAdapter(restaurants) { presenter.onPlaceClicked(it) }

        barsRecyclerView.setTitleDrawable(R.drawable.ic_local_bar)
        barsRecyclerView.recyclerView.adapter = barsAdapter

        cafesRecyclerView.setTitleDrawable(R.drawable.ic_free_breakfast)
        cafesRecyclerView.recyclerView.adapter = cafesAdapter

        restaurantsRecyclerView.setTitleDrawable(R.drawable.ic_local_dining)
        restaurantsRecyclerView.recyclerView.adapter = restaurantsAdapter
    }
}
