package com.example.firstapp.helper

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


private const val PERMISSION_REQUEST_CODE = 100
fun checkPermissions(activity: Activity, permission: String, action: () -> Unit) {
    if (ContextCompat.checkSelfPermission(activity, permission)
        !=PackageManager.PERMISSION_GRANTED)
        ActivityCompat.requestPermissions(activity, arrayOf(permission),
            PERMISSION_REQUEST_CODE
        )
    else action()
}