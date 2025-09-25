package com.rk_sofwares.e_commerce.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import com.rk_sofwares.e_commerce.Other.EdgeToEdge
import com.rk_sofwares.e_commerce.R
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rk_sofwares.e_commerce.Model.ItemModel
import com.rk_sofwares.e_commerce.Model.MessageModel
import com.rk_sofwares.e_commerce.adapter.Messages
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


class Fg_messages : Fragment() {

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
    private lateinit var m_map : HashMap<String, String>

    //other
    private lateinit var edge_to_edge : EdgeToEdge
    private lateinit var mAdapter : Messages

    var actCount = 0
    var proCount = 0


    //XML id's---------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_messages, container, false)

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

        message()

        sentIntent()

        tv_mark_read.setOnClickListener {

            tv_act_dot.visibility = View.INVISIBLE
            tv_pro_dot.visibility = View.INVISIBLE
            actCount = 0
            proCount = 0

        }



        return view
    }//on create==========================================================

    //message from server-------------------------------------------------
    private fun message(){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url("https://rksoftwares.xyz/All_app/Daraz_clone/Api/All_Images.php?resource=messages")
            .build()

        client.newCall(request).enqueue(object : Callback{

            override fun onFailure(call: Call, e: IOException) {

                CoroutineScope(Dispatchers.Main).launch {


                    rv_messages.visibility = View.GONE

                }

            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful){

                    val data = response.body.string()

                    try {

                        val item_message = gson.fromJson(data, MessageModel::class.java)

                        if (item_message.status == "successful"){

                            m_list.clear()

                            for (item in item_message.images){

                                m_map = HashMap()
                                m_map["title"] = item.title ?: ""
                                m_map["date"] = item.date ?: ""
                                m_map["image"] = item.image ?: ""
                                m_map["message"] = item.offer_message ?: ""
                                m_map["item"] = item.item ?: ""

                                m_list.add(m_map)

                            }

                            CoroutineScope(Dispatchers.Main).launch {

                                for (count in item_message.images){

                                    if (count.item.equals("activities")){

                                        tv_act_dot.visibility = View.VISIBLE
                                        actCount++
                                        tv_act_dot.text = actCount.toString()

                                    }else if (count.item.equals("promos")){

                                        tv_pro_dot.visibility = View.VISIBLE
                                        proCount++
                                        tv_pro_dot.text = proCount.toString()

                                    }

                                }

                                mAdapter.notifyDataSetChanged()
                                rv_messages.visibility = View.VISIBLE


                            }

                        }

                    }catch (e : Exception){

                        e.printStackTrace()

                    }

                }


            }
        })

    }

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