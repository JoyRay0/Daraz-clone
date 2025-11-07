package com.rk_softwares.e_commerce.activity

import android.os.Bundle
import android.widget.RelativeLayout
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.radiobutton.MaterialRadioButton
import com.rk_softwares.e_commerce.Other.DialogHelper
import com.rk_softwares.e_commerce.Other.EdgeToEdge
import com.rk_softwares.e_commerce.Other.IntentHelper
import com.rk_softwares.e_commerce.Other.ShortMessageHelper
import com.rk_softwares.e_commerce.Other.StorageHelper
import com.rk_softwares.e_commerce.R

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
    private lateinit var dialogHelper: DialogHelper

    //initialize
    private var checkCountryValue = ""
    private var radioLanguage = "English"

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
        edge_to_edge.setStatusBarColor("#FFFFFF", true)
        edge_to_edge.setToolBar(rl_toolbar)
        storage = StorageHelper(this, "settings")
        storage = StorageHelper(this, "setting_message")
        dialogHelper = DialogHelper(this)


        val data = storage.getData("all_message")
        val country = storage.getData("country")
        val language = storage.getData("language")


        if (data == null) tv_onOff.text = "On" else tv_onOff.text = data
        if (country == null) tv_countryName.text = "Bangladesh" else tv_countryName.text = country
        if (language == null) tv_languageName.text = radioLanguage else tv_languageName.text = language

        logOut()
        iv_back.setOnClickListener {

            //IntentHelper.intent(this, Act_home::class.java)
            IntentHelper.setDataIntent(this, Act_home::class.java, "item", "Fg_account")

        }
        sendDataWithIntent()

        onBackPressedDispatcher.addCallback(this, true){

            IntentHelper.intent(this@Act_setting, Act_home::class.java)

        }

        rl_country.setOnClickListener {

            countryDialog()

        }

        rl_language.setOnClickListener {

            languageDialog()

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

    //country dialog---------------------------------------------------------------------------------
    private fun countryDialog(){

        val dialog = dialogHelper.customBottomDialog(R.layout.lay_change_country)

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

    //language dialog---------------------------------------------------------------------
    private fun languageDialog(){

        val dialog = dialogHelper.customBottomDialog(R.layout.lay_languages)

        val rb_english = dialog.findViewById<MaterialRadioButton>(R.id.rb_english)
        val rb_bangla = dialog.findViewById<MaterialRadioButton>(R.id.rb_bangla)
        val cv_cancel = dialog.findViewById<MaterialCardView>(R.id.cv_cancel)
        val cv_ok = dialog.findViewById<MaterialCardView>(R.id.cv_ok)

        radioChecked(rb_english, rb_bangla, "English")
        radioChecked(rb_bangla, rb_english, "বাংলা")

        getRadioChecked(rb_english, rb_bangla)

        cv_cancel.setOnClickListener {

            dialog.dismiss()

        }

        cv_ok.setOnClickListener {

            if (radioLanguage.isEmpty()){

                ShortMessageHelper.toast(this, "Language can not be empty")

            }else{

                tv_languageName.text = radioLanguage
                storage.setData("language", radioLanguage)

                dialog.dismiss()

            }

        }

        dialog.show()

    }

    private fun radioChecked( rbTrue : MaterialRadioButton ,rbFalse : MaterialRadioButton, language : String){

        rbTrue.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) rbFalse.isChecked = false

            radioLanguage = language
        }


    }

    private fun getRadioChecked(rb1 : MaterialRadioButton, rb2 : MaterialRadioButton){

        val data = storage.getData("language")

        if (data == null){

            rb1.isChecked = true

            storage.setData("language", radioLanguage)

        }else{

            if (data == "English"){
                rb1.isChecked = true
                rb2.isChecked = false

            } else {
                rb2.isChecked = true
                rb1.isChecked = false
            }

        }

    }

}// public class==================================================================