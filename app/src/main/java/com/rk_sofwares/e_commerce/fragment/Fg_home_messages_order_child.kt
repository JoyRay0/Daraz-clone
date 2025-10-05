package com.rk_sofwares.e_commerce.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.rk_sofwares.e_commerce.R
import com.rk_sofwares.e_commerce.activity.Act_home

class Fg_home_messages_order_child : Fragment() {

    //XML id's---------------------------------------------------

    private lateinit var btn_start_shopping : AppCompatTextView
    //XML id's---------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_home_messages_order, container, false)

        //identity period---------------------------------------------------

        btn_start_shopping = view.findViewById(R.id.btn_start_shopping)
        //identity period---------------------------------------------------

        btn_start_shopping.setOnClickListener {

            startActivity(Intent(requireActivity(), Act_home::class.java))
        }

        return view
    }//on create==========================================================

}// public class==========================================================