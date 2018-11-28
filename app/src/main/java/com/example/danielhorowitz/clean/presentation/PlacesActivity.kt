package com.example.danielhorowitz.clean.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.danielhorowitz.clean.R
import com.example.danielhorowitz.clean.domain.model.Location
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)
        presenter.onViewReady()
    }

    override fun getCurrentLocation(): Single<Location> = locationHandler.getCurrentLocation()

    override fun showPlaces(place: List<Place>) {
    }

    override fun showError(throwable: Throwable, tag: String, message: Int) {
        Toast.makeText(this.applicationContext,R.string.unexpected_error,Toast.LENGTH_SHORT).show()
    }

    override fun dismissView() {
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }
}
