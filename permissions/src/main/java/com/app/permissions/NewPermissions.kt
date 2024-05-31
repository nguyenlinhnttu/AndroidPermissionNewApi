package com.app.permissions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager

/**
 * Created by NguyenLinh on 30,May,2024
 */
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

    //Open setting app to manual enable permission
    fun openSettingPermission(activity: Activity) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        with(intent) {
            data = Uri.fromParts("package", activity.packageName, null)
            addCategory(Intent.CATEGORY_DEFAULT)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        }
        activity.startActivity(intent)
    }


    fun isPermissionGranted(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun isPermissionBlocked(activity: Activity, permission: String): Boolean {
        return !ActivityCompat.shouldShowRequestPermissionRationale(activity, permission) &&
                ContextCompat.checkSelfPermission(
                    activity,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
    }
}