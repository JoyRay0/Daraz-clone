package com.rk_softwares.e_commerce.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.card.MaterialCardView
import com.rk_softwares.e_commerce.Other.IntentHelper
import com.rk_softwares.e_commerce.Other.StorageHelper
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.activity.Act_change_email_password_number
import com.rk_softwares.e_commerce.Other.DialogHelper

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
    private lateinit var emailStorage: StorageHelper
    private lateinit var dialogHelper: DialogHelper

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
        emailStorage = StorageHelper(requireActivity(), "change_email")
        dialogHelper = DialogHelper(requireActivity())

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

        rl_change_email.setOnClickListener {

            IntentHelper.setDataIntent(requireActivity(), Act_change_email_password_number::class.java, "change_email", "email")


        }

        rl_gender.setOnClickListener {

            genderDialog()

        }

        rl_birthday.setOnClickListener {

            birthdayDialog()

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
        val email = maskEmail(emailStorage.getData("email").toString())
        val gender = storageHelper.getData("gender")
        val birthday = storageHelper.getData("birthday")

        tv_name.text = if (name.isNullOrEmpty()) "Guest" else name
        tv_mobile_number.text = if (number.isNullOrEmpty()) "Not Set" else number
        tv_change_email.text = if (email.isNullOrEmpty()) "Not Set" else email
        tv_birthday.text = if (birthday.isNullOrEmpty()) "Not Set" else birthday
        tv_gender.text = if (gender.isNullOrEmpty()) "Not Set" else gender

    }

    //mask email-----------------------------------------------------------------------------
    private fun maskEmail(email : String): String {

        if (email.isNullOrEmpty()) return "Not Set"

        val index = email.indexOf("@")
        if (index == -1) return "Not Set"

          return if (index > 2){

              email.substring(0, 2) + "******" + email.substring(index)

            }else{

                "******" + email.substring(index)

            }

    }

    //gender dialog----------------------------------------------------------
    private fun genderDialog(){

        val dialog = dialogHelper.customBottomDialog(R.layout.lay_gender_dialog)

        val rb_female = dialog.findViewById<AppCompatRadioButton>(R.id.rb_female)
        val rb_male = dialog.findViewById<AppCompatRadioButton>(R.id.rb_male)
        val rb_other = dialog.findViewById<AppCompatRadioButton>(R.id.rb_other)
        val cd_cancel = dialog.findViewById<MaterialCardView>(R.id.cd_cancel)
        val cd_ok = dialog.findViewById<MaterialCardView>(R.id.cd_ok)

        cd_cancel.setOnClickListener {

            dialog.dismiss()

        }

        cd_ok.setOnClickListener {

            var selectedGender = ""

            if (rb_female.isChecked){

                selectedGender =  rb_female.text.toString()


            }else if (rb_male.isChecked){

                selectedGender =  rb_male.text.toString()

            }else if (rb_other.isChecked){

                selectedGender =  rb_other.text.toString()

            }else{

                Toast.makeText(requireActivity(), " Not selected", Toast.LENGTH_SHORT).show()

            }

            tv_gender.text = selectedGender

            storageHelper.deleteData("gender")
            storageHelper.setData("gender", selectedGender)

            Toast.makeText(requireActivity(), "Saved successful", Toast.LENGTH_SHORT).show()

            dialog.dismiss()
        }

        val Gender = storageHelper.getData("gender")

        if (Gender == "Other"){

            rb_other.isChecked = true

        }else if (Gender == "Female"){

            rb_female.isChecked = true

        }else if (Gender == "Male"){

            rb_male.isChecked = true

        }else{

            rb_female.isChecked = false
            rb_male.isChecked = false
            rb_other.isChecked = false

        }


        dialog.show()

    }

    //birthday dialog--------------------------------------------------------------
    private fun birthdayDialog(){

        val dialog = dialogHelper.customBottomDialog(R.layout.lay_change_birthday)

        val ed_day = dialog.findViewById<AppCompatEditText>(R.id.ed_day)
        val ed_month = dialog.findViewById<AppCompatEditText>(R.id.ed_month)
        val ed_year = dialog.findViewById<AppCompatEditText>(R.id.ed_year)
        val cd_cancel = dialog.findViewById<MaterialCardView>(R.id.cd_cancel)
        val cd_ok = dialog.findViewById<MaterialCardView>(R.id.cd_ok)

        ed_day.requestFocus()

        cd_cancel.setOnClickListener {

            dialog.dismiss()

        }

        cd_ok.setOnClickListener {

            val day = ed_day.text.toString().trim()
            val month = ed_month.text.toString().trim()
            val year = ed_year.text.toString().trim()

            if (day.isNullOrEmpty()){

                ed_day.error = "Date required"

            }else if (month.isNullOrEmpty()){

                ed_month.error = "Month required"

            }else if (year.isNullOrEmpty()){

                ed_year.error = "Year required"

            }else{

                val totalDate = day+"/"+month+"/"+year

                storageHelper.deleteData("birthday")
                storageHelper.setData("birthday", totalDate)

                tv_birthday.text = totalDate

                Toast.makeText(requireActivity(), "Save successful", Toast.LENGTH_SHORT).show()

                dialog.dismiss()
            }


        }

        dialog.show()

    }

}//public class=================================================================