> [!NOTE]
> This is a library that simplifies requesting permissions using the new Android API.<br>
> By using DialogFragment as an intermediary layer, requesting permissions with this library will not affect the application's lifecycle.<br>
> Ref: https://developer.android.com/training/permissions/requesting


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
		}
	}


```
Step 2. Add the dependency
```
dependencies {
	        implementation 'com.github.nguyenlinhnttu:AndroidPermissionNewApi:Tag'
	}

```
