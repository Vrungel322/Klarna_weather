package com.example.nikitaromanenko.klarna_coding_test.base

abstract class BasePresenter<T : BaseView> {

    var view: T? = null

    fun attachView(v: T) {
        view = v
    }

    fun detachView() {
        view = null
    }

    open protected fun processError(error: Throwable) {
        error.printStackTrace()
    }
}