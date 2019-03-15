package com.example.nikitaromanenko.klarna_coding_test.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.nikitaromanenko.klarna_coding_test.R
import com.example.nikitaromanenko.klarna_coding_test.extenstions.show
import kotlinx.android.synthetic.main.widget_progress_view.view.*

/**
 * Use instead of deprecated ProgressDialog, it already used in BaseActivityWithLoading
 */
class CustomProgressView @JvmOverloads constructor(
        context: Context, val attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_progress_view, this)
    }

    fun setMessage(message: String) {
        tv_progress_text.show()
        tv_progress_text.setText(message)
    }
}