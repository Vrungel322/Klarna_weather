package com.example.nikitaromanenko.klarna_coding_test.base

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.nikitaromanenko.klarna_coding_test.view.CustomProgressView
import com.example.nikitaromanenko.klarna_coding_test.R
import com.example.nikitaromanenko.klarna_coding_test.extenstions.addViewToRoot
import com.example.nikitaromanenko.klarna_coding_test.extenstions.removeViewFromRoot
import com.example.nikitaromanenko.klarna_coding_test.extenstions.isNetworkAvailable


abstract class BaseActivityWithLoading : AppCompatActivity(),
    BaseViewWithLoading {

    private val progressView by lazy { CustomProgressView(this) }

    override fun showLoading() {
        addViewToRoot(progressView)
    }

    override fun showLoading(message: String) {
        progressView.setMessage(message)
        showLoading()
    }

    override fun showLoading(message: Int) {
        progressView.setMessage(getString(message))
        showLoading()
    }

    override fun dismissLoading() {
        removeViewFromRoot(progressView)
    }

    override fun showMessage(message: String) {
        runOnUiThread({ Toast.makeText(this, message, Toast.LENGTH_SHORT).show() })
    }

    override fun showMessage(messageResId: Int) {
        runOnUiThread({ Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show() })
    }

    override fun isNetworkAvailable(): Boolean {
        return applicationContext.isNetworkAvailable()
    }

    override fun getAppContext(): Context {
        return applicationContext
    }

    protected fun ifInternetAvailableDo(func: () -> Unit) {
        if (isNetworkAvailable()) {
            func.invoke()
        } else {
            showMessage(R.string.no_internet)
        }
    }

    override fun goBack() {
        onBackPressed()
    }


}