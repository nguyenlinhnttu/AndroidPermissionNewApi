package com.app.permissions

/**
 * Created by NguyenLinh on 30,May,2024
 */
interface PermissionSingleCallback {
    //Permission Granted
    fun onPermissionGranted(permission: String)
    //Can request permission again
    fun onPermissionDenied(permission: String)
    //Should show popup open setting
    fun onPermissionBlocked(permission: String)
}