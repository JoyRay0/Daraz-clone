package com.rk_softwares.e_commerce.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rk_softwares.e_commerce.model.MessageModel
import com.rk_softwares.e_commerce.Other.DomainHelper
import com.rk_softwares.e_commerce.Other.EdgeToEdge
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.activity.Act_c_o_a_p
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

class Fg_home_messages : Fragment() {

    //XML id's---------------------------------------------------------------

    //toolbar
    private lateinit var fl_toolbar : FrameLayout
    private lateinit var fl_c_o_a_p : FrameLayout

    //chats orders activities promos
    private lateinit var ll_chats : LinearLayout
    private lateinit var ll_orders : LinearLayout
    private lateinit var ll_activities : LinearLayout
    private lateinit var ll_promos : LinearLayout
    private lateinit var tv_act_dot : AppCompatTextView
    private lateinit var tv_pro_dot : AppCompatTextView

    private lateinit var tv_mark_read : AppCompatTextView

    private lateinit var rv_messages : RecyclerView

    private var m_list : ArrayList<HashMap<String, String>> = ArrayList()

    //other
    private lateinit var edge_to_edge : EdgeToEdge
    private lateinit var mAdapter : Messages
    private lateinit var messageServer : MessageServer


    var actCount = 0
    var proCount = 0


    //XML id's---------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_home_messages, container, false)

        //identity period---------------------------------------------------

        //toolbar
        fl_toolbar = view.findViewById(R.id.fl_toolbar)
        fl_c_o_a_p = view.findViewById(R.id.fl_c_o_a_p)

        //c,o,a,p
        ll_chats = view.findViewById(R.id.ll_chats)
        ll_orders = view.findViewById(R.id.ll_orders)
        ll_activities = view.findViewById(R.id.ll_activities)
        ll_promos = view.findViewById(R.id.ll_promos)
        tv_act_dot = view.findViewById(R.id.tv_act_dot)
        tv_pro_dot = view.findViewById(R.id.tv_pro_dot)
        tv_mark_read = view.findViewById(R.id.tv_mark_read)

        //recyclerview
        rv_messages = view.findViewById(R.id.rv_messages)

        //identity period---------------------------------------------------

        edge_to_edge = EdgeToEdge(requireActivity())
        edge_to_edge.setToolBar(fl_toolbar)

        mAdapter = Messages(requireActivity(), m_list)
        rv_messages.adapter = mAdapter

        messageServer = MessageServer(requireActivity())

        sentIntent()

        tv_mark_read.setOnClickListener {

            tv_act_dot.visibility = View.INVISIBLE
            tv_pro_dot.visibility = View.INVISIBLE
            actCount = 0
            proCount = 0
            mAdapter.updateVisibility(true)

        }

        messageServer.message(rv_messages, m_list, mAdapter, tv_act_dot, tv_pro_dot, actCount, proCount)




        return view
    }//on create==========================================================

    //open in another app-----------------------------------------------------
    private fun sentIntent(){

        val chat ="allChats"
        val order = "allOrder"
        val activities = "allActivities"
        val promos = "allPromos"

        //chats
        ll_chats.setOnClickListener {

            val intent = Intent(requireActivity(), Act_c_o_a_p::class.java)
            intent.putExtra("c", chat)
            startActivity(intent)

        }

        //orders
        ll_orders.setOnClickListener {

            val intent = Intent(requireActivity(), Act_c_o_a_p::class.java)
            intent.putExtra("o", order)
            startActivity(intent)

        }

        //activities
        ll_activities.setOnClickListener {

            val intent = Intent(requireActivity(), Act_c_o_a_p::class.java)
            intent.putExtra("a", activities)
            startActivity(intent)

        }

        //promos
        ll_promos.setOnClickListener {

            val intent = Intent(requireActivity(), Act_c_o_a_p::class.java)
            intent.putExtra("p", promos)
            startActivity(intent)

        }



    }


}//public class============================================================