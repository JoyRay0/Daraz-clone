package com.rk_sofwares.e_commerce.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.cast.framework.media.ImagePicker
import com.google.gson.Gson
import com.rk_sofwares.e_commerce.Model.GIFimagesModel
import com.rk_sofwares.e_commerce.Model.ProfileitemModel
import com.rk_sofwares.e_commerce.Other.EdgeToEdge
import com.rk_sofwares.e_commerce.Other.GIFLoader
import com.rk_sofwares.e_commerce.Other.PermissionHelper
import com.rk_sofwares.e_commerce.R
import com.rk_sofwares.e_commerce.adapter.ProfileItems
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
    private lateinit var cv_profile_image : CardView
    private lateinit var iv_profile_image : AppCompatImageView
    private lateinit var iv_setting : AppCompatImageView

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

    //items
    private lateinit var rv_items : RecyclerView
    private var i_list : ArrayList<HashMap<String, String>> = ArrayList()
    private lateinit var i_map : HashMap<String, String>

    //others
    private lateinit var edge_to_edge : EdgeToEdge
    private lateinit var gif_loader : GIFLoader
    private lateinit var items : ProfileItems
    private lateinit var permission : PermissionHelper

    //XML id's-----------------------------------------------------

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_profile, container, false)

        //identity period-----------------------------------------------------

        //toolbar
        fl_toolbar = view.findViewById(R.id.fl_toolbar)
        cv_profile_image = view.findViewById(R.id.cv_profile_image)
        iv_profile_image = view.findViewById(R.id.iv_profile_image)
        iv_setting = view.findViewById(R.id.iv_setting)

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

        //items
        rv_items = view.findViewById(R.id.rv_items)

        //identity period-----------------------------------------------------

        edge_to_edge = EdgeToEdge(requireActivity())
        edge_to_edge.setToolBar(fl_toolbar)

        gif_loader = GIFLoader(requireActivity())

        items = ProfileItems(requireActivity(), i_list)
        rv_items.adapter = items

        Gif_images()
        item()

       // permission = PermissionHelper(requireActivity())
        cv_profile_image.setOnClickListener {




        }
        iv_setting.setOnClickListener {

            startActivity(Intent(requireActivity(), Act_setting::class.java))
        }


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

    //profiles items-------------------------------------------------
    private fun item() {

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url("https://rksoftwares.xyz/All_app/Daraz_clone/Api/All_Images.php?resource=profile_item_images")
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {

                CoroutineScope(Dispatchers.Main).launch {

                    rv_items.visibility = View.GONE

                }

            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful) {

                    val data = response.body.string()

                    try {

                        val p_item = gson.fromJson(data, ProfileitemModel::class.java)

                        if (p_item.status == "successful") {

                            for (item in p_item.images) {

                                i_map = HashMap()
                                i_map["image"] = item.image ?: ""
                                i_map["text"] = item.text ?: ""
                                i_map["item"] = item.item ?: ""
                                i_list.add(i_map)

                            }

                            CoroutineScope(Dispatchers.Main).launch {

                                rv_items.visibility = View.VISIBLE
                                items.notifyDataSetChanged()

                            }

                        }

                    } catch (e: Exception) {

                        e.printStackTrace()

                    }

                }


            }
        })

    }
}// public class========================================================