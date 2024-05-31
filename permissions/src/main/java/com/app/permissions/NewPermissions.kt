package com.app.permissions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager

/**
 * Created by NguyenLinh on 30,May,2024
 */

fun Context.isPermissionGranted(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun Context.isPermissionBlocked(permission: String): Boolean {
    return !ActivityCompat.shouldShowRequestPermissionRationale(this as Activity, permission) &&
            ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED
}

object NewPermissions {

    //For request single permission
    //Example : requestSinglePermission(Manifest.permission.CAMERA,..)
    fun requestSinglePermission(
        fm: FragmentManager,
        permission: String,
        callback: PermissionSingleCallback
    ) {
        RequestPermissionDialog.newInstance(permission, callback)
            .show(fm, "RequestPermissionDialog")
    }


    // For request multiple permission
    //Example : requestMultiplePermission(arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS),..)
    fun requestMultiplePermission(
        fm: FragmentManager,
        permissions: Array<String>,
        callback: PermissionMultiCallback
    ) {
        RequestPermissionDialog.newInstance(permissions, callback)
            .show(fm, "RequestPermissionDialog")
    }

}