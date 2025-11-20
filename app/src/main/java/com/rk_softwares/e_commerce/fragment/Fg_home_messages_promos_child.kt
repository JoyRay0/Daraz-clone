package com.rk_softwares.e_commerce.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rk_softwares.e_commerce.model.MessageModel
import com.rk_softwares.e_commerce.Other.DomainHelper
import com.rk_softwares.e_commerce.R
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

class Fg_home_messages_promos_child : Fragment() {

    //XML id's----------------------------------------------------------

    private lateinit var rv_items : RecyclerView

    private var m_list : ArrayList<HashMap<String, String>> = ArrayList()

    //other
    private lateinit var messages: Messages
    private lateinit var messageServer: MessageServer

    //XML id's----------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_home_messages_promos, container, false)

        //identity period----------------------------------------------

        rv_items = view.findViewById(R.id.rv_items)

        //identity period----------------------------------------------

        messages = Messages(requireActivity(), m_list)
        rv_items.adapter = messages

        messageServer = MessageServer(requireActivity())
        messageServer.promoMessages(rv_items, m_list, messages)

        return view
    }// on create=====================================================

}// public class=====================================================