package com.rk_softwares.e_commerce.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rk_softwares.e_commerce.model.MessageModel
import com.rk_softwares.e_commerce.Other.DomainHelper
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.activity.Act_home
import com.rk_softwares.e_commerce.adapter.Messages
import com.rk_softwares.e_commerce.server.MessageServer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import java.lang.Exception

class Fg_home_messages_activities_child : Fragment() {

    //XML id's-------------------------------------------------------

    private lateinit var rv_items : RecyclerView
    private lateinit var rbtn_all : AppCompatTextView
    private lateinit var rbtn_coins : AppCompatTextView
    private lateinit var rbtn_live : AppCompatTextView
    private lateinit var rbtn_service : AppCompatTextView
    private lateinit var ll_no_item : LinearLayout
    private lateinit var btn_start_shopping : AppCompatTextView

    private var m_list : ArrayList<HashMap<String, String>> = ArrayList()

    //other
    private lateinit var messages: Messages
    private lateinit var messageServer: MessageServer

    //XML id's-------------------------------------------------------

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_home_messages_activities, container, false)

        //identity period-------------------------------------------------------

        rv_items = view.findViewById(R.id.rv_items)
        rbtn_all = view.findViewById(R.id.rbtn_all)
        rbtn_coins = view.findViewById(R.id.rbtn_coins)
        rbtn_live = view.findViewById(R.id.rbtn_live)
        rbtn_service = view.findViewById(R.id.rbtn_service)
        ll_no_item = view.findViewById(R.id.ll_no_item)
        btn_start_shopping = view.findViewById(R.id.btn_start_shopping)

        //identity period-------------------------------------------------------

        messages = Messages(requireActivity(), m_list)
        rv_items.adapter = messages

        messageServer = MessageServer(requireActivity())

        messageServer.actMessages(rv_items, m_list, messages)

        selectedItem()

        btn_start_shopping.setOnClickListener {

            startActivity(Intent(requireActivity(), Act_home::class.java))

        }

        return view
    }// on create===============================================================

    //radio group---------------------------------------------------------------
    private fun selectedItem(){

        val selectedTextColor = "#FF5722".toColorInt()
        val unSelectedTextColor = "#8D7B7B".toColorInt()
        val selectedBackground = ContextCompat.getDrawable(requireActivity(), R.drawable.sh_radio_btn_selected)
        val unSelectedBackground = ContextCompat.getDrawable(requireActivity(), R.drawable.sh_radio_btn_unselected)

        rbtn_all.setTextColor(selectedTextColor)
        rbtn_all.background = selectedBackground

        rbtn_coins.setTextColor(unSelectedTextColor)
        rbtn_live.setTextColor(unSelectedTextColor)
        rbtn_service.setTextColor(unSelectedTextColor)

        rbtn_coins.background = unSelectedBackground
        rbtn_live.background = unSelectedBackground
        rbtn_service.background = unSelectedBackground

        selected(rbtn_all, rbtn_coins, rbtn_live, rbtn_service, selectedTextColor, unSelectedTextColor, selectedBackground, unSelectedBackground, true, false)

        selected(rbtn_coins, rbtn_all, rbtn_live, rbtn_service, selectedTextColor, unSelectedTextColor, selectedBackground, unSelectedBackground, true, false)

        selected(rbtn_live, rbtn_coins, rbtn_all, rbtn_service, selectedTextColor, unSelectedTextColor, selectedBackground, unSelectedBackground, false, true)

        selected(rbtn_service, rbtn_coins, rbtn_live, rbtn_all, selectedTextColor, unSelectedTextColor, selectedBackground, unSelectedBackground, false, true)

    }

    private fun selected(
        tv1 : AppCompatTextView,
        tv2 : AppCompatTextView,
        tv3 : AppCompatTextView,
        tv4 : AppCompatTextView,
        selColor : Int,
        unSelColor : Int,
        selStyle : Drawable?,
        unSelStyle : Drawable?,
        isVisible : Boolean,
        isGone : Boolean){

        tv1.setOnClickListener {

            tv1.setTextColor(selColor)
            tv1.background = selStyle

            tv2.setTextColor(unSelColor)
            tv3.setTextColor(unSelColor)
            tv4.setTextColor(unSelColor)

            tv2.background = unSelStyle
            tv3.background = unSelStyle
            tv4.background = unSelStyle

            if (isVisible) rv_items.visibility = View.VISIBLE else rv_items.visibility = View.GONE
            if (isGone) ll_no_item.visibility = View.VISIBLE else ll_no_item.visibility = View.GONE
        }

    }

}// public class================================================================