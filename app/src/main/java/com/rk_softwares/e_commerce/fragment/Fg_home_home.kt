package com.rk_softwares.e_commerce.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.rk_softwares.e_commerce.model.CategoriesModel
import com.rk_softwares.e_commerce.model.CouponModel
import com.rk_softwares.e_commerce.model.FlashSaleModel
import com.rk_softwares.e_commerce.model.ItemModel
import com.rk_softwares.e_commerce.model.ViewpagerModel
import com.rk_softwares.e_commerce.Other.Cache
import com.rk_softwares.e_commerce.Other.DomainHelper
import com.rk_softwares.e_commerce.Other.EdgeToEdge
import com.rk_softwares.e_commerce.Other.IntentHelper
import com.rk_softwares.e_commerce.Other.ItemClick
import com.rk_softwares.e_commerce.Other.KeyHelper
import com.rk_softwares.e_commerce.Other.PermissionHelper
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.activity.Act_product_full_info
import com.rk_softwares.e_commerce.activity.Act_wishlist
import com.rk_softwares.e_commerce.adapter.Categories
import com.rk_softwares.e_commerce.adapter.ItemAdapter
import com.rk_softwares.e_commerce.adapter.ViewpagerAdapter
import com.rk_softwares.e_commerce.server.HomeServer
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

class Fg_home_home : Fragment() {

    //XML id's--------------------------------------------------------------

    //toolbar
    private lateinit var iv_scan : AppCompatImageView
    private lateinit var cv_search : CardView
    private lateinit var iv_camera : AppCompatImageView
    private lateinit var tv_search_btn : AppCompatTextView
    private lateinit var iv_upload : AppCompatImageView
    private lateinit var tv_searchBar_text : AppCompatTextView
    private lateinit var fl_toolbar : FrameLayout

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

    private var vp_list : ArrayList<HashMap<String, String>> = ArrayList()
    private lateinit var vp_map : HashMap<String, String>

    // others
    private lateinit var cache : Cache
    private lateinit var nsv_scroll : NestedScrollView

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

    //tab item
    private lateinit var ll_for_you : LinearLayout
    private lateinit var ll_hot_deals : LinearLayout
    private lateinit var ll_voucher_max : LinearLayout
    private lateinit var ll_daraz_look : LinearLayout
    private lateinit var ll_free_delivery : LinearLayout
    private lateinit var ll_buy_any : LinearLayout
    private lateinit var ll_buy_more : LinearLayout
    private lateinit var ll_new_arrival : LinearLayout
    private lateinit var ll_must_buy : LinearLayout
    private lateinit var fl_ch_tab_item : FrameLayout
    private lateinit var tv_for_you : AppCompatTextView
    private lateinit var tv_hot_deals : AppCompatTextView
    private lateinit var tv_voucher_max : AppCompatTextView
    private lateinit var tv_daraz_look : AppCompatTextView
    private lateinit var tv_free_delivery : AppCompatTextView
    private lateinit var tv_buy_any : AppCompatTextView
    private lateinit var tv_buy_more : AppCompatTextView
    private lateinit var tv_new_arrival : AppCompatTextView
    private lateinit var tv_must_buy : AppCompatTextView
    private lateinit var fl_tablayout : FrameLayout


    //other
    private lateinit var permission : PermissionHelper
    private lateinit var homeServer: HomeServer
    private lateinit var classListener : ItemClick.ClassListener
    private lateinit var edge_to_edge : EdgeToEdge


