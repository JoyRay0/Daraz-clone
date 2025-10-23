package com.rk_sofwares.e_commerce.activity

import android.annotation.SuppressLint
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.addCallback
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
import com.rk_sofwares.e_commerce.fragment.Fg_system_setting

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

        iv_back.setOnClickListener {

            startActivity(Intent(this, Act_setting::class.java))
            finishAffinity()

        }

        onBackPressedDispatcher.addCallback(this, true){

            startActivity(Intent(this@Act_child_settings, Act_setting::class.java))
            finishAffinity()

        }

    }//on create=========================================================

    //get data from intent---------------------------------------------------
    private fun getDataFromIntent(){

        intentChecker("info", "AccountInformation", R.id.fl_container, Fg_setting_account_info(), "Account Information")
        intentChecker("security", "AccountSecurity", R.id.fl_container, Fg_account_security(), "Privacy protection")
        intentChecker("address", "AddressBook", R.id.fl_container, Fg_address_book(), "My Address")
        intentChecker("message", "Messages", R.id.fl_container, Fg_home_profile_setting_messages(), "Messages Settings")
        intentChecker("policies", "Policies", R.id.fl_container, Fg_setting_policies(), "Policies")
        intentChecker("help", "Help", R.id.fl_container, Fg_setting_help(), "Help")
        intentChecker("feedback", "FeedBack", R.id.fl_container, Fg_setting_feedback(), "Feedback")
        intentChecker("system", "systemSetting", R.id.fl_container, Fg_system_setting(), "System setting")
        intentChecker("deleted", "accountDeletion", R.id.fl_container, Fg_setting_account_info(), "Account Information")

    }

    private fun intentChecker(key : String, value : String, frameLayout : Int, fragment : androidx.fragment.app.Fragment, text : String){

        if (intent.getStringExtra(key).equals(value)){

            supportFragmentManager.beginTransaction().replace(frameLayout, fragment).commit()

            toolbar_title.text = text

        }

    }

}// public class=========================================================