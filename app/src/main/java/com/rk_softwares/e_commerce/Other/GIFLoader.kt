package com.rk_softwares.e_commerce.Other

import android.app.Activity
import androidx.appcompat.widget.AppCompatImageView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.request.ImageRequest

class GIFLoader(
    private var activity: Activity) {

    fun setGIF(url : String, targetID : AppCompatImageView){

        val imageloader = ImageLoader.Builder(activity)
            .components {
                add(GifDecoder.Factory())
            }
            .build()

        val request = ImageRequest.Builder(activity)
            .data(url)
            .target(targetID)
            .build()

        imageloader.enqueue(request)


    }
}