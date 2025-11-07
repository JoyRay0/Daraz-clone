package com.rk_softwares.e_commerce.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.rk_softwares.e_commerce.Other.IntentHelper
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.activity.Act_home

class Fg_my_order_all : Fragment() {

    //XML id's----------------------------------------------------------

    private lateinit var btn_shopping : MaterialButton

    //XML id's----------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_my_order_all, container, false)

        //identity period----------------------------------------------

        btn_shopping = view.findViewById(R.id.btn_shopping)

        //identity period----------------------------------------------

        btn_shopping.setOnClickListener {

            IntentHelper.intent(requireActivity(), Act_home::class.java)

        }

        return view
    }// on create=================================================================

}//class==========================================================================