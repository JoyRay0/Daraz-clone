package com.rk_sofwares.e_commerce.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.google.gson.Gson
import com.rk_sofwares.e_commerce.Model.GIFimagesModel
import com.rk_sofwares.e_commerce.Other.EdgeToEdge
import com.rk_sofwares.e_commerce.Other.GIFLoader
import com.rk_sofwares.e_commerce.R
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


class Fg_profile : Fragment() {

    //XML id's-----------------------------------------------------

    //toolbar
    private lateinit var fl_toolbar : FrameLayout

    //gifs
    private lateinit var fl_gifs : FrameLayout
    private lateinit var iv_gif1 : AppCompatImageView
    private lateinit var tv_gif_title1 : AppCompatTextView
    private lateinit var tv_gif_info1 : AppCompatTextView
    private lateinit var tv_btn1 : AppCompatTextView
    private lateinit var iv_gif2 : AppCompatImageView
    private lateinit var tv_gif_title2 : AppCompatTextView
    private lateinit var tv_gif_info2 : AppCompatTextView
    private lateinit var tv_btn2 : AppCompatTextView

    //others
    private lateinit var edge_to_edge : EdgeToEdge
    private lateinit var gif_loader : GIFLoader

    //XML id's-----------------------------------------------------

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_profile, container, false)

        //identity period-----------------------------------------------------

        //toolbar
        fl_toolbar = view.findViewById(R.id.fl_toolbar)

        //gifs
        fl_gifs = view.findViewById(R.id.fl_gifs)
        iv_gif1 = view.findViewById(R.id.iv_gif1)
        tv_gif_title1 = view.findViewById(R.id.tv_gif_title1)
        tv_gif_info1 = view.findViewById(R.id.tv_gif_info1)
        tv_btn1 = view.findViewById(R.id.tv_btn1)
        iv_gif2 = view.findViewById(R.id.iv_gif2)
        tv_gif_title2 = view.findViewById(R.id.tv_gif_title2)
        tv_gif_info2 = view.findViewById(R.id.tv_gif_info2)
        tv_btn2 = view.findViewById(R.id.tv_btn2)

        //identity period-----------------------------------------------------

        edge_to_edge = EdgeToEdge(requireActivity())
        edge_to_edge.setToolBar(fl_toolbar)

        gif_loader = GIFLoader(requireActivity())


        Gif_images()

        return view
    }// on create======================================================

    //load GIF images from server--------------------------------------------------
    private fun Gif_images(){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url("https://rksoftwares.xyz/All_app/Daraz_clone/Api/All_Images.php?resource=gifs")
            .build()

        client.newCall(request).enqueue(object : Callback{

            override fun onFailure(call: Call, e: IOException) {

                CoroutineScope(Dispatchers.Main).launch {

                    fl_gifs.visibility = View.GONE

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

                                fl_gifs.visibility = View.VISIBLE

                            }

                        }

                    }catch (e : Exception){

                        e.printStackTrace()

                    }

                }


            }
        })

    }

}// public class========================================================