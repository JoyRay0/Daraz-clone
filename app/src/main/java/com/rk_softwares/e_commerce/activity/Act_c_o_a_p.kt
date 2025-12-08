package com.rk_softwares.e_commerce.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.rk_softwares.e_commerce.Other.EdgeToEdge
import com.rk_softwares.e_commerce.Other.IntentHelper
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.fragment.Fg_home_messages_activities_child
import com.rk_softwares.e_commerce.fragment.Fg_home_messages_chats_child
import com.rk_softwares.e_commerce.fragment.Fg_home_messages_order_child
import com.rk_softwares.e_commerce.fragment.Fg_home_messages_promos_child

class Act_c_o_a_p : AppCompatActivity() {

    //XML id's--------------------------------------------------

    //toolbar
    private lateinit var fl_toolbar : FrameLayout
    private lateinit var fl_container : FrameLayout
    private lateinit var iv_back : AppCompatImageView
    private lateinit var toolbar_title : AppCompatTextView
    private lateinit var iv_setting : AppCompatImageView

    //others
    private lateinit var edge_to_edge : EdgeToEdge

    //XML id's--------------------------------------------------

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_co_ap)

        //identity period-----------------------------------------------

        fl_toolbar = findViewById(R.id.fl_toolbar)
        fl_container = findViewById(R.id.fl_container)
        iv_back = findViewById(R.id.iv_back)
        toolbar_title = findViewById(R.id.toolbar_title)
        iv_setting = findViewById(R.id.iv_setting)

        //identity period-----------------------------------------------

        edge_to_edge = EdgeToEdge(this)
        edge_to_edge.setEdgeToEdge()
        edge_to_edge.setToolBar(fl_toolbar)
        edge_to_edge.setStatusBarColor("#FFFFFF", true)
        edge_to_edge.setBottomNav(fl_container)

        //back button--------------------------------------------------
        iv_back.setOnClickListener {

            IntentHelper.intent(this, Act_home::class.java)

        }

        //setting----------------------------------------------------------
        iv_setting.setOnClickListener {

            Toast.makeText(this, "Working on it", Toast.LENGTH_SHORT).show()

        }


        sendToFragment()

        onBackPressedDispatcher.addCallback(this, true){

            IntentHelper.intent(this@Act_c_o_a_p, Act_home::class.java)

        }

    }// on create==============================================

    //sending user to fragment---------------------------------------------
    private fun sendToFragment(){

        val intent = intent
        val chat = intent.getStringExtra("c")
        val order = intent.getStringExtra("o")
        val activities = intent.getStringExtra("a")
        val promos = intent.getStringExtra("p")

        if (chat == "allChats") {

            toolbar_title.text = "Chats"
            supportFragmentManager.beginTransaction().replace(
                R.id.fl_container,
                Fg_home_messages_chats_child()
            ).commit()

        }else if (order == "allOrder"){

            toolbar_title.text = "Orders"
            supportFragmentManager.beginTransaction().replace(
                R.id.fl_container,
                Fg_home_messages_order_child()
            ).commit()


        }else if (activities == "allActivities"){

            toolbar_title.text = "Activities"
            supportFragmentManager.beginTransaction().replace(
                R.id.fl_container,
                Fg_home_messages_activities_child()
            ).commit()

        }else{

            toolbar_title.text = "Promos"
            supportFragmentManager.beginTransaction().replace(
                R.id.fl_container,
                Fg_home_messages_promos_child()
            ).commit()

        }

    }

}// public class=================================================