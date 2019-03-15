package com.example.nikitaromanenko.klarna_coding_test.extenstions

import android.view.View

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.isVisible() = this.visibility == View.VISIBLE
