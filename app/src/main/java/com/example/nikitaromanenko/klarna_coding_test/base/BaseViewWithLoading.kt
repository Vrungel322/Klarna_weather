package com.example.nikitaromanenko.klarna_coding_test.base

interface BaseViewWithLoading : BaseView {
    fun showLoading()
    fun showLoading(message: String)
    fun showLoading(message: Int)
    fun dismissLoading()
}