    //XML id's--------------------------------------------------------------

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_home_home, container, false)

        init(view)

        changeSearchBarText()   //changing search bar text after 10 seconds

        viewpager_infinite_loop()

        dataFromCache()
        child_fragment()

        buttons()

        return view
    }//on create=================================================================

    private fun init(view : View){

        nsv_scroll = view.findViewById(R.id.nsv_scroll);

        //toolbar
        iv_scan = view.findViewById(R.id.iv_scan);
        cv_search = view.findViewById(R.id.cv_search);
        iv_camera = view.findViewById(R.id.iv_camera);
        tv_search_btn = view.findViewById(R.id.tv_search_btn);
        iv_upload = view.findViewById(R.id.iv_upload);
        tv_searchBar_text = view.findViewById(R.id.tv_searchBar_text);
        fl_toolbar = view.findViewById(R.id.fl_toolbar);

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

        //tab item
        ll_for_you = view.findViewById(R.id.ll_for_you)
        ll_hot_deals = view.findViewById(R.id.ll_hot_deals)
        ll_voucher_max = view.findViewById(R.id.ll_voucher_max)
        ll_daraz_look = view.findViewById(R.id.ll_daraz_look)
        ll_free_delivery = view.findViewById(R.id.ll_free_delivery)
        ll_buy_any = view.findViewById(R.id.ll_buy_any)
        ll_buy_more = view.findViewById(R.id.ll_buy_more)
        ll_new_arrival = view.findViewById(R.id.ll_new_arrival)
        ll_must_buy = view.findViewById(R.id.ll_must_buy)
        fl_ch_tab_item = view.findViewById(R.id.fl_ch_tab_item)
        tv_for_you = view.findViewById(R.id.tv_for_you)
        tv_hot_deals = view.findViewById(R.id.tv_hot_deals)
        tv_voucher_max = view.findViewById(R.id.tv_voucher_max)
        tv_daraz_look = view.findViewById(R.id.tv_daraz_look)
        tv_free_delivery = view.findViewById(R.id.tv_free_delivery)
        tv_buy_any = view.findViewById(R.id.tv_buy_any)
        tv_buy_more = view.findViewById(R.id.tv_buy_more)
        tv_new_arrival = view.findViewById(R.id.tv_new_arrival)
        tv_must_buy = view.findViewById(R.id.tv_must_buy)
        fl_tablayout = view.findViewById(R.id.fl_tablayout)

        edge_to_edge = EdgeToEdge(requireActivity())
        edge_to_edge.setToolBar(fl_toolbar)

        itemAdapter = ItemAdapter(requireContext(), item_list)
        rv_item.adapter = itemAdapter

        viewPagerAdapter = ViewpagerAdapter(requireContext(), vp_list)
        vp_image.adapter = viewPagerAdapter
        dotsIndicator.attachTo(vp_image)

        catrgories = Categories(requireContext(), cate_list)
        rv_cat.adapter = catrgories

        cache = Cache(requireContext(), "Fg_home")

        homeServer = HomeServer(requireActivity())


    }

    private fun buttons(){

        iv_coupon_img.setOnClickListener {

            //IntentHelper.intent(requireActivity(), Act_wishlist::class.java)
            startActivity(Intent(requireActivity(), Act_wishlist::class.java))

        }

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

    //infinite viewpager with dot indicator
    private fun viewpager_infinite_loop(){

        lifecycleScope.launch {

            while (true){

                delay(10000)

                if (vp_list.isNotEmpty()){

                    val nextItem = (vp_image.currentItem + 1) % vp_list.size
                    vp_image.setCurrentItem(nextItem, true)

                }

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

            homeServer.itemImage(rv_item, fl_item_image_loading, item_list, itemAdapter)
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

            vp_image.visibility = View.VISIBLE
            dotsIndicator.visibility = View.VISIBLE
            fl_vp_image_loading.visibility = View.GONE
            viewPagerAdapter.notifyDataSetChanged()

        }else{

            vp_image.visibility = View.GONE
            dotsIndicator.visibility = View.GONE
            fl_vp_image_loading.visibility = View.VISIBLE

            homeServer.viewPagerImage(vp_image, fl_vp_image_loading, vp_list, viewPagerAdapter, dotsIndicator)
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

            homeServer.couponImage(fl_coupon, iv_coupon_img)
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

            homeServer.flashSaleImage(
                fl_flash_sale,
                iv_image1,
                tv_item_left1,
                tv_image_item_price1,
                tv_image_item_discount1,
                iv_image2,
                tv_item_left2,
                tv_image_item_price2,
                tv_image_item_discount2,
                iv_image3,
                tv_item_left3,
                tv_image_item_price3,
                tv_image_item_discount3)
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

            homeServer.categoryImage(fl_categories, cate_list, catrgories)
        }

    }

    //child fragment----------------------------------------------------------------
    private fun child_fragment(){

        childFragmentManager.beginTransaction().replace(R.id.fl_ch_tab_item, Fg_tab_item_for_upu()).commit()

        val selectedBackground = ContextCompat.getDrawable(requireContext(), R.drawable.sh_tab_item)
        val unSelectedBackground = null
        val selectedTextColor = "#FF5722".toColorInt()
        val unSelectedTextColor = "#5B4949".toColorInt()
        val boldText = Typeface.DEFAULT_BOLD
        val normalText = Typeface.DEFAULT


        //default
        ll_for_you.background = selectedBackground
        tv_for_you.setTextColor(selectedTextColor)
        tv_for_you.typeface = boldText

        //normal
        tv_hot_deals.setTextColor(unSelectedTextColor)
        tv_voucher_max.setTextColor(unSelectedTextColor)
        tv_daraz_look.setTextColor(unSelectedTextColor)
        tv_free_delivery.setTextColor(unSelectedTextColor)
        tv_buy_any.setTextColor(unSelectedTextColor)
        tv_buy_more.setTextColor(unSelectedTextColor)
        tv_new_arrival.setTextColor(unSelectedTextColor)
        tv_must_buy.setTextColor(unSelectedTextColor)

        tv_hot_deals.typeface = normalText
        tv_voucher_max.typeface = normalText
        tv_daraz_look.typeface = normalText
        tv_free_delivery.typeface = normalText
        tv_buy_any.typeface = normalText
        tv_buy_more.typeface = normalText
        tv_new_arrival.typeface = normalText
        tv_must_buy.typeface = normalText

        //for you
        ll_for_you.setOnClickListener {

            ll_for_you.background = selectedBackground
            tv_for_you.setTextColor(selectedTextColor)
            tv_for_you.typeface = boldText

            childFragmentManager.beginTransaction().replace(
                R.id.fl_ch_tab_item,
                Fg_tab_item_for_upu()
            ).commit()


            tv_hot_deals.setTextColor(unSelectedTextColor)
            tv_voucher_max.setTextColor(unSelectedTextColor)
            tv_daraz_look.setTextColor(unSelectedTextColor)
            tv_free_delivery.setTextColor(unSelectedTextColor)
            tv_buy_any.setTextColor(unSelectedTextColor)
            tv_buy_more.setTextColor(unSelectedTextColor)
            tv_new_arrival.setTextColor(unSelectedTextColor)
            tv_must_buy.setTextColor(unSelectedTextColor)

            ll_hot_deals.background = unSelectedBackground
            ll_voucher_max.background = unSelectedBackground
            ll_daraz_look.background = unSelectedBackground
            ll_free_delivery.background = unSelectedBackground
            ll_buy_any.background = unSelectedBackground
            ll_buy_more.background = unSelectedBackground
            ll_new_arrival.background = unSelectedBackground
            ll_must_buy.background = unSelectedBackground

            tv_hot_deals.typeface = normalText
            tv_voucher_max.typeface = normalText
            tv_daraz_look.typeface = normalText
            tv_free_delivery.typeface = normalText
            tv_buy_any.typeface = normalText
            tv_buy_more.typeface = normalText
            tv_new_arrival.typeface = normalText
            tv_must_buy.typeface = normalText

        }

        //hot deals
        ll_hot_deals.setOnClickListener {

            ll_hot_deals.background = selectedBackground
            tv_hot_deals.setTextColor(selectedTextColor)
            tv_hot_deals.typeface = boldText

            childFragmentManager.beginTransaction().replace(
                R.id.fl_ch_tab_item,
                Fg_tab_item_for_upu()
            ).commit()

            tv_for_you.setTextColor(unSelectedTextColor)
            tv_voucher_max.setTextColor(unSelectedTextColor)
            tv_daraz_look.setTextColor(unSelectedTextColor)
            tv_free_delivery.setTextColor(unSelectedTextColor)
            tv_buy_any.setTextColor(unSelectedTextColor)
            tv_buy_more.setTextColor(unSelectedTextColor)
            tv_new_arrival.setTextColor(unSelectedTextColor)
            tv_must_buy.setTextColor(unSelectedTextColor)

            ll_for_you.background = unSelectedBackground
            ll_voucher_max.background = unSelectedBackground
            ll_daraz_look.background = unSelectedBackground
            ll_free_delivery.background = unSelectedBackground
            ll_buy_any.background = unSelectedBackground
            ll_buy_more.background = unSelectedBackground
            ll_new_arrival.background = unSelectedBackground
            ll_must_buy.background = unSelectedBackground

            tv_voucher_max.typeface = normalText
            tv_daraz_look.typeface = normalText
            tv_free_delivery.typeface = normalText
            tv_buy_any.typeface = normalText
            tv_buy_more.typeface = normalText
            tv_new_arrival.typeface = normalText
            tv_must_buy.typeface = normalText
            tv_for_you.typeface = normalText

        }

        //voucher
        ll_voucher_max.setOnClickListener {

            ll_voucher_max.background = selectedBackground
            tv_voucher_max.setTextColor(selectedTextColor)
            tv_voucher_max.typeface = boldText

            childFragmentManager.beginTransaction().replace(
                R.id.fl_ch_tab_item,
                Fg_tab_item_for_upu()
            ).commit()

            tv_for_you.setTextColor(unSelectedTextColor)
            tv_hot_deals.setTextColor(unSelectedTextColor)
            tv_daraz_look.setTextColor(unSelectedTextColor)
            tv_free_delivery.setTextColor(unSelectedTextColor)
            tv_buy_any.setTextColor(unSelectedTextColor)
            tv_buy_more.setTextColor(unSelectedTextColor)
            tv_new_arrival.setTextColor(unSelectedTextColor)
            tv_must_buy.setTextColor(unSelectedTextColor)

            ll_for_you.background = unSelectedBackground
            ll_hot_deals.background = unSelectedBackground
            ll_daraz_look.background = unSelectedBackground
            ll_free_delivery.background = unSelectedBackground
            ll_buy_any.background = unSelectedBackground
            ll_buy_more.background = unSelectedBackground
            ll_new_arrival.background = unSelectedBackground
            ll_must_buy.background = unSelectedBackground

            tv_hot_deals.typeface = normalText
            tv_daraz_look.typeface = normalText
            tv_free_delivery.typeface = normalText
            tv_buy_any.typeface = normalText
            tv_buy_more.typeface = normalText
            tv_new_arrival.typeface = normalText
            tv_must_buy.typeface = normalText
            tv_for_you.typeface = normalText

        }

        //daraz look
        ll_daraz_look.setOnClickListener {

            ll_daraz_look.background = selectedBackground
            tv_daraz_look.setTextColor(selectedTextColor)
            tv_daraz_look.typeface = boldText

            childFragmentManager.beginTransaction().replace(
                R.id.fl_ch_tab_item,
                Fg_tab_item_for_upu()
            ).commit()

            tv_for_you.setTextColor(unSelectedTextColor)
            tv_hot_deals.setTextColor(unSelectedTextColor)
            tv_voucher_max.setTextColor(unSelectedTextColor)
            tv_free_delivery.setTextColor(unSelectedTextColor)
            tv_buy_any.setTextColor(unSelectedTextColor)
            tv_buy_more.setTextColor(unSelectedTextColor)
            tv_new_arrival.setTextColor(unSelectedTextColor)
            tv_must_buy.setTextColor(unSelectedTextColor)

            ll_for_you.background = unSelectedBackground
            ll_hot_deals.background = unSelectedBackground
            ll_voucher_max.background = unSelectedBackground
            ll_free_delivery.background = unSelectedBackground
            ll_buy_any.background = unSelectedBackground
            ll_buy_more.background = unSelectedBackground
            ll_new_arrival.background = unSelectedBackground
            ll_must_buy.background = unSelectedBackground

            tv_hot_deals.typeface = normalText
            tv_voucher_max.typeface = normalText
            tv_free_delivery.typeface = normalText
            tv_buy_any.typeface = normalText
            tv_buy_more.typeface = normalText
            tv_new_arrival.typeface = normalText
            tv_must_buy.typeface = normalText
            tv_for_you.typeface = normalText

        }

        //free delivery
        ll_free_delivery.setOnClickListener {

            ll_free_delivery.background = selectedBackground
            tv_free_delivery.setTextColor(selectedTextColor)
            tv_free_delivery.typeface = boldText

            childFragmentManager.beginTransaction().replace(
                R.id.fl_ch_tab_item,
                Fg_tab_item_for_upu()
            ).commit()

            tv_for_you.setTextColor(unSelectedTextColor)
            tv_hot_deals.setTextColor(unSelectedTextColor)
            tv_daraz_look.setTextColor(unSelectedTextColor)
            tv_voucher_max.setTextColor(unSelectedTextColor)
            tv_buy_any.setTextColor(unSelectedTextColor)
            tv_buy_more.setTextColor(unSelectedTextColor)
            tv_new_arrival.setTextColor(unSelectedTextColor)
            tv_must_buy.setTextColor(unSelectedTextColor)

            ll_for_you.background = unSelectedBackground
            ll_hot_deals.background = unSelectedBackground
            ll_voucher_max.background = unSelectedBackground
            ll_daraz_look.background = unSelectedBackground
            ll_buy_any.background = unSelectedBackground
            ll_buy_more.background = unSelectedBackground
            ll_new_arrival.background = unSelectedBackground
            ll_must_buy.background = unSelectedBackground

            tv_hot_deals.typeface = normalText
            tv_voucher_max.typeface = normalText
            tv_daraz_look.typeface = normalText
            tv_buy_any.typeface = normalText
            tv_buy_more.typeface = normalText
            tv_new_arrival.typeface = normalText
            tv_must_buy.typeface = normalText
            tv_hot_deals.typeface = normalText

        }

        //buy any 4
        ll_buy_any.setOnClickListener {

            ll_buy_any.background = selectedBackground
            tv_buy_any.setTextColor(selectedTextColor)
            tv_buy_any.typeface = boldText

            childFragmentManager.beginTransaction().replace(
                R.id.fl_ch_tab_item,
                Fg_tab_item_for_upu()
            ).commit()

            tv_for_you.setTextColor(unSelectedTextColor)
            tv_hot_deals.setTextColor(unSelectedTextColor)
            tv_daraz_look.setTextColor(unSelectedTextColor)
            tv_voucher_max.setTextColor(unSelectedTextColor)
            tv_free_delivery.setTextColor(unSelectedTextColor)
            tv_buy_more.setTextColor(unSelectedTextColor)
            tv_new_arrival.setTextColor(unSelectedTextColor)
            tv_must_buy.setTextColor(unSelectedTextColor)

            ll_for_you.background = unSelectedBackground
            ll_hot_deals.background = unSelectedBackground
            ll_voucher_max.background = unSelectedBackground
            ll_daraz_look.background = unSelectedBackground
            ll_free_delivery.background = unSelectedBackground
            ll_buy_more.background = unSelectedBackground
            ll_new_arrival.background = unSelectedBackground
            ll_must_buy.background = unSelectedBackground

            tv_hot_deals.typeface = normalText
            tv_voucher_max.typeface = normalText
            tv_daraz_look.typeface = normalText
            tv_free_delivery.typeface = normalText
            tv_buy_more.typeface = normalText
            tv_new_arrival.typeface = normalText
            tv_must_buy.typeface = normalText
            tv_for_you.typeface = normalText

        }

        //buy more save more
        ll_buy_more.setOnClickListener {

            ll_buy_more.background = selectedBackground
            tv_buy_more.setTextColor(selectedTextColor)
            tv_buy_more.typeface = boldText

            childFragmentManager.beginTransaction().replace(
                R.id.fl_ch_tab_item,
                Fg_tab_item_for_upu()
            ).commit()

            tv_for_you.setTextColor(unSelectedTextColor)
            tv_hot_deals.setTextColor(unSelectedTextColor)
            tv_daraz_look.setTextColor(unSelectedTextColor)
            tv_voucher_max.setTextColor(unSelectedTextColor)
            tv_free_delivery.setTextColor(unSelectedTextColor)
            tv_buy_any.setTextColor(unSelectedTextColor)
            tv_new_arrival.setTextColor(unSelectedTextColor)
            tv_must_buy.setTextColor(unSelectedTextColor)

            ll_for_you.background = unSelectedBackground
            ll_hot_deals.background = unSelectedBackground
            ll_voucher_max.background = unSelectedBackground
            ll_daraz_look.background = unSelectedBackground
            ll_free_delivery.background = unSelectedBackground
            ll_buy_any.background = unSelectedBackground
            ll_new_arrival.background = unSelectedBackground
            ll_must_buy.background = unSelectedBackground

            tv_hot_deals.typeface = normalText
            tv_voucher_max.typeface = normalText
            tv_daraz_look.typeface = normalText
            tv_free_delivery.typeface = normalText
            tv_buy_any.typeface = normalText
            tv_new_arrival.typeface = normalText
            tv_must_buy.typeface = normalText
            tv_for_you.typeface = normalText

        }

        //new arrival
        ll_new_arrival.setOnClickListener {

            ll_new_arrival.background = selectedBackground
            tv_new_arrival.setTextColor(selectedTextColor)
            tv_new_arrival.typeface = boldText

            childFragmentManager.beginTransaction().replace(
                R.id.fl_ch_tab_item,
                Fg_tab_item_for_upu()
            ).commit()

            tv_for_you.setTextColor(unSelectedTextColor)
            tv_hot_deals.setTextColor(unSelectedTextColor)
            tv_daraz_look.setTextColor(unSelectedTextColor)
            tv_voucher_max.setTextColor(unSelectedTextColor)
            tv_free_delivery.setTextColor(unSelectedTextColor)
            tv_buy_any.setTextColor(unSelectedTextColor)
            tv_buy_more.setTextColor(unSelectedTextColor)
            tv_must_buy.setTextColor(unSelectedTextColor)

            ll_for_you.background = unSelectedBackground
            ll_hot_deals.background = unSelectedBackground
            ll_voucher_max.background = unSelectedBackground
            ll_daraz_look.background = unSelectedBackground
            ll_free_delivery.background = unSelectedBackground
            ll_buy_any.background = unSelectedBackground
            ll_buy_more.background = unSelectedBackground
            ll_must_buy.background = unSelectedBackground

            tv_hot_deals.typeface = normalText
            tv_voucher_max.typeface = normalText
            tv_daraz_look.typeface = normalText
            tv_free_delivery.typeface = normalText
            tv_buy_any.typeface = normalText
            tv_buy_more.typeface = normalText
            tv_must_buy.typeface = normalText
            tv_for_you.typeface = normalText

        }

        //must buy
        ll_must_buy.setOnClickListener {

            ll_must_buy.background = selectedBackground
            tv_must_buy.setTextColor(selectedTextColor)
            tv_must_buy.typeface = boldText

            childFragmentManager.beginTransaction().replace(
                R.id.fl_ch_tab_item,
                Fg_tab_item_for_upu()
            ).commit()

            tv_for_you.setTextColor(unSelectedTextColor)
            tv_hot_deals.setTextColor(unSelectedTextColor)
            tv_daraz_look.setTextColor(unSelectedTextColor)
            tv_voucher_max.setTextColor(unSelectedTextColor)
            tv_free_delivery.setTextColor(unSelectedTextColor)
            tv_buy_any.setTextColor(unSelectedTextColor)
            tv_buy_more.setTextColor(unSelectedTextColor)
            tv_new_arrival.setTextColor(unSelectedTextColor)

            ll_for_you.background = unSelectedBackground
            ll_hot_deals.background = unSelectedBackground
            ll_voucher_max.background = unSelectedBackground
            ll_daraz_look.background = unSelectedBackground
            ll_free_delivery.background = unSelectedBackground
            ll_buy_any.background = unSelectedBackground
            ll_buy_more.background = unSelectedBackground
            ll_new_arrival.background = unSelectedBackground

            tv_hot_deals.typeface = normalText
            tv_voucher_max.typeface = normalText
            tv_daraz_look.typeface = normalText
            tv_free_delivery.typeface = normalText
            tv_buy_any.typeface = normalText
            tv_buy_more.typeface = normalText
            tv_new_arrival.typeface = normalText
            tv_for_you.typeface = normalText

        }

    }


}//public class=============================================================