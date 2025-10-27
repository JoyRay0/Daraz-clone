package com.rk_sofwares.e_commerce.activity

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.drawable.toDrawable
import com.google.android.material.checkbox.MaterialCheckBox
import com.rk_sofwares.e_commerce.Other.EdgeToEdge
import com.rk_sofwares.e_commerce.Other.IntentHelper
import com.rk_sofwares.e_commerce.Other.ShortMessageHelper
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

    private lateinit var tv_onOff : AppCompatTextView
    private lateinit var tv_countryName : AppCompatTextView
    private lateinit var tv_languageName : AppCompatTextView

    //other
    private lateinit var edge_to_edge : EdgeToEdge
    private lateinit var storage : StorageHelper

    //initialize
    private var checkCountryValue = ""

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
        tv_onOff = findViewById(R.id.tv_onOff)
        tv_countryName = findViewById(R.id.tv_countryName)
        tv_languageName = findViewById(R.id.tv_languageName)

        //identity period---------------------------------------------------------

        edge_to_edge = EdgeToEdge(this)
        edge_to_edge.statusBarColor("#FFFFFF", true)
        edge_to_edge.setToolBar(rl_toolbar)
        storage = StorageHelper(this, "settings")
        storage = StorageHelper(this, "setting_message")


        val data = storage.getData("all_message")
        val country = storage.getData("country")

        if (data == null) tv_onOff.text = "On" else tv_onOff.text = data
        if (country == null) tv_countryName.text = "Bangladesh" else tv_countryName.text = country

        logOut()
        iv_back.setOnClickListener {

            IntentHelper.intent(this, Act_home::class.java)

        }
        sendDataWithIntent()

        onBackPressedDispatcher.addCallback(this, true){

            IntentHelper.intent(this@Act_setting, Act_home::class.java)

        }

        rl_country.setOnClickListener {

            countryDialog()

        }


    }// on create=================================================================

    //logout-------------------------------------------------------------------
    private fun logOut(){

        tv_logOut.setOnClickListener {

            val userInfo = storage.getData("user_data")

            if (userInfo != null){

                storage.deleteData("user_data")

                ShortMessageHelper.toast(this, "Logout successful")

            }else{

                ShortMessageHelper.toast(this, "Please login")

            }

        }

    }

    //send data with intent-------------------------------------------------------
    private fun sendDataWithIntent(){

        intentData("info", "AccountInformation", tv_accountInfo, null)
        intentData("security", "AccountSecurity", tv_accountSecurity, null)
        intentData("address", "AddressBook", tv_addressBook, null)
        intentData("policies", "Policies", tv_policies, null)
        intentData("help", "Help", tv_help, null)
        intentData("feedback", "FeedBack", tv_feedback, null)
        intentData("message", "Messages", null, rl_message)

    }

    private fun intentData( key : String, data : String, tv : AppCompatTextView?, rl : RelativeLayout?){

        tv?.setOnClickListener {

            IntentHelper.setDataIntent(this, Act_child_settings::class.java, key, data)
            finishAffinity()

        }

        rl?.setOnClickListener {

            IntentHelper.setDataIntent(this, Act_child_settings::class.java, key, data)
            finishAffinity()

        }

    }

    private fun countryDialog(){

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.lay_change_country)

        dialog.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
        dialog.window?.setGravity(Gravity.BOTTOM)

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val rl_bangladesh = dialog.findViewById<RelativeLayout>(R.id.rl_bangladesh)
        val rl_sri_lanka = dialog.findViewById<RelativeLayout>(R.id.rl_sri_lanka)
        val rl_nepal = dialog.findViewById<RelativeLayout>(R.id.rl_nepal)
        val rl_pakistan = dialog.findViewById<RelativeLayout>(R.id.rl_pakistan)

        val cb_pakistan = dialog.findViewById<MaterialCheckBox>(R.id.cb_pakistan)
        val cb_nepal = dialog.findViewById<MaterialCheckBox>(R.id.cb_nepal)
        val cb_sri_lanka = dialog.findViewById<MaterialCheckBox>(R.id.cb_sri_lanka)
        val cb_bangladesh = dialog.findViewById<MaterialCheckBox>(R.id.cb_bangladesh)

        val cv_cancel = dialog.findViewById<CardView>(R.id.cv_cancel)
        val cv_save = dialog.findViewById<CardView>(R.id.cv_save)

        oneChecked(rl_bangladesh, cb_bangladesh, cb_sri_lanka, cb_nepal, cb_pakistan, "Bangladesh")
        oneChecked(rl_sri_lanka, cb_sri_lanka, cb_bangladesh, cb_nepal, cb_pakistan, "Sri Lanka")
        oneChecked(rl_nepal, cb_nepal, cb_sri_lanka, cb_bangladesh, cb_pakistan, "Nepal")
        oneChecked(rl_pakistan, cb_pakistan, cb_sri_lanka, cb_nepal, cb_bangladesh, "Pakistan")

        getCheckboxData("country", true, "Bangladesh", cb_bangladesh, cb_sri_lanka, cb_nepal, cb_pakistan)

        cv_cancel.setOnClickListener {

            dialog.dismiss()

        }

        cv_save.setOnClickListener {

            if (checkCountryValue.isEmpty()){

                ShortMessageHelper.toast(this, "No country selected")

            }else{

                tv_countryName.text = checkCountryValue

                storage.setData("country", checkCountryValue)
                dialog.dismiss()

            }


        }

        dialog.show()
    }

    private fun oneChecked( rl : RelativeLayout ,trueCheck : MaterialCheckBox, falseCheck1 : MaterialCheckBox, falseCheck2 : MaterialCheckBox, falseCheck3 : MaterialCheckBox, value : String){

        rl.setOnClickListener {

            trueCheck.isChecked = true

            falseCheck1.isChecked = false
            falseCheck2.isChecked = false
            falseCheck3.isChecked = false

            checkCountryValue = value

        }

    }

    private fun getCheckboxData(cacheKey : String, Bool : Boolean, defaultValue : String, cb1 : MaterialCheckBox, cb2 : MaterialCheckBox, cb3 : MaterialCheckBox, cb4 : MaterialCheckBox){

        val data =  storage.getData(cacheKey)

        if (data == null){

            cb1.isChecked = Bool

            val value = if (Bool) defaultValue else ""

            storage.setData(cacheKey, value)

            checkCountryValue = value

        }else{

            checkbox(cb1, cb2, cb3, cb4, "Bangladesh", data)
            checkbox(cb2, cb3, cb4, cb1, "Sri Lanka", data)
            checkbox(cb3, cb4, cb1, cb2, "Nepal", data)
            checkbox(cb4, cb1, cb2, cb3, "Pakistan", data)

        }

    }

    private fun checkbox(
        cbBox : MaterialCheckBox,
        cb2 : MaterialCheckBox,
        cb3 : MaterialCheckBox,
        cb4 : MaterialCheckBox,
        countryName : String,
        data : String
    ){

        if (data == countryName){

            cbBox.isChecked = true

            cb2.isChecked = false
            cb3.isChecked = false
            cb4.isChecked = false

            checkCountryValue = countryName

        }

    }

}// public class==================================================================