package com.rk_softwares.e_commerce.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import com.rk_softwares.e_commerce.Other.ShortMessageHelper
import com.rk_softwares.e_commerce.Other.StorageHelper
import com.rk_softwares.e_commerce.R

class Fg_setting_feedback : Fragment() {

    //xml id's---------------------------------------------------------------------

    private lateinit var tv_userName : AppCompatTextView
    private lateinit var cv_ask_for_help : CardView

    //other
    private lateinit var cache : StorageHelper

    //xml id's---------------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_profile_setting_feedback, container, false)

        //identity period------------------------------------------------------------

        tv_userName = view.findViewById(R.id.tv_userName)
        cv_ask_for_help = view.findViewById(R.id.cv_ask_for_help)

        //identity period------------------------------------------------------------

        cache = StorageHelper(requireActivity(), "account_info")

        tv_userName.text = "Hello "+cache.getData("user_name")

        cv_ask_for_help.setOnClickListener {

            ShortMessageHelper.toast(requireActivity(), "Please Wait")

        }

        return view
    }// on create============================================================

}//public class==========================================================