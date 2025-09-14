package com.rk_sofwares.e_commerce.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.rk_sofwares.e_commerce.Model.ItemModel
import com.rk_sofwares.e_commerce.Model.ViewpagerModel
import com.rk_sofwares.e_commerce.R
import com.rk_sofwares.e_commerce.adapter.ItemAdapter
import com.rk_sofwares.e_commerce.adapter.ViewpagerAdapter
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import java.lang.Exception
import java.nio.file.attribute.DosFileAttributes
import java.util.ArrayList

class Fg_home : Fragment() {

    //XML id's--------------------------------------------------------------
    //toolbar
    private lateinit var iv_scan : AppCompatImageView
    private lateinit var cv_search : CardView
    private lateinit var iv_camera : AppCompatImageView
    private lateinit var tv_search_btn : AppCompatTextView
    private lateinit var iv_upload : AppCompatImageView
    private lateinit var tv_searchBar_text : AppCompatTextView

    //Recyclerview & viewpager
    private lateinit var rv_item : RecyclerView
    private lateinit var vp_image : ViewPager2

    //arraylist & hashmaps
    private var item_list : ArrayList<HashMap<String, String>> = ArrayList()
    private lateinit var item_map : HashMap<String, String>

    private var vp_list : ArrayList<HashMap<String, String>> = ArrayList()
    private lateinit var vp_map : HashMap<String, String>

    // adapter
    private lateinit var itemAdapter : ItemAdapter
    private lateinit var viewPagerAdapter : ViewpagerAdapter

    private lateinit var dotsIndicator : WormDotsIndicator

    //XML id's--------------------------------------------------------------

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fg_home, container, false)

        //identity period----------------------------------------------------

        iv_scan = view.findViewById(R.id.iv_scan);
        cv_search = view.findViewById(R.id.cv_search);
        iv_camera = view.findViewById(R.id.iv_camera);
        tv_search_btn = view.findViewById(R.id.tv_search_btn);
        iv_upload = view.findViewById(R.id.iv_upload);
        rv_item = view.findViewById(R.id.rv_item)
        tv_searchBar_text = view.findViewById(R.id.tv_searchBar_text);
        vp_image = view.findViewById(R.id.vp_image);
        dotsIndicator = view.findViewById(R.id.dotsIndicator)

        //identity period----------------------------------------------------


        itemAdapter = ItemAdapter(requireContext(), item_list)
        rv_item.adapter = itemAdapter

        viewPagerAdapter = ViewpagerAdapter(requireContext(), vp_list)
        vp_image.adapter = viewPagerAdapter
        dotsIndicator.attachTo(vp_image)


        item_image()    // showing item images

        changeSearchBarText()   //changing search bar text after 10 seconds

        viewpager()
        viewpager_infinite_loop()

        return view
    }//on create=================================================================

    //item image from server----------------------------------------
    private fun item_image(){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url("https://rksoftwares.xyz/All_app/Daraz_clone/Api/All_Images.php?resource=item")
            .build()

        client.newCall(request).enqueue(object : Callback{

            override fun onFailure(call: Call, e: IOException) {

                CoroutineScope(Dispatchers.Main).launch {

                    Toast.makeText(requireContext(), "No internet", Toast.LENGTH_SHORT).show()

                }

            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful){

                    val data = response.body.string()

                    try {

                        val itemImage = gson.fromJson(data, ItemModel::class.java)

                        if (itemImage.status == "successful"){

                            item_list.clear()

                            for (item in itemImage.images){

                                item_map = HashMap()
                                item_map["image"] = item.image ?: ""
                                item_map["text"] = item.text ?: ""
                                item_list.add(item_map)

                            }

                            CoroutineScope(Dispatchers.Main).launch {

                                itemAdapter.notifyDataSetChanged()

                            }

                        }

                    }catch (e : Exception){

                        e.printStackTrace()

                    }

                }


            }
        })

    }
    //chaining search bar text--------------------------------------------------
    private fun changeSearchBarText(){

        val texts = arrayOf("Mobile phone", "Rechargeable battery", "Home item", "T-shirt")
        var index = 0

        CoroutineScope(Dispatchers.Main).launch {

            while (true){

                val animation = TranslateAnimation(
                    0f, 0f, 50f, 0f // Xfrom, Xto, Yfrom, Yto
                )
                animation.duration = 500 // animation time
                tv_searchBar_text.startAnimation(animation)

                tv_searchBar_text.text = texts[index]
                index = (index + 1) % texts.size
                delay(10000)

            }

        }

    }

    //viewpager-------------------------------------------------------------
    private fun viewpager(){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url("https://rksoftwares.xyz/All_app/Daraz_clone/Api/All_Images.php?resource=viewpager")
            .build()

        client.newCall(request).enqueue(object : Callback{

            override fun onFailure(call: Call, e: IOException) {

                CoroutineScope(Dispatchers.Main).launch {

                    Toast.makeText(requireContext(), "No internet", Toast.LENGTH_SHORT).show()

                }

            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful){

                    val data = response.body.string()

                    try {

                        val vp_img = gson.fromJson(data, ViewpagerModel::class.java)

                        if (vp_img.status == "successful"){

                            vp_list.clear()

                            for (item in vp_img.images){

                                vp_map = HashMap()
                                vp_map["image"] = item.vp_image ?: ""
                                vp_list.add(vp_map)

                            }

                            CoroutineScope(Dispatchers.Main).launch {

                                viewPagerAdapter.notifyDataSetChanged()

                            }

                        }

                    }catch (e : Exception){

                        e.printStackTrace()

                    }

                }


            }
        })

    }

    //infinite viewpager with dot indicator
    private fun viewpager_infinite_loop(){

        CoroutineScope(Dispatchers.Main).launch {

            while (true){

                delay(5000)
                val nextItem = (vp_image.currentItem + 1) % vp_list.size
                vp_image.setCurrentItem(nextItem, true)


            }

        }

    }


}//public class=============================================================