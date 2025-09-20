package com.rk_sofwares.e_commerce.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updatePadding
import com.rk_sofwares.e_commerce.Other.EdgeToEdge
import com.rk_sofwares.e_commerce.R

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

    private lateinit var edge_to_edge : EdgeToEdge

    //XML id's------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_home)

        edge_to_edge = EdgeToEdge(this)
        edge_to_edge.setEdgeToEdge()

        //identity period---------------------------------------------------
        val main = findViewById<RelativeLayout>(R.id.main)
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

        val fg = supportFragmentManager
        fg.beginTransaction().replace(R.id.fl_container, Fg_home()).commit()

        bottom_nav()


        edge_to_edge.setBottomNav(fl_bottom_navigation)

    }//on create===========================================================

    //bottom navigation -------------------------------------------------------
    private fun bottom_nav(){

        val selectedColor = "#FF5722".toColorInt()
        val unselectedColor = "#988080".toColorInt()
        val itemBadge = "1"

        edge_to_edge.statusBarColor("#FF5722")

        iv_home.setImageResource(R.drawable.ic_home_fill)
        tv_home.setTextColor(selectedColor)
        tv_badge1.text = itemBadge
        tv_badge1.visibility = View.INVISIBLE


        iv_message.setImageResource(R.drawable.ic_chat_outline)
        tv_message.setTextColor(unselectedColor)
        tv_badge2.text = itemBadge
        tv_badge2.visibility = View.VISIBLE

        iv_cart.setImageResource(R.drawable.ic_cart_outline)
        tv_cart.setTextColor(unselectedColor)
        tv_badge3.text = itemBadge
        tv_badge3.visibility = View.VISIBLE

        iv_account.setImageResource(R.drawable.ic_account_outline)
        tv_account.setTextColor(unselectedColor)
        tv_badge4.text = itemBadge
        tv_badge4.visibility = View.VISIBLE


        ll_home.setOnClickListener {

            val fg = supportFragmentManager
            fg.beginTransaction().replace(R.id.fl_container, Fg_home()).commit()

            edge_to_edge.statusBarColor("#FF5722")

            iv_home.setImageResource(R.drawable.ic_home_fill)
            tv_home.setTextColor(selectedColor)
            tv_badge1.visibility = View.INVISIBLE

            iv_message.setImageResource(R.drawable.ic_chat_outline)
            tv_message.setTextColor(unselectedColor)
            tv_badge2.visibility = View.VISIBLE

            iv_cart.setImageResource(R.drawable.ic_cart_outline)
            tv_cart.setTextColor(unselectedColor)
            tv_badge3.visibility = View.VISIBLE

            iv_account.setImageResource(R.drawable.ic_account_outline)
            tv_account.setTextColor(unselectedColor)
            tv_badge4.visibility = View.VISIBLE


        }

        ll_cart.setOnClickListener {

            iv_cart.setImageResource(R.drawable.ic_cart_fill)
            tv_cart.setTextColor(selectedColor)
            tv_badge3.visibility = View.INVISIBLE


            iv_home.setImageResource(R.drawable.ic_home_outline)
            tv_home.setTextColor(unselectedColor)
            tv_badge1.visibility = View.VISIBLE

            iv_message.setImageResource(R.drawable.ic_chat_outline)
            tv_message.setTextColor(unselectedColor)
            tv_badge2.visibility = View.VISIBLE

            iv_account.setImageResource(R.drawable.ic_account_outline)
            tv_account.setTextColor(unselectedColor)
            tv_badge4.visibility = View.VISIBLE

            val fg = supportFragmentManager
            fg.beginTransaction().replace(R.id.fl_container, Fg_home()).commit()

            edge_to_edge.statusBarColor("#FF5722")

        }

        ll_message.setOnClickListener {

            iv_message.setImageResource(R.drawable.ic_chat_fill)
            tv_message.setTextColor(selectedColor)
            tv_badge2.visibility = View.INVISIBLE



            iv_home.setImageResource(R.drawable.ic_home_outline)
            tv_home.setTextColor(unselectedColor)
            tv_badge1.visibility = View.VISIBLE

            iv_cart.setImageResource(R.drawable.ic_cart_outline)
            tv_cart.setTextColor(unselectedColor)
            tv_badge3.visibility = View.VISIBLE

            iv_account.setImageResource(R.drawable.ic_account_outline)
            tv_account.setTextColor(unselectedColor)
            tv_badge4.visibility = View.VISIBLE

            val fg = supportFragmentManager
            fg.beginTransaction().replace(R.id.fl_container, Fg_messages()).commit()

            edge_to_edge.statusBarColor("#FFFFFF")

        }

        ll_account.setOnClickListener {

            iv_account.setImageResource(R.drawable.ic_account_fill)
            tv_account.setTextColor(selectedColor);
            tv_badge4.visibility = View.INVISIBLE


            iv_home.setImageResource(R.drawable.ic_home_outline)
            tv_home.setTextColor(unselectedColor)
            tv_badge1.visibility = View.VISIBLE

            iv_message.setImageResource(R.drawable.ic_chat_outline)
            tv_message.setTextColor(unselectedColor)
            tv_badge2.visibility = View.VISIBLE

            iv_cart.setImageResource(R.drawable.ic_cart_outline)
            tv_cart.setTextColor(unselectedColor)
            tv_badge3.visibility = View.VISIBLE

            val fg = supportFragmentManager
            fg.beginTransaction().replace(R.id.fl_container, Fg_home()).commit()

            edge_to_edge.statusBarColor("#FF5722")
        }

    }

    //bottom navigation -------------------------------------------------------


}//public class===========================================================