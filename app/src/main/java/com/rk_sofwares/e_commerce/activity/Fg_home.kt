package com.rk_sofwares.e_commerce.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.rk_sofwares.e_commerce.Model.CategoriesModel
import com.rk_sofwares.e_commerce.Model.CouponModel
import com.rk_sofwares.e_commerce.Model.FlashSaleModel
import com.rk_sofwares.e_commerce.Model.ItemModel
import com.rk_sofwares.e_commerce.Model.ViewpagerModel
import com.rk_sofwares.e_commerce.Model.c_images
import com.rk_sofwares.e_commerce.R
import com.rk_sofwares.e_commerce.Other.Cache
import com.rk_sofwares.e_commerce.adapter.Categories
import com.rk_sofwares.e_commerce.adapter.ItemAdapter
import com.rk_sofwares.e_commerce.adapter.ViewpagerAdapter
import com.squareup.picasso.Picasso
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

    //item image
    private lateinit var rv_item : RecyclerView
    private lateinit var itemAdapter : ItemAdapter
    private lateinit var fl_item_image_loading : FrameLayout

    private var item_list : ArrayList<HashMap<String, String>> = ArrayList()
    private lateinit var item_map : HashMap<String, String>

    //viewpager
    private lateinit var vp_image : ViewPager2
    private lateinit var viewPagerAdapter : ViewpagerAdapter
    private lateinit var fl_vp_image_loading : FrameLayout
    private lateinit var dotsIndicator : WormDotsIndicator
    private var isViewPagerVisible : Boolean = false

    private var vp_list : ArrayList<HashMap<String, String>> = ArrayList()
    private lateinit var vp_map : HashMap<String, String>

    // others
    private lateinit var cache : Cache

    //coupon image
    private lateinit var iv_coupon_img : AppCompatImageView
    private lateinit var fl_coupon : FrameLayout

    //flash sale
    private lateinit var fl_flash_sale : FrameLayout
    private lateinit var rl_flash_sale_btn : RelativeLayout
    private lateinit var iv_image1 : AppCompatImageView
    private lateinit var tv_item_left1 : AppCompatTextView
    private lateinit var tv_image_item_price1 : AppCompatTextView
    private lateinit var tv_image_item_discount1 : AppCompatTextView

    private lateinit var iv_image2 : AppCompatImageView
    private lateinit var tv_item_left2 : AppCompatTextView
    private lateinit var tv_image_item_price2 : AppCompatTextView
    private lateinit var tv_image_item_discount2 : AppCompatTextView

    private lateinit var iv_image3 : AppCompatImageView
    private lateinit var tv_item_left3 : AppCompatTextView
    private lateinit var tv_image_item_price3 : AppCompatTextView
    private lateinit var tv_image_item_discount3 : AppCompatTextView

    //categories
    private lateinit var fl_categories : FrameLayout
    private lateinit var rl_cate_btn : RelativeLayout
    private lateinit var rv_cat : RecyclerView
    private lateinit var catrgories : Categories
    private var cate_list : ArrayList<HashMap<String, String>> = ArrayList()
    private lateinit var cate_map : HashMap<String, String>

    //XML id's--------------------------------------------------------------

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_home, container, false)

        //identity period----------------------------------------------------

        //toolbar
        iv_scan = view.findViewById(R.id.iv_scan);
        cv_search = view.findViewById(R.id.cv_search);
        iv_camera = view.findViewById(R.id.iv_camera);
        tv_search_btn = view.findViewById(R.id.tv_search_btn);
        iv_upload = view.findViewById(R.id.iv_upload);
        tv_searchBar_text = view.findViewById(R.id.tv_searchBar_text);

        //item image
        rv_item = view.findViewById(R.id.rv_item)
        fl_item_image_loading = view.findViewById(R.id.fl_item_image_loading)

        //viewpager
        vp_image = view.findViewById(R.id.vp_image);
        dotsIndicator = view.findViewById(R.id.dotsIndicator)
        fl_vp_image_loading = view.findViewById(R.id.fl_vp_image_loading)

        //coupon
        fl_coupon = view.findViewById(R.id.fl_coupon)
        iv_coupon_img = view.findViewById(R.id.iv_coupon_img)

        //flash sale
        fl_flash_sale = view.findViewById(R.id.fl_flash_sale)
        rl_flash_sale_btn = view.findViewById(R.id.rl_flash_sale_btn)
        iv_image1 = view.findViewById(R.id.iv_image1)
        tv_item_left1 = view.findViewById(R.id.tv_item_left1)
        tv_image_item_price1 = view.findViewById(R.id.tv_image_item_price1)
        tv_image_item_discount1 = view.findViewById(R.id.tv_image_item_discount1)
        iv_image2 = view.findViewById(R.id.iv_image2)
        tv_item_left2 = view.findViewById(R.id.tv_item_left2)
        tv_image_item_price2 = view.findViewById(R.id.tv_image_item_price2)
        tv_image_item_discount2 = view.findViewById(R.id.tv_image_item_discount2)
        iv_image3 = view.findViewById(R.id.iv_image3)
        tv_item_left3 = view.findViewById(R.id.tv_item_left3)
        tv_image_item_price3 = view.findViewById(R.id.tv_image_item_price3)
        tv_image_item_discount3 = view.findViewById(R.id.tv_image_item_discount3)

        //categories
        fl_categories = view.findViewById(R.id.fl_categories)
        rl_cate_btn = view.findViewById(R.id.rl_cate_btn)
        rv_cat = view.findViewById(R.id.rv_cat)

        //identity period----------------------------------------------------


        itemAdapter = ItemAdapter(requireContext(), item_list)
        rv_item.adapter = itemAdapter

        viewPagerAdapter = ViewpagerAdapter(requireContext(), vp_list)
        vp_image.adapter = viewPagerAdapter
        dotsIndicator.attachTo(vp_image)

        catrgories = Categories(requireContext(), cate_list)
        rv_cat.adapter = catrgories

        cache = Cache(requireContext(), "Fg_home")

        changeSearchBarText()   //changing search bar text after 10 seconds

        dataFromCache()

        if (isViewPagerVisible){

            viewpager_infinite_loop()

        }

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


                    rv_item.visibility = View.GONE
                    fl_item_image_loading.visibility = View.GONE

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

                            cache.setCache("item_image", gson.toJson(itemImage))

                            CoroutineScope(Dispatchers.Main).launch {

                                itemAdapter.notifyDataSetChanged()

                                rv_item.visibility = View.VISIBLE
                                fl_item_image_loading.visibility = View.GONE

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

                    vp_image.visibility = View.GONE
                    fl_vp_image_loading.visibility = View.GONE
                    dotsIndicator.visibility = View.GONE
                    isViewPagerVisible = false
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
                                vp_map["image"] = item.vp_image
                                vp_list.add(vp_map)

                            }

                            cache.setCache("vp_image", gson.toJson(vp_img))

                            CoroutineScope(Dispatchers.Main).launch {

                                isViewPagerVisible = true
                                vp_image.visibility = View.VISIBLE
                                dotsIndicator.visibility = View.VISIBLE
                                fl_vp_image_loading.visibility = View.GONE
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

    //data from cache---------------------------------------------------
    private fun dataFromCache(){

        val cacheData = cache.getCache("item_image", 5)
        val vpCacheImage = cache.getCache("vp_image", 5)
        val couponImage = cache.getCache("coupon_image", 5)
        val flashSale = cache.getCache("flash_sale_image", 5)
        val categories = cache.getCache("cate_image", 5)

        //item image
        if (!cacheData.isNullOrEmpty()){

            val gson = Gson()

            val itemImage = gson.fromJson(cacheData, ItemModel::class.java)

            item_list.clear()

            for (item in itemImage.images){

               // Log.i("cache", item.toString())

                item_map = HashMap()
                item_map["image"] = item.image.toString()
                item_map["text"] = item.text.toString()
                item_list.add(item_map)

            }

            rv_item.visibility = View.VISIBLE
            fl_item_image_loading.visibility = View.GONE
            itemAdapter.notifyDataSetChanged()

        }else{

            rv_item.visibility = View.GONE
            fl_item_image_loading.visibility = View.VISIBLE
            item_image()    // showing item images
        }

        //viewpager
        if (!vpCacheImage.isNullOrEmpty()){

            val gson = Gson()

            val vp = gson.fromJson(vpCacheImage, ViewpagerModel::class.java)

            vp_list.clear()

            for (i in vp.images){

                //Log.i("cache", i.toString())

                vp_map = HashMap()
                vp_map["image"] = i.vp_image.toString()
                vp_list.add(vp_map)

            }

            isViewPagerVisible = true
            vp_image.visibility = View.VISIBLE
            dotsIndicator.visibility = View.VISIBLE
            fl_vp_image_loading.visibility = View.GONE
            itemAdapter.notifyDataSetChanged()


        }else{

            isViewPagerVisible = false
            vp_image.visibility = View.GONE
            dotsIndicator.visibility = View.GONE
            fl_vp_image_loading.visibility = View.VISIBLE
            viewpager()
        }

        //coupon
        if (!couponImage.isNullOrEmpty()){

            val gson = Gson()

            val ci = gson.fromJson(couponImage, CouponModel::class.java)


            Picasso.get().load(ci.images[0].oth_image).into(iv_coupon_img)


            iv_coupon_img.visibility = View.VISIBLE
            fl_coupon.visibility = View.VISIBLE


        }else{

            iv_coupon_img.visibility = View.GONE
            fl_coupon.visibility = View.GONE
            coupon_image()
        }

        //flash sale
        if (!flashSale.isNullOrEmpty()){

            val gson = Gson()

            val fl = gson.fromJson(flashSale, FlashSaleModel::class.java)


            fl_flash_sale.visibility = View.VISIBLE

            if (fl.images.isNotEmpty()){

                Picasso.get().load(fl.images[0].sale_image).into(iv_image1)
                tv_item_left1.text = fl.images[0].text
                tv_image_item_price1.text = fl.images[0].price
                tv_image_item_discount1.text = fl.images[0].discount

                Picasso.get().load(fl.images[1].sale_image).into(iv_image2)
                tv_item_left2.text = fl.images[1].text
                tv_image_item_price2.text = fl.images[1].price
                tv_image_item_discount2.text = fl.images[1].discount

                Picasso.get().load(fl.images[2].sale_image).into(iv_image3)
                tv_item_left3.text = fl.images[2].text
                tv_image_item_price3.text = fl.images[2].price
                tv_image_item_discount3.text = fl.images[2].discount

            }


        }else{

            fl_flash_sale.visibility = View.GONE
            flash_sale()
        }

        //categories
        if (!categories.isNullOrEmpty()){

            val gson = Gson()

            val ci = gson.fromJson(categories, CategoriesModel::class.java)


            fl_categories.visibility = View.VISIBLE

            if (ci.images.isNotEmpty()){

                for (item in ci.images){

                    cate_map = HashMap()
                    cate_map["image"] = item.cate_image ?: ""
                    cate_map["text"] = item.text ?: ""
                    cate_list.add(cate_map)

                }

            }


        }else{

            fl_categories.visibility = View.GONE
            categories()
        }

    }

    //new user coupon image-----------------------------------------------------------
    private fun coupon_image(){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url("https://rksoftwares.xyz/All_app/Daraz_clone/Api/All_Images.php?resource=other_images")
            .build()

        client.newCall(request).enqueue(object : Callback{

            override fun onFailure(call: Call, e: IOException) {

                CoroutineScope(Dispatchers.Main).launch {


                    fl_coupon.visibility = View.GONE


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

                                fl_coupon.visibility = View.VISIBLE
                                iv_coupon_img.visibility = View.VISIBLE

                                Picasso.get().load(itemImage.images[0].oth_image).into(iv_coupon_img)

                            }

                        }

                    }catch (e : Exception){

                        e.printStackTrace()

                    }

                }


            }
        })

    }

    //flash sale item-------------------------------------------------------------------------------

    private fun flash_sale(){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url("https://rksoftwares.xyz/All_app/Daraz_clone/Api/All_Images.php?resource=flash_sale_images")
            .build()

        client.newCall(request).enqueue(object : Callback{

            override fun onFailure(call: Call, e: IOException) {

                CoroutineScope(Dispatchers.Main).launch {


                    fl_flash_sale.visibility = View.GONE


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

                                fl_flash_sale.visibility = View.VISIBLE

                                if (itemImage.images.isNotEmpty()){

                                    Picasso.get().load(itemImage.images[0].sale_image).into(iv_image1)
                                    tv_item_left1.text = itemImage.images[0].text
                                    tv_image_item_price1.text = itemImage.images[0].price
                                    tv_image_item_discount1.text = itemImage.images[0].discount

                                    Picasso.get().load(itemImage.images[1].sale_image).into(iv_image2)
                                    tv_item_left2.text = itemImage.images[1].text
                                    tv_image_item_price2.text = itemImage.images[1].price
                                    tv_image_item_discount2.text = itemImage.images[1].discount

                                    Picasso.get().load(itemImage.images[2].sale_image).into(iv_image3)
                                    tv_item_left3.text = itemImage.images[2].text
                                    tv_image_item_price3.text = itemImage.images[2].price
                                    tv_image_item_discount3.text = itemImage.images[2].discount

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

    //viewpager-------------------------------------------------------------
    private fun categories(){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url("https://rksoftwares.xyz/All_app/Daraz_clone/Api/All_Images.php?resource=category_image")
            .build()

        client.newCall(request).enqueue(object : Callback{

            override fun onFailure(call: Call, e: IOException) {

                CoroutineScope(Dispatchers.Main).launch {

                    fl_categories.visibility = View.GONE

                }

            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful){

                    val data = response.body.string()

                    try {

                        val c_img = gson.fromJson(data, CategoriesModel::class.java)

                        if (c_img.status == "successful"){

                            cate_list.clear()

                            for (item in c_img.images){

                                cate_map = HashMap()
                                cate_map["image"] = item.cate_image ?: ""
                                cate_map["text"] = item.text ?: ""
                                cate_list.add(cate_map)

                            }

                           cache.setCache("cate_image", gson.toJson(c_img))

                            CoroutineScope(Dispatchers.Main).launch {

                                fl_categories.visibility = View.VISIBLE
                                catrgories.notifyDataSetChanged()

                            }

                        }

                    }catch (e : Exception){

                        e.printStackTrace()

                    }

                }


            }
        })

    }

}//public class=============================================================