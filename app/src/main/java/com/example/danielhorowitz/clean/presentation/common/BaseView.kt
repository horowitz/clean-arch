package com.example.danielhorowitz.clean.presentation.common

import com.example.danielhorowitz.clean.R


interface BaseView {
    fun showError(throwable: Throwable, tag: String = "", message: Int = R.string.unexpected_error )
    fun dismissView()
    fun showLoading()
    fun hideLoading()
}
