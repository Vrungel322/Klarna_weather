package com.example.nikitaromanenko.klarna_coding_test.base

import android.content.Context
import android.support.annotation.StringRes

interface BaseView {
    fun isNetworkAvailable(): Boolean
    fun getAppContext(): Context
    fun showMessage(message: String)
    fun showMessage(@StringRes messageResId: Int)
    fun goBack()
}