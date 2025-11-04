package com.rk_softwares.e_commerce.server

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rk_softwares.e_commerce.Other.DomainHelper
import com.rk_softwares.e_commerce.Other.GIFLoader
import com.rk_softwares.e_commerce.model.GIFimagesModel
import com.rk_softwares.e_commerce.model.ProfileitemModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import java.lang.Exception
import java.util.ArrayList

class ProfileServer(

    private var activity: Activity

) {

    private var gif_loader = GIFLoader(activity)

    fun gifLoader(
        fl : FrameLayout,
        iv_gif1 : AppCompatImageView,
        tv_gif_title1 : AppCompatTextView,
        tv_gif_info1 : AppCompatTextView,
        tv_btn1 : AppCompatTextView,
        iv_gif2 : AppCompatImageView,
        tv_gif_title2 : AppCompatTextView,
        tv_gif_info2 : AppCompatTextView,
        tv_btn2 : AppCompatTextView,
        ){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url(DomainHelper.getGifLink())
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {

                CoroutineScope(Dispatchers.Main).launch {

                    fl.visibility = View.GONE

                }

            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful){

                    val data = response.body.string()

                    try {

                        val GIF_img = gson.fromJson(data, GIFimagesModel::class.java)

                        if (GIF_img.status == "successful"){

                            gif_loader.setGIF(GIF_img.images[0].gif.toString(), iv_gif1)
                            tv_gif_title1.text = GIF_img.images[0].gif_title
                            tv_gif_info1.text = GIF_img.images[0].gif_information
                            tv_btn1.text = GIF_img.images[0].gif_button

                            gif_loader.setGIF(GIF_img.images[1].gif.toString(), iv_gif2)
                            tv_gif_title2.text = GIF_img.images[1].gif_title
                            tv_gif_info2.text = GIF_img.images[1].gif_information
                            tv_btn2.text = GIF_img.images[1].gif_button

                            CoroutineScope(Dispatchers.Main).launch {

                                fl.visibility = View.VISIBLE

                            }

                        }

                    }catch (e : Exception){

                        e.printStackTrace()

                    }

                }


            }
        })

    }

    fun items(rv : RecyclerView, i_list : ArrayList<HashMap<String, String>>){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url(DomainHelper.getProfileItemLink())
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {

                CoroutineScope(Dispatchers.Main).launch {

                    rv.visibility = View.GONE

                }

            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful) {

                    val data = response.body.string()

                    try {

                        val p_item = gson.fromJson(data, ProfileitemModel::class.java)

                        i_list.clear()

                        if (p_item.status == "successful") {

                            for (item in p_item.images) {

                                val i_map : HashMap<String, String>  = HashMap()
                                i_map["image"] = item.image ?: ""
                                i_map["text"] = item.text ?: ""
                                i_map["item"] = item.item ?: ""
                                i_list.add(i_map)

                            }

                            CoroutineScope(Dispatchers.Main).launch {

                                rv.visibility = View.VISIBLE

                            }

                        }

                    } catch (e: Exception) {

                        e.printStackTrace()

                    }

                }


            }
        })

    }

}