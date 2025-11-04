package com.rk_softwares.e_commerce.Other

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

class PermissionHelper(

    private var activity: Activity

) {

    var isCameraGranted = false
    var isGalleryGranted = false

/*
    //requesting camera and gallery permissions----------------------------------------
    fun requestCameraAndGalleryPermission(){

        val permissions = arrayOf(

            Manifest.permission.CAMERA,

            when {
                Build.VERSION.SDK_INT >= 34 -> Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
                Build.VERSION.SDK_INT == 33 -> Manifest.permission.READ_MEDIA_IMAGES
                else -> Manifest.permission.READ_EXTERNAL_STORAGE
            }

        )

        ActivityCompat.requestPermissions(activity, permissions, 200)

    }

 */

}