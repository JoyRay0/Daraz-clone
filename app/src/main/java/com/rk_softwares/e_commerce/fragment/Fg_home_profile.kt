package com.rk_softwares.e_commerce.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rk_softwares.e_commerce.model.GIFimagesModel
import com.rk_softwares.e_commerce.model.ProfileitemModel
import com.rk_softwares.e_commerce.Other.DomainHelper
import com.rk_softwares.e_commerce.Other.EdgeToEdge
import com.rk_softwares.e_commerce.Other.GIFLoader
import com.rk_softwares.e_commerce.Other.IntentHelper
import com.rk_softwares.e_commerce.Other.PermissionHelper
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.activity.Act_my_order
import com.rk_softwares.e_commerce.activity.Act_setting
import com.rk_softwares.e_commerce.activity.Act_wishlist
import com.rk_softwares.e_commerce.adapter.ProfileItems
import com.rk_softwares.e_commerce.server.ProfileServer
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

class Fg_home_profile : Fragment() {

    //XML id's-----------------------------------------------------

    //toolbar
    private lateinit var fl_toolbar : FrameLayout
    private lateinit var cv_profile_image : CardView
    private lateinit var iv_profile_image : AppCompatImageView
    private lateinit var iv_setting : AppCompatImageView

    //user item

    private lateinit var cv_wishlist : CardView
    private lateinit var cv_followed_store : CardView
    private lateinit var cv_voucher : CardView
    private lateinit var cv_cart : CardView

    //user item

    //my order
    private lateinit var tv_all_order : AppCompatTextView
    private lateinit var ll_to_pay : LinearLayout
    private lateinit var ll_to_ship : LinearLayout
    private lateinit var ll_to_receive : LinearLayout
    private lateinit var ll_to_review : LinearLayout
    private lateinit var ll_return : LinearLayout

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
    private lateinit var profileServer : ProfileServer

    //XML id's-----------------------------------------------------

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_home_profile, container, false)

        init(view)

        buttons()

        myOrders()

        return view
    }// on create======================================================

    private fun init(view: View){

        //toolbar
        fl_toolbar = view.findViewById(R.id.fl_toolbar)
        cv_profile_image = view.findViewById(R.id.cv_profile_image)
        iv_profile_image = view.findViewById(R.id.iv_profile_image)
        iv_setting = view.findViewById(R.id.iv_setting)

        //user item
        cv_wishlist = view.findViewById(R.id.cv_wishlist)
        cv_followed_store = view.findViewById(R.id.cv_followed_store)
        cv_voucher = view.findViewById(R.id.cv_voucher)
        cv_cart = view.findViewById(R.id.cv_cart)
        //user item

        //my order
        tv_all_order = view.findViewById(R.id.tv_all_order)
        ll_to_pay = view.findViewById(R.id.ll_to_pay)
        ll_to_ship = view.findViewById(R.id.ll_to_ship)
        ll_to_receive = view.findViewById(R.id.ll_to_receive)
        ll_to_review = view.findViewById(R.id.ll_to_review)
        ll_return = view.findViewById(R.id.ll_return)

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

        //nsv = view.findViewById(R.id.nsv)

        edge_to_edge = EdgeToEdge(requireActivity())
        edge_to_edge.setToolBar(fl_toolbar)


        gif_loader = GIFLoader(requireActivity())
        profileServer = ProfileServer(requireActivity())

        items = ProfileItems(requireActivity(), i_list)
        rv_items.adapter = items

        lifecycleScope.launch {

            profileServer.gifLoader(fl_gifs, iv_gif1, tv_gif_title1, tv_gif_info1, tv_btn1, iv_gif2, tv_gif_title2, tv_gif_info2, tv_btn2)

            i_list.clear()
            profileServer.items(rv_items, i_list)

        }

        items.notifyDataSetChanged()

    }

    private fun buttons(){

        iv_setting.setOnClickListener {

            startActivity(Intent(requireActivity(), Act_setting::class.java))
        }

        tv_all_order.setOnClickListener {

            IntentHelper.intent(requireActivity(), Act_my_order::class.java)

        }

        cv_wishlist.setOnClickListener {

            IntentHelper.intent(requireActivity(), Act_wishlist::class.java)

        }

    }

    private fun myOrders(){

        clickedMyOrders(ll_to_pay, "Fg_pay")
        clickedMyOrders(ll_to_ship, "Fg_ship")
        clickedMyOrders(ll_to_receive, "Fg_receive")
        clickedMyOrders(ll_to_review, "Fg_review")

    }

    private fun clickedMyOrders(ll : LinearLayout, value : String){

        ll.setOnClickListener {

            IntentHelper.setDataIntent(requireActivity(), Act_my_order::class.java, "tab", value)

        }

    }

}// public class========================================================