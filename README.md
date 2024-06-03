# **Android Permission New API**
[![](https://jitpack.io/v/nguyenlinhnttu/AndroidPermissionNewApi.svg)](https://jitpack.io/#nguyenlinhnttu/AndroidPermissionNewApi)
> [!NOTE]
> This is a library that simplifies requesting permissions using the new Android API.<br>
> By using DialogFragment as an intermediary layer, requesting permissions with this library will not affect the application's lifecycle.<br>
> Ref: https://developer.android.com/training/permissions/requesting
# **CallBack functions**
- All callback functions are **optional**. You can implement any callback you want. That makes your code cleaner.
# **How to use:**
- With single permisison like :  **Manifest.permission.CAMERA**
```
   NewPermissions.requestSinglePermission(
                supportFragmentManager,
                android.Manifest.permission.CAMERA,
                object :
                    PermissionSingleCallback {
                    override fun onPermissionGranted(permission: String) {
                        Log.d(TAG, "onPermissionsGranted: $permission")
                    }

                    override fun onPermissionDenied(permission: String) {
                        Log.d(TAG, "onPermissionDenied: $permission")
                    }

                    override fun onPermissionBlocked(permission: String) {
                        Log.d(TAG, "onPermissionBlocked: $permission")
                    }

		   override fun onRequestDone(isGranted: Boolean, isBlocked: Boolean) {
                        Log.d(TAG, "onRequestDone: $isGranted - $isBlocked")
                    }

                })
```
- With multiple permission:
```
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

                override fun onPermissionsDenied(permissions: List<String>) {
                    Log.d(TAG, "onPermissionsDenied: $permissions")
                }

                override fun onPermissionsBlocked(permissions: List<String>) {
                    Log.d(TAG, "onPermissionsBlocked: $permissions")
                }

		 override fun onRequestDone(
                    granted: List<String>,
                    denied: List<String>,
                    blocked: List<String>
                ) {
                    Log.d(TAG, "onRequestDone: $granted - $denied -$blocked")
                }


            })
```
# **Add to project**
Step 1. Add it in your root build.gradle at the end of repositories:
```
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
			//for: kts style
			//maven {
            		//	url = uri("https://jitpack.io")
			// }
		}
	}


```

Step 2. Add the dependency
```
dependencies {
	        implementation 'com.github.nguyenlinhnttu:AndroidPermissionNewApi:version'
	}

```
# **Another Method Supported:**
- Manual open app setting after permission blocked
```
NewPermissions.openSettingPermission()
```
- Check permission state before request
 ```
NewPermissions.isPermissionGranted()
NewPermissions.isPermissionBlocked()
 ```
