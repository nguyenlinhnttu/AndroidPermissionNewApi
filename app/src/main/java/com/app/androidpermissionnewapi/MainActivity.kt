package com.app.androidpermissionnewapi

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.permissions.NewPermissions
import com.app.permissions.PermissionMultiCallback
import com.app.permissions.PermissionSingleCallback

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnSingle = findViewById<Button>(R.id.btnSingle)
        btnSingle.setOnClickListener {
            NewPermissions.requestSinglePermission(
                supportFragmentManager,
                android.Manifest.permission.CAMERA,
                object :
                    PermissionSingleCallback {
                    override fun onPermissionGranted(permission: String) {
                        Log.d(TAG, "onPermissionsGranted: $permission")
                    }

                })
        }
        val btnMultiple = findViewById<Button>(R.id.btnMultiple)
        btnMultiple.setOnClickListener {
            val permissions = arrayOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_MEDIA_IMAGES
            )
            NewPermissions.requestMultiplePermission(supportFragmentManager, permissions, object :
                PermissionMultiCallback {
                override fun onPermissionsGranted(permissions: List<String>) {
                    Log.d(TAG, "onPermissionsGranted: $permissions")
                }

            })
        }


    }
}