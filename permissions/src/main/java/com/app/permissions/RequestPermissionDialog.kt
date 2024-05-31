package com.app.permissions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment

/**
 * Created by NguyenLinh on 30,May,2024
 */
class RequestPermissionDialog : DialogFragment() {
    private  var singleLauncher: ActivityResultLauncher<String>?= null
    private var singleCallback: PermissionSingleCallback? = null
    private lateinit var permission: String

    private  var multipleLauncher: ActivityResultLauncher<Array<String>>?= null
    private var multipleCallback: PermissionMultiCallback? = null
    private lateinit var permissions: Array<String>

    companion object {
        //For single permission request
        private const val ARG_SINGLE_PERMISSION = "permission"
        fun newInstance(
            permission: String, callback: PermissionSingleCallback
        ): RequestPermissionDialog {
            val fragment = RequestPermissionDialog()
            val args = Bundle().apply { putString(ARG_SINGLE_PERMISSION, permission) }
            fragment.arguments = args
            fragment.singleCallback = callback
            return fragment
        }

        //For multiple permission request
        private const val ARG_MULTI_PERMISSION = "permissions"
        fun newInstance(
            permissions: Array<String>, multipleCallback: PermissionMultiCallback
        ): RequestPermissionDialog {
            val fragment = RequestPermissionDialog()
            val args = Bundle().apply { putStringArray(ARG_MULTI_PERMISSION, permissions) }
            fragment.arguments = args
            fragment.multipleCallback = multipleCallback
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Return result of single permission request
        singleLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    singleCallback?.onPermissionGranted(permission)
                } else {
                    if (shouldShowRequestPermissionRationale(permission)) {
                        singleCallback?.onPermissionDenied(permission)
                    } else {
                        singleCallback?.onPermissionBlocked(permission)
                    }
                }
                dismiss()
            }

        //Return result of multiple permission request
        multipleLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
                val granted = result.filterValues { it }.keys.toList()
                val denied = result.filterValues { !it }.keys.toList()

                val blocked = denied.filter { !shouldShowRequestPermissionRationale(it) }
                val deniedPermissions = denied - blocked

                if (granted.isNotEmpty()) multipleCallback?.onPermissionsGranted(granted)
                if (deniedPermissions.isNotEmpty()) multipleCallback?.onPermissionsDenied(deniedPermissions)
                if (blocked.isNotEmpty()) multipleCallback?.onPermissionsBlocked(blocked)

                dismiss()
            }
    }


    override fun onResume() {
        super.onResume()
        // Set the dialog style to STYLE_NO_FRAME
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val params = dialog?.window?.attributes
        params?.width = ViewGroup.LayoutParams.MATCH_PARENT
        params?.height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params as android.view.WindowManager.LayoutParams
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_request_permission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments?.containsKey(ARG_SINGLE_PERMISSION) == true) {
            //Request single permission
            permission = arguments?.getString(ARG_SINGLE_PERMISSION)
                ?: throw IllegalArgumentException("Permission is required")
            singleLauncher?.launch(permission)
        } else {
            //Request multiple permission
            permissions = arguments?.getStringArray(ARG_MULTI_PERMISSION)
                ?: throw IllegalArgumentException("Permissions are required")
            multipleLauncher?.launch(permissions)
        }

    }
}