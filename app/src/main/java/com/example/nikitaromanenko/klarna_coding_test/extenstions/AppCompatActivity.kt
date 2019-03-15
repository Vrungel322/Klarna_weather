package com.example.nikitaromanenko.klarna_coding_test.extenstions

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup

fun AppCompatActivity.addViewToRoot(view: View) {
    val v: ViewGroup = findViewById(android.R.id.content)
    if (view.windowToken != null) {
        v.removeView(view)
    }
    if (view.windowToken == null) {
        v.addView(view)
    }
}

fun AppCompatActivity.removeViewFromRoot(view: View) {
    val v: ViewGroup = findViewById(android.R.id.content)
    if (view.windowToken != null) {
        v.removeView(view)
    }
}