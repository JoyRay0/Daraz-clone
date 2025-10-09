package com.rk_sofwares.e_commerce.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.card.MaterialCardView
import com.rk_sofwares.e_commerce.Other.IntentHelper
import com.rk_sofwares.e_commerce.Other.StorageHelper
import com.rk_sofwares.e_commerce.R
import com.rk_sofwares.e_commerce.activity.Act_change_email_password_number

class Fg_setting_account_info : Fragment() {

    //xml id's-------------------------------------------------------

    private lateinit var rl_full_name : RelativeLayout
    private lateinit var tv_change_password : AppCompatTextView
    private lateinit var sc_quick_login : SwitchCompat
    private lateinit var rl_add_mobile : RelativeLayout
    private lateinit var rl_change_email : RelativeLayout
    private lateinit var rl_gender : RelativeLayout
    private lateinit var rl_birthday : RelativeLayout
    private lateinit var tv_name : AppCompatTextView
    private lateinit var tv_mobile_number : AppCompatTextView
    private lateinit var tv_change_email : AppCompatTextView
    private lateinit var tv_gender : AppCompatTextView
    private lateinit var tv_birthday : AppCompatTextView

    //other
    private lateinit var storageHelper: StorageHelper
    private lateinit var numStorage: StorageHelper

    //xml id's-------------------------------------------------------

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_profile_setting_account_info, container, false)

        //identity period------------------------------------------------------------

        rl_full_name = view.findViewById(R.id.rl_full_name)
        tv_change_password = view.findViewById(R.id.tv_change_password)
        sc_quick_login = view.findViewById(R.id.sc_quick_login)
        rl_add_mobile = view.findViewById(R.id.rl_add_mobile)
        rl_change_email = view.findViewById(R.id.rl_change_email)
        rl_gender = view.findViewById(R.id.rl_gender)
        rl_birthday = view.findViewById(R.id.rl_birthday)
        tv_name = view.findViewById(R.id.tv_name)
        tv_mobile_number = view.findViewById(R.id.tv_mobile_number)
        tv_change_email = view.findViewById(R.id.tv_change_email)
        tv_gender = view.findViewById(R.id.tv_gender)
        tv_birthday = view.findViewById(R.id.tv_birthday)

        //identity period------------------------------------------------------------

        storageHelper = StorageHelper(requireActivity(), "account_info")
        numStorage = StorageHelper(requireActivity(), "mobile_number")

        buttonClicked()
        setText()

        val switch = storageHelper.getData("switch")

        if (switch == null){
            sc_quick_login.isChecked = true

            storageHelper.setData("switch", "on")

        }else{

            if (switch == "on") sc_quick_login.isChecked = true else sc_quick_login.isChecked = false

        }



        return view
    }//on create=============================================================

    //button clicked----------------------------------------------------------
    private fun buttonClicked(){

        rl_full_name.setOnClickListener {

            nameDialog()

        }

        tv_change_password.setOnClickListener {

            IntentHelper.setDataIntent(requireActivity(), Act_change_email_password_number::class.java, "change_password", "password")

        }

        sc_quick_login.setOnCheckedChangeListener { _, isChecked ->

           val value = if (isChecked) "on" else "off"

            storageHelper.deleteData("switch")
            storageHelper.setData("switch", value)

        }

        rl_add_mobile.setOnClickListener {

            IntentHelper.setDataIntent(requireActivity(), Act_change_email_password_number::class.java, "add_number", "number")

        }

    }

    //full name bottom sheet dialog-----------------------------------------------------------
    private fun nameDialog(){

        val dialog = BottomSheetDialog(requireActivity())
        val view = LayoutInflater.from(requireActivity()).inflate(R.layout.lay_setting_acoount_info_change_name_bottom_dialog, null)

        view.layoutParams?.apply {

            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = ViewGroup.LayoutParams.WRAP_CONTENT
            view.layoutParams = this

        }

        dialog.setContentView(view)
        dialog.window?.setBackgroundDrawable(null)

        val ed_name = view.findViewById<AppCompatEditText>(R.id.ed_name)
        val cd_cancel = view.findViewById<MaterialCardView>(R.id.cd_cancel)
        val cd_ok = view.findViewById<MaterialCardView>(R.id.cd_ok)


        ed_name.requestFocus()

        cd_ok.setOnClickListener {

            val name = ed_name.text.toString()

            if (name.isNullOrEmpty()){

                ed_name.error = "please enter your name"

            }else{

                storageHelper.deleteData("user_name")

                storageHelper.setData("user_name", name)

                Toast.makeText(requireActivity(), "Name save successfully", Toast.LENGTH_SHORT).show()

                tv_name.text = name

                dialog.dismiss()


            }

        }

        cd_cancel.setOnClickListener {

            dialog.dismiss()

        }

        dialog.show()

    }

    //showing text from share preference--------------------------------------------
    private fun setText(){

        val name = storageHelper.getData("user_name")
        val number = numStorage.getData("user_number")


        if (!name.isNullOrEmpty()) tv_name.text = name else tv_name.text = "Guest"

        if (!number.isNullOrEmpty()) tv_mobile_number.text = "*****" else tv_mobile_number.text = "Not Set"


    }

}//public class=================================================================