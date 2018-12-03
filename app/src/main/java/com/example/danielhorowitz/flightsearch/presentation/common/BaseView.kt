package com.example.danielhorowitz.flightsearch.presentation.common

import com.example.danielhorowitz.flightsearch.R


interface BaseView {
    fun showError(throwable: Throwable, tag: String = "", message: Int = R.string.unexpected_error )
    fun showLoading()
    fun hideLoading()
}
