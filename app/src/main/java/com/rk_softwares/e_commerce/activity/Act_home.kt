package com.rk_softwares.e_commerce.activity

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.activity.FullyDrawnReporter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import com.rk_softwares.e_commerce.Other.EdgeToEdge
import com.rk_softwares.e_commerce.Other.FragmentHelper
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.fragment.Fg_home_cart
import com.rk_softwares.e_commerce.fragment.Fg_home_home
import com.rk_softwares.e_commerce.fragment.Fg_home_messages
import com.rk_softwares.e_commerce.fragment.Fg_home_profile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Act_home : AppCompatActivity() {

    //XML id's------------------------------------------------------------

    //bottom nav
    private lateinit var ll_home : LinearLayout
    private lateinit var ll_message : LinearLayout
    private lateinit var ll_cart : LinearLayout
    private lateinit var ll_account : LinearLayout

    private lateinit var iv_home : AppCompatImageView
    private lateinit var tv_home : AppCompatTextView
    private lateinit var iv_message : AppCompatImageView
    private lateinit var tv_message : AppCompatTextView
    private lateinit var iv_cart : AppCompatImageView
    private lateinit var tv_cart : AppCompatTextView
    private lateinit var iv_account : AppCompatImageView
    private lateinit var tv_account : AppCompatTextView

    private lateinit var tv_badge1 : AppCompatTextView
    private lateinit var tv_badge2 : AppCompatTextView
    private lateinit var tv_badge3 : AppCompatTextView
    private lateinit var tv_badge4 : AppCompatTextView

    //main activity
    private lateinit var fl_bottom_navigation : FrameLayout
    private lateinit var fl_container : FrameLayout

    //others
    private lateinit var edge_to_edge : EdgeToEdge

    //init
    val selectedColor = "#FF5722".toColorInt()
    val unselectedColor = "#988080".toColorInt()
    val cartBadge = "1"

    //XML id's------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_home)

        edge_to_edge = EdgeToEdge(this)
        edge_to_edge.setEdgeToEdge()

        //identity period---------------------------------------------------
        //bottom na
        ll_home = findViewById(R.id.ll_home);
        ll_cart = findViewById(R.id.ll_cart);
        ll_message = findViewById(R.id.ll_message);
        ll_account = findViewById(R.id.ll_account);

        iv_home = findViewById(R.id.iv_home);
        tv_home = findViewById(R.id.tv_home);
        iv_message = findViewById(R.id.iv_message);
        tv_message = findViewById(R.id.tv_message);
        iv_cart = findViewById(R.id.iv_cart);
        tv_cart = findViewById(R.id.tv_cart);
        iv_account = findViewById(R.id.iv_account);
        tv_account = findViewById(R.id.tv_account);

        tv_badge1 = findViewById(R.id.tv_badge1);
        tv_badge2 = findViewById(R.id.tv_badge2);
        tv_badge3 = findViewById(R.id.tv_badge3);
        tv_badge4 = findViewById(R.id.tv_badge4);

        fl_bottom_navigation = findViewById(R.id.fl_bottom_navigation);
        fl_container = findViewById(R.id.fl_container);

        //identity period---------------------------------------------------

        getIntentData()

        edge_to_edge.setBottomNav(fl_bottom_navigation)

    }//on create===========================================================

    //bottom navigation -------------------------------------------------------
    private fun getIntentData(){

        val i = intent.getStringExtra("item") ?: "none"

        when(i){

            "Fg_message" -> selected(
                iv_message,
                tv_message,
                tv_badge2,
                Fg_home_messages(),
                R.drawable.ic_chat_fill,
                iv_home,
                iv_cart,
                iv_account,
                tv_home,
                tv_cart,
                tv_account,
                R.drawable.ic_home_outline,
                R.drawable.ic_cart_outline,
                R.drawable.ic_account_outline,
                tv_badge1,
                tv_badge3,
                tv_badge4
                )

            "Fg_cart" -> selected(
                iv_cart,
                tv_cart,
                tv_badge3,
                Fg_home_cart(),
                R.drawable.ic_cart_fill,
                iv_home,
                iv_message,
                iv_account,
                tv_home,
                tv_message,
                tv_account,
                R.drawable.ic_home_outline,
                R.drawable.ic_chat_outline,
                R.drawable.ic_account_outline,
                tv_badge1,
                tv_badge2,
                tv_badge4
            )

            "Fg_account" -> selected(
                iv_account,
                tv_account,
                tv_badge4,
                Fg_home_profile(),
                R.drawable.ic_account_fill,
                iv_home,
                iv_cart,
                iv_message,
                tv_home,
                tv_cart,
                tv_message,
                R.drawable.ic_home_outline,
                R.drawable.ic_cart_outline,
                R.drawable.ic_chat_outline,
                tv_badge1,
                tv_badge2,
                tv_badge3
            )

            else -> selected(
                iv_home,
                tv_home,
                tv_badge1,
                Fg_home_home(),
                R.drawable.ic_home_fill,
                iv_message,
                iv_cart,
                iv_account,
                tv_message,
                tv_cart,
                tv_account,
                R.drawable.ic_chat_outline,
                R.drawable.ic_cart_outline,
                R.drawable.ic_account_outline,
                tv_badge2,
                tv_badge3,
                tv_badge4
            )
        }

        navClicked(
            ll_home,
            iv_home,
            tv_home,
            tv_badge1,
            Fg_home_home(),
            R.drawable.ic_home_fill,
            iv_message,
            iv_cart,
            iv_account,
            tv_message,
            tv_cart,
            tv_account,
            R.drawable.ic_chat_outline,
            R.drawable.ic_cart_outline,
            R.drawable.ic_account_outline,
            tv_badge2,
            tv_badge3,
            tv_badge4)

        navClicked(
            ll_message,
            iv_message,
            tv_message,
            tv_badge2,
            Fg_home_messages(),
            R.drawable.ic_chat_fill,
            iv_home,
            iv_cart,
            iv_account,
            tv_home,
            tv_cart,
            tv_account,
            R.drawable.ic_home_outline,
            R.drawable.ic_cart_outline,
            R.drawable.ic_account_outline,
            tv_badge1,
            tv_badge3,
            tv_badge4)

        navClicked(
            ll_cart,
            iv_cart,
            tv_cart,
            tv_badge3,
            Fg_home_cart(),
            R.drawable.ic_cart_fill,
            iv_home,
            iv_message,
            iv_account,
            tv_home,
            tv_message,
            tv_account,
            R.drawable.ic_home_outline,
            R.drawable.ic_chat_outline,
            R.drawable.ic_account_outline,
            tv_badge1,
            tv_badge2,
            tv_badge4)

        navClicked(
            ll_account,
            iv_account,
            tv_account,
            tv_badge4,
            Fg_home_profile(),
            R.drawable.ic_account_fill,
            iv_home,
            iv_cart,
            iv_message,
            tv_home,
            tv_cart,
            tv_message,
            R.drawable.ic_home_outline,
            R.drawable.ic_cart_outline,
            R.drawable.ic_chat_outline,
            tv_badge1,
            tv_badge2,
            tv_badge3)

    }

    private fun unSelected(iv : AppCompatImageView, tv : AppCompatTextView, drawable : Int, badge : AppCompatTextView?){

        iv.setImageResource(drawable)
        tv.setTextColor(unselectedColor)
        badge?.visibility = View.INVISIBLE

    }

    private fun selected(
        selIv : AppCompatImageView,
        selTv : AppCompatTextView,
        selBadge : AppCompatTextView,
        fragment : Fragment,
        selDrawable : Int,
        unSelectedImage1 : AppCompatImageView,
        unSelectedImage2 : AppCompatImageView,
        unSelectedImage3 : AppCompatImageView,
        unSelectedText1 : AppCompatTextView,
        unSelectedText2 : AppCompatTextView,
        unSelectedText3 : AppCompatTextView,
        unSelDrawable1 : Int,
        unSelDrawable2 : Int,
        unSelDrawable3 : Int,
        unSelBadge1 : AppCompatTextView?,
        unSelBadge2 : AppCompatTextView?,
        unSelBadge3 : AppCompatTextView?
    ){

        selIv.setImageResource(selDrawable)
        selTv.setTextColor(selectedColor);
        selBadge.visibility = View.INVISIBLE

        supportFragmentManager.beginTransaction().replace(R.id.fl_container, fragment).commit()

        edge_to_edge.setStatusBarColor("#FFFFFF", true)

        unSelected(unSelectedImage1, unSelectedText1, unSelDrawable1, unSelBadge1)
        unSelected(unSelectedImage2, unSelectedText2, unSelDrawable2, unSelBadge2)
        unSelected(unSelectedImage3, unSelectedText3, unSelDrawable3, unSelBadge3)

    }

    private fun navClicked(
        ll : LinearLayout,
        selIv : AppCompatImageView,
        selTv : AppCompatTextView,
        selBadge : AppCompatTextView,
        fragment : Fragment,
        selDrawable : Int,
        unSelectedImage1 : AppCompatImageView,
        unSelectedImage2 : AppCompatImageView,
        unSelectedImage3 : AppCompatImageView,
        unSelectedText1 : AppCompatTextView,
        unSelectedText2 : AppCompatTextView,
        unSelectedText3 : AppCompatTextView,
        unSelDrawable1 : Int,
        unSelDrawable2 : Int,
        unSelDrawable3 : Int,
        unSelBadge1 : AppCompatTextView?,
        unSelBadge2 : AppCompatTextView?,
        unSelBadge3 : AppCompatTextView?
    ){

        ll.setOnClickListener {

            selected(
                selIv,
                selTv,
                selBadge,
                fragment,
                selDrawable,
                unSelectedImage1,
                unSelectedImage2,
                unSelectedImage3,
                unSelectedText1,
                unSelectedText2,
                unSelectedText3,
                unSelDrawable1,
                unSelDrawable2,
                unSelDrawable3,
                unSelBadge1,
                unSelBadge2,
                unSelBadge3
            )

        }

    }

    //bottom navigation -------------------------------------------------------

}//public class===========================================================