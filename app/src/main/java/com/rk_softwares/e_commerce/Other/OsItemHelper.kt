package com.rk_softwares.e_commerce.Other

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.provider.MediaStore

class OsItemHelper (

    private var activity: Activity

){

    //opening camera and gallery
    @SuppressLint("IntentReset")
    fun openCameraAndGallery(){

        //camera
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        //gallery
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryIntent.type = "image/*"

        //chooser
        val chooser = Intent.createChooser(galleryIntent, "Select Image")
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))

        activity.startActivityForResult(chooser, 1001)

    }

}