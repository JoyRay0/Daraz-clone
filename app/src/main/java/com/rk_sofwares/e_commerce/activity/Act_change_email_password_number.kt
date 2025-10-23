package com.rk_sofwares.e_commerce.activity

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
import com.rk_sofwares.e_commerce.fragment.Fg_add_mobile_number
import com.rk_sofwares.e_commerce.fragment.Fg_change_email
import com.rk_sofwares.e_commerce.fragment.Fg_change_password
import com.rk_sofwares.e_commerce.fragment.Fg_setting_account_info

class Act_change_email_password_number : AppCompatActivity() {

    //xml id's-------------------------------------------------------

    //toolbar
    private lateinit var fl_toolbar : FrameLayout
    private lateinit var iv_back : AppCompatImageView
    private lateinit var toolbar_title : AppCompatTextView
    private lateinit var iv_setting : AppCompatImageView

    //container
    private lateinit var fl_container : FrameLayout

    //other
    private lateinit var edge_to_edge : EdgeToEdge

    //xml id's-------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_change_email_password_number)

        //identity period----------------------------------------------

        fl_toolbar = findViewById(R.id.fl_toolbar)
        iv_back = findViewById(R.id.iv_back)
        toolbar_title = findViewById(R.id.toolbar_title)
        iv_setting = findViewById(R.id.iv_setting)
        fl_container = findViewById(R.id.fl_container)

        //identity period----------------------------------------------

        iv_setting.visibility = View.GONE

        edge_to_edge = EdgeToEdge(this)
        edge_to_edge.setToolBar(fl_toolbar)
        edge_to_edge.setBottomNav(fl_container)
        edge_to_edge.statusBarColor("#FFFFFF", true)


        iv_back.setOnClickListener {

            startActivity(Intent(this, Act_setting::class.java))
            finishAffinity()

        }
        dataFromIntent()

        onBackPressedDispatcher.addCallback(this, true){

            startActivity(Intent(this@Act_change_email_password_number, Act_setting::class.java))
            finishAffinity()

        }

    }// on create======================================================

    //using fragment----------------------------------------------------
    private fun dataFromIntent(){

        val password = intent.getStringExtra("change_password")
        val number = intent.getStringExtra("add_number")

        if (password.equals("password")){

            supportFragmentManager.beginTransaction().replace(R.id.fl_container, Fg_change_password()).commit()

            toolbar_title.text = "Change Password"

        }else if (number.equals("number")){

            supportFragmentManager.beginTransaction().replace(R.id.fl_container,
                Fg_add_mobile_number()).commit()

            toolbar_title.text = "Verify Number"

        }else{

            supportFragmentManager.beginTransaction().replace(R.id.fl_container,
                Fg_change_email()).commit()

            toolbar_title.text = "Change Email"

        }

    }

}//public class========================================================