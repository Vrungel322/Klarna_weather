package com.example.nikitaromanenko.klarna_coding_test.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.example.nikitaromanenko.klarna_coding_test.R

private const val MY_PERMISSIONS_REQUEST_LOCATION = 99

/**
 * Class for get permission from user
 */
class PermissionHandler {

    private var permissionGrantedCallback = {}
    private var permissionDeniedCallback = {}
    private lateinit var permission: String

    /**
     * @param activity From what activity this method was called
     * @param grantAccessCallback What need do if user grant permission
     * @param denieAccessCallback What need do if user denied permission
     * @param permission Permission that need to be asked , type like Manifest.permission. ...
     * @return true if requested [permission] granted, false - if denied
     */
    fun checkLocationPermission(activity: Activity, grantAccessCallback: () -> Unit, denieAccessCallback: () -> Unit, permission: String): Boolean {
        permissionGrantedCallback = grantAccessCallback
        permissionDeniedCallback = denieAccessCallback
        this.permission = permission
        return if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                explaintWhyNeedPermission(activity)
            } else {
                askPermission(activity)
            }
            false
        } else {
            grantAccessCallback.invoke()
            true
        }
    }

    /**
     * Method need to be called from [onRequestPermissionsResult]
     */
    fun handlePermission(requestCode: Int, permissions: Array<String>, grantResults: IntArray, activity: Activity) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
                    ) {
                        permissionGrantedCallback.invoke()
                    }
                } else {
                    checkLocationPermission(activity, permissionGrantedCallback, permissionDeniedCallback, permission)

                }
                return
            }
        }
    }

    private fun explaintWhyNeedPermission(activity: Activity) {
        AlertDialog.Builder(activity)
                .setTitle(R.string.title_location_permission)
                .setMessage(R.string.text_location_permission)
                .setPositiveButton(R.string.ok) { dialogInterface, i ->
                    askPermission(activity)
                }
                .create()
                .show()
    }

    private fun askPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
                activity,
                arrayOf(permission),
                MY_PERMISSIONS_REQUEST_LOCATION
        )
    }
}