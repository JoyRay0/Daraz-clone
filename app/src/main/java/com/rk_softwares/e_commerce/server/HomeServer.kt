package com.rk_softwares.e_commerce.server

import android.app.Activity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.rk_softwares.e_commerce.Other.Cache
import com.rk_softwares.e_commerce.Other.DomainHelper
import com.rk_softwares.e_commerce.adapter.Categories
import com.rk_softwares.e_commerce.adapter.ItemAdapter
import com.rk_softwares.e_commerce.adapter.ViewpagerAdapter
import com.rk_softwares.e_commerce.model.CategoriesModel
import com.rk_softwares.e_commerce.model.CouponModel
import com.rk_softwares.e_commerce.model.FlashSaleModel
import com.rk_softwares.e_commerce.model.ItemModel
import com.rk_softwares.e_commerce.model.ViewpagerModel
import com.squareup.picasso.Picasso
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
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
import kotlin.collections.set

class HomeServer(

    private var activity: Activity

) {

    private var cache = Cache(activity, "Fg_home")

    fun itemImage(rv : RecyclerView, fl : FrameLayout, list : ArrayList<HashMap<String, String>>, item : ItemAdapter){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url(DomainHelper.getItemLink())
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {

                CoroutineScope(Dispatchers.Main).launch {

                    rv.visibility = View.GONE
                    fl.visibility = View.GONE

                }

            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful){

                    val data = response.body.string()

                    try {

                        val itemImage = gson.fromJson(data, ItemModel::class.java)

                        if (itemImage.status == "successful"){

                            list.clear()

                            for (item in itemImage.images){

                                val item_map : HashMap<String, String> = HashMap()
                                item_map["image"] = item.image ?: ""
                                item_map["text"] = item.text ?: ""
                                list.add(item_map)

                            }

                            cache.setCache("item_image", gson.toJson(itemImage))

                            CoroutineScope(Dispatchers.Main).launch {

                                item.notifyDataSetChanged()

                                rv.visibility = View.VISIBLE
                                fl.visibility = View.GONE

                            }

                        }

                    }catch (e : Exception){

                        e.printStackTrace()

                    }

                }


            }
        })

    }

    fun viewPagerImage(vp : ViewPager2, fl : FrameLayout, list : ArrayList<HashMap<String, String>>, adapter : ViewpagerAdapter ,dot : WormDotsIndicator){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url(DomainHelper.getViewpagerLink())
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {

                CoroutineScope(Dispatchers.Main).launch {

                    vp.visibility = View.GONE
                    fl.visibility = View.GONE
                    dot.visibility = View.GONE
                }

            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful){

                    val data = response.body.string()

                    try {

                        val vp_img = gson.fromJson(data, ViewpagerModel::class.java)

                        if (vp_img.status == "successful"){

                            list.clear()

                            for (item in vp_img.images){

                                val vp_map : HashMap<String, String> = HashMap()
                                vp_map["image"] = item.vp_image
                                list.add(vp_map)

                            }

                            cache.setCache("vp_image", gson.toJson(vp_img))

                            CoroutineScope(Dispatchers.Main).launch {

                                vp.visibility = View.VISIBLE
                                dot.visibility = View.VISIBLE
                                fl.visibility = View.GONE
                                adapter.notifyDataSetChanged()

                            }

                        }

                    }catch (e : Exception){

                        e.printStackTrace()

                    }

                }


            }
        })


    }

    fun couponImage(fl : FrameLayout, iv : AppCompatImageView){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url(DomainHelper.getCouponLink())
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

                        val itemImage = gson.fromJson(data, CouponModel::class.java)

                        if (itemImage.status == "successful"){

                            cache.setCache("coupon_image", gson.toJson(itemImage))

                            CoroutineScope(Dispatchers.Main).launch {

                                fl.visibility = View.VISIBLE
                                iv.visibility = View.VISIBLE

                                Picasso.get().load(itemImage.images[0].oth_image).into(iv)

                            }

                        }

                    }catch (e : Exception){

                        e.printStackTrace()

                    }

                }


            }
        })

    }

    fun flashSaleImage(
        fl : FrameLayout,
        iv1 : AppCompatImageView,
        tv1 : AppCompatTextView,
        tvp1 : AppCompatTextView,
        tvd1 : AppCompatTextView,
        iv2 : AppCompatImageView,
        tv2 : AppCompatTextView,
        tvp2 : AppCompatTextView,
        tvd2 : AppCompatTextView,
        iv3 : AppCompatImageView,
        tv3 : AppCompatTextView,
        tvp3 : AppCompatTextView,
        tvd3 : AppCompatTextView,
        ){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url(DomainHelper.getFlashSaleLink())
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

                        val itemImage = gson.fromJson(data, FlashSaleModel::class.java)

                        if (itemImage.status == "successful"){

                            cache.setCache("flash_sale_image", gson.toJson(itemImage))

                            CoroutineScope(Dispatchers.Main).launch {

                                fl.visibility = View.VISIBLE

                                if (itemImage.images.isNotEmpty()){

                                    Picasso.get().load(itemImage.images[0].sale_image).into(iv1)
                                    tv1.text = itemImage.images[0].text
                                    tvp1.text = itemImage.images[0].price
                                    tvd1.text = itemImage.images[0].discount

                                    Picasso.get().load(itemImage.images[1].sale_image).into(iv2)
                                    tv2.text = itemImage.images[1].text
                                    tvp2.text = itemImage.images[1].price
                                    tvd2.text = itemImage.images[1].discount

                                    Picasso.get().load(itemImage.images[2].sale_image).into(iv3)
                                    tv3.text = itemImage.images[2].text
                                    tvp3.text = itemImage.images[2].price
                                    tvd3.text = itemImage.images[2].discount

                                }


                            }

                        }

                    }catch (e : Exception){

                        e.printStackTrace()

                    }

                }


            }
        })

    }

    fun categoryImage(fl : FrameLayout, list : ArrayList<HashMap<String, String>>, adapter : Categories){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url(DomainHelper.getCategoryLink())
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

                        val c_img = gson.fromJson(data, CategoriesModel::class.java)

                        if (c_img.status == "successful"){

                            list.clear()

                            for (item in c_img.images){

                                val cate_map : HashMap<String, String> = HashMap()
                                cate_map["image"] = item.cate_image ?: ""
                                cate_map["text"] = item.text ?: ""
                                list.add(cate_map)

                            }

                            cache.setCache("cate_image", gson.toJson(c_img))

                            CoroutineScope(Dispatchers.Main).launch {

                                fl.visibility = View.VISIBLE
                                adapter.notifyDataSetChanged()

                            }

                        }

                    }catch (e : Exception){

                        e.printStackTrace()

                    }

                }

            }
        })

    }

    fun productImage(){}
}