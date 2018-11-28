package com.example.danielhorowitz.clean.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.danielhorowitz.clean.R
import com.example.danielhorowitz.clean.domain.model.Place
import javax.inject.Inject

class PlacesActivity : AppCompatActivity(), PlacesContract.View {
    @Inject
    lateinit var presenter: PlacesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showPlaces(place: List<Place>) {
    }

    override fun showError(throwable: Throwable, tag: String, message: Int) {
    }

    override fun dismissView() {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }


}
