package com.example.awfc.utils

import android.content.Context
import pub.devrel.easypermissions.EasyPermissions
import android.Manifest
import androidx.fragment.app.Fragment

object PermissionRequestUtil {
    fun hasCameraPermissions(context: Context) =
        EasyPermissions.hasPermissions(
            context,
            Manifest.permission.CAMERA
        )

    fun hasStoragePermissions(context: Context) =
        EasyPermissions.hasPermissions(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

    fun requestContactsPermission(fragment: Fragment)
    {
        EasyPermissions.requestPermissions(
            fragment,
            Manifest.permission.READ_CONTACTS,
            1
        )
    }
}