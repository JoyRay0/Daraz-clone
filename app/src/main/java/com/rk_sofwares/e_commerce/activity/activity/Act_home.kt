package com.rk_sofwares.e_commerce.activity.activity

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.toColorInt
import androidx.core.view.isGone
import androidx.core.view.marginTop
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

    //toolbar
    private lateinit var iv_scan : AppCompatImageView
    private lateinit var cv_search : CardView
    private lateinit var iv_camera : AppCompatImageView
    private lateinit var tv_search_btn : AppCompatTextView
    private lateinit var iv_upload : AppCompatImageView

    //main activity
    private lateinit var iv_up_down : AppCompatImageView
    private lateinit var fl_bottom_navigation : FrameLayout
    private lateinit var fl_tool_bar : FrameLayout

    //XML id's------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_home)

        //identity period---------------------------------------------------

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


        iv_scan = findViewById(R.id.iv_scan);
        cv_search = findViewById(R.id.cv_search);
        iv_camera = findViewById(R.id.iv_camera);
        tv_search_btn = findViewById(R.id.tv_search_btn);
        iv_upload = findViewById(R.id.iv_upload);

        iv_up_down = findViewById(R.id.iv_up_down);
        fl_bottom_navigation = findViewById(R.id.fl_bottom_navigation);
        fl_tool_bar = findViewById(R.id.fl_tool_bar);


        //identity period---------------------------------------------------

        bottom_nav()

        // bottom and toolbar visibility
        iv_up_down.tag = "open"

        iv_up_down.setOnClickListener {

            var currentTag = iv_up_down.tag.toString()

            if (currentTag == "open"){

                fl_bottom_navigation.visibility = View.GONE
                fl_tool_bar.visibility = View.GONE

                fl_bottom_navigation.animate()
                    .translationY(-fl_bottom_navigation.height.toFloat())
                    .alpha(0f)
                    .setDuration(300)
                    .start()

                fl_tool_bar.animate()
                    .translationY(-fl_tool_bar.height.toFloat())
                    .alpha(0f)
                    .setDuration(300)
                    .start()

                iv_up_down.setImageResource(R.drawable.ic_close_eye)
                iv_up_down.tag = "close"

            }else if (currentTag == "close"){

                fl_bottom_navigation.visibility = View.VISIBLE
                fl_tool_bar.visibility = View.VISIBLE

                fl_bottom_navigation.animate()
                    .translationY(0f)
                    .alpha(1f)
                    .setDuration(300)
                    .start()

                fl_tool_bar.animate()
                    .translationY(0f)
                    .alpha(1f)
                    .setDuration(300)
                    .start()

                iv_up_down.setImageResource(R.drawable.ic_open_eye)
                iv_up_down.tag = "open"

            }


        }


    }//on create===========================================================

    //bottom navigation -------------------------------------------------------

    private fun bottom_nav(){

        val selectedColor = "#FF5722".toColorInt()
        val unselectedColor = "#988080".toColorInt()
        val itemBadge = "1"


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


        }


    }

    //bottom navigation -------------------------------------------------------

}//public class===========================================================