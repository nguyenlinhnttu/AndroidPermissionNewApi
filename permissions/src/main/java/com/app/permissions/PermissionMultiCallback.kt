package com.app.permissions

/**
 * Created by NguyenLinh on 30,May,2024
 */
interface PermissionMultiCallback {
    //Permission Granted
    fun onPermissionsGranted(permissions: List<String>)
    //Can request permission again
    fun onPermissionsDenied(permissions: List<String>){}
    //Should show popup open setting
    fun onPermissionsBlocked(permissions: List<String>){}
}