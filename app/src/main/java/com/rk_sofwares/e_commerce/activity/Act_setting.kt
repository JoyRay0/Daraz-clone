package com.rk_sofwares.e_commerce.activity

import android.content.Intent
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rk_sofwares.e_commerce.Other.EdgeToEdge
import com.rk_sofwares.e_commerce.Other.StorageHelper
import com.rk_sofwares.e_commerce.R

class Act_setting : AppCompatActivity() {

    //XML id's----------------------------------------------------------------

    //toolbar
    private lateinit var iv_back : AppCompatImageView
    private lateinit var rl_toolbar : RelativeLayout

    //btn
    private lateinit var tv_accountInfo : AppCompatTextView
    private lateinit var tv_accountSecurity : AppCompatTextView
    private lateinit var tv_addressBook : AppCompatTextView
    private lateinit var tv_policies : AppCompatTextView
    private lateinit var tv_help : AppCompatTextView
    private lateinit var tv_feedback : AppCompatTextView
    private lateinit var tv_logOut : AppCompatTextView
    private lateinit var rl_message : RelativeLayout
    private lateinit var rl_country : RelativeLayout
    private lateinit var rl_language : RelativeLayout

    //other
    private lateinit var edge_to_edge : EdgeToEdge
    private lateinit var storage : StorageHelper

    //XML id's----------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_setting)

        //identity period---------------------------------------------------------

        //toolbar
        iv_back = findViewById(R.id.iv_back)
        rl_toolbar = findViewById(R.id.rl_toolbar)

        //item
        tv_accountInfo = findViewById(R.id.tv_accountInfo)
        tv_accountSecurity = findViewById(R.id.tv_accountSecurity)
        tv_addressBook = findViewById(R.id.tv_addressBook)
        tv_policies = findViewById(R.id.tv_policies)
        tv_help = findViewById(R.id.tv_help)
        tv_feedback = findViewById(R.id.tv_feedback)
        tv_logOut = findViewById(R.id.tv_logOut)
        rl_message = findViewById(R.id.rl_message)
        rl_country = findViewById(R.id.rl_country)
        rl_language = findViewById(R.id.rl_language)

        //identity period---------------------------------------------------------

        edge_to_edge = EdgeToEdge(this)
        edge_to_edge.statusBarColor("#FFFFFF", true)
        edge_to_edge.setToolBar(rl_toolbar)
        storage = StorageHelper(this, "settings")

        logOut()
        iv_back.setOnClickListener {

            startActivity(Intent(this, Act_home::class.java))
            
        }
        sendDataWithIntent()



    }// on create=================================================================

    //logout-------------------------------------------------------------------
    private fun logOut(){

        tv_logOut.setOnClickListener {

            val userInfo = storage.getData("user_data")

            if (userInfo != null){

                storage.deleteData("user_data")
                Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show()

            }else{

                Toast.makeText(this, "Please login", Toast.LENGTH_SHORT).show()

            }

        }

    }

    //send data with intent-------------------------------------------------------
    private fun sendDataWithIntent(){

        val accountInfo = "AccountInformation"
        val addressBook = "AddressBook"
        val messages = "Messages"
        val accountSecurity = "AccountSecurity"
        val policies = "Policies"
        val help = "Help"
        val feedBack = "FeedBack"

        tv_accountInfo.setOnClickListener {

            intent("info", accountInfo)

        }

        tv_accountSecurity.setOnClickListener {

            intent("security", accountSecurity)

        }

        tv_addressBook.setOnClickListener {

            intent("address", addressBook)

        }

        rl_message.setOnClickListener {

            intent("message", messages)

        }

        tv_policies.setOnClickListener {

            intent("policies", policies)

        }

        tv_help.setOnClickListener {

            intent("help", help)

        }

        tv_feedback.setOnClickListener {

            intent("feedback", feedBack)

        }


    }

    private fun intent(key : String, value : String){

        val intent = Intent(this, Act_child_settings::class.java)
        intent.putExtra(key, value)
        startActivity(intent)
        finishAffinity()

    }
}// public class==================================================================