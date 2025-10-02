package com.rk_sofwares.e_commerce.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rk_sofwares.e_commerce.Other.EdgeToEdge
import com.rk_sofwares.e_commerce.R

class Act_child_settings : AppCompatActivity() {

    //XML id's----------------------------------------------------

    //toolbar
    private lateinit var fl_toolbar : FrameLayout
    private lateinit var iv_back : FrameLayout
    private lateinit var toolbar_title : FrameLayout
    private lateinit var iv_setting : FrameLayout

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


    }

}// public class=========================================================