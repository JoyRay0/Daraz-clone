package com.rk_sofwares.e_commerce.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.rk_sofwares.e_commerce.Other.EdgeToEdge
import com.rk_sofwares.e_commerce.R
import com.rk_sofwares.e_commerce.fragment.Fg_account_security
import com.rk_sofwares.e_commerce.fragment.Fg_address_book
import com.rk_sofwares.e_commerce.fragment.Fg_home_profile_setting_messages
import com.rk_sofwares.e_commerce.fragment.Fg_setting_account_info
import com.rk_sofwares.e_commerce.fragment.Fg_setting_feedback
import com.rk_sofwares.e_commerce.fragment.Fg_setting_help
import com.rk_sofwares.e_commerce.fragment.Fg_setting_policies

class Act_child_settings : AppCompatActivity() {

    //XML id's----------------------------------------------------

    //toolbar
    private lateinit var fl_toolbar : FrameLayout
    private lateinit var iv_back : AppCompatImageView
    private lateinit var toolbar_title : AppCompatTextView
    private lateinit var iv_setting : AppCompatImageView

    //item
    private lateinit var fl_container : FrameLayout

    //other
    private lateinit var edgeToEdge: EdgeToEdge

    //XML id's----------------------------------------------------

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_child_settings)

        //identity period---------------------------------------------------

        //toolbar
        fl_toolbar = findViewById(R.id.fl_toolbar)
        iv_back = findViewById(R.id.iv_back)
        toolbar_title = findViewById(R.id.toolbar_title)
        iv_setting = findViewById(R.id.iv_setting)

        //item
        fl_container = findViewById(R.id.fl_container)


        //identity period---------------------------------------------------

        edgeToEdge = EdgeToEdge(this)
        edgeToEdge.statusBarColor("#FFFFFF", true)
        edgeToEdge.setToolBar(fl_toolbar)
        edgeToEdge.setBottomNav(fl_container)

        iv_setting.visibility = View.GONE

        getDataFromIntent()

    }//on create=========================================================

    //get data from intent---------------------------------------------------
    private fun getDataFromIntent(){

        if (intent.getStringExtra("info").equals("AccountInformation")){

            supportFragmentManager.beginTransaction().replace(R.id.fl_container, Fg_setting_account_info()).commit()

            toolbar_title.text = "Account Information"

        }else if (intent.getStringExtra("security").equals("AccountSecurity")){

            supportFragmentManager.beginTransaction().replace(R.id.fl_container,
                Fg_account_security()).commit()

            toolbar_title.text = "Privacy protection"

        }else if (intent.getStringExtra("address").equals("AddressBook")){

            supportFragmentManager.beginTransaction().replace(R.id.fl_container,
                Fg_address_book()).commit()

            toolbar_title.text = "My Address"

        }else if (intent.getStringExtra("message").equals("Messages")){

            supportFragmentManager.beginTransaction().replace(R.id.fl_container,
                Fg_home_profile_setting_messages()).commit()

            toolbar_title.text = "Messages Settings"

        }else if (intent.getStringExtra("policies").equals("Policies")){

            supportFragmentManager.beginTransaction().replace(R.id.fl_container,
                Fg_setting_policies()).commit()

            toolbar_title.text = "Policies"

        }else if (intent.getStringExtra("help").equals("Help")){

            supportFragmentManager.beginTransaction().replace(R.id.fl_container, Fg_setting_help()).commit()

            toolbar_title.text = "Help"

        }else{

            supportFragmentManager.beginTransaction().replace(R.id.fl_container,
                Fg_setting_feedback()).commit()

            toolbar_title.text = "Feedback"

        }



    }

}// public class=========================================================