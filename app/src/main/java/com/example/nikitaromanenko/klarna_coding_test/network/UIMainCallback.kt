package com.example.nikitaromanenko.klarna_coding_test.network

import android.os.Handler
import android.os.Looper
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException


abstract class UIMainCallback : Callback {

    abstract fun failure(e: IOException)

    abstract fun response(response: Response)

    override fun onFailure(call: okhttp3.Call, e: IOException) {
        mUIHandler.post { failure(e) }
    }

    @Throws(IOException::class)
    override fun onResponse(call: okhttp3.Call, response: Response) {
        mUIHandler.post { response(response) }
    }

    companion object {
        private val mUIHandler = Handler(Looper.getMainLooper())
    }
}