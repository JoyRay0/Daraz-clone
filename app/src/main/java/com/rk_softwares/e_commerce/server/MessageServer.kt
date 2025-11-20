package com.rk_softwares.e_commerce.server

import android.app.Activity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rk_softwares.e_commerce.Other.DomainHelper
import com.rk_softwares.e_commerce.adapter.Messages
import com.rk_softwares.e_commerce.model.MessageModel
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

class MessageServer(

    private var activity: Activity

) {

    fun message(
        rv : RecyclerView,
        list : ArrayList<HashMap<String, String>>,
        adapter : Messages,
        tv_act : AppCompatTextView,
        tv_pro : AppCompatTextView,
        actCount : Int,
        proCount : Int
        ){

        val client = OkHttpClient()
        var actCountLocal = actCount
        var proCountLocal = proCount

        val gson = Gson()

        val request = Request.Builder()
            .url(DomainHelper.getMessagesLink())
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {

                CoroutineScope(Dispatchers.Main).launch {


                    rv.visibility = View.GONE

                }

            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful){

                    val data = response.body.string()

                    try {

                        val item_message = gson.fromJson(data, MessageModel::class.java)

                        if (item_message.status == "successful"){

                            list.clear()

                            for (item in item_message.images){

                                val m_map : HashMap<String, String> = HashMap()
                                m_map["title"] = item.title ?: ""
                                m_map["date"] = item.date ?: ""
                                m_map["image"] = item.image ?: ""
                                m_map["message"] = item.offer_message ?: ""
                                m_map["item"] = item.item ?: ""

                                list.add(m_map)

                            }

                            CoroutineScope(Dispatchers.Main).launch {

                                for (count in item_message.images){

                                    if (count.item.equals("activities")){

                                        tv_act.visibility = View.VISIBLE
                                        actCountLocal++
                                        tv_act.text = actCountLocal.toString()

                                    }else if (count.item.equals("promos")){

                                        tv_pro.visibility = View.VISIBLE
                                        proCountLocal++
                                        tv_pro.text = proCountLocal.toString()

                                    }

                                }

                                adapter.notifyDataSetChanged()
                                rv.visibility = View.VISIBLE

                            }

                        }

                    }catch (e : Exception){

                        e.printStackTrace()

                    }

                }


            }
        })

    }

    fun actMessages(rv: RecyclerView, list : ArrayList<HashMap<String, String>>, messages: Messages){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url(DomainHelper.getMessagesLink())
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {

                CoroutineScope(Dispatchers.Main).launch {

                    rv.visibility = View.GONE

                }

            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful){

                    val data = response.body.string()

                    try {

                        val item_message = gson.fromJson(data, MessageModel::class.java)

                        if (item_message.status == "successful"){

                            list.clear()

                            for (item in item_message.images){

                                if (item.item == "activities"){

                                    val m_map : HashMap<String, String> = HashMap()
                                    m_map["title"] = item.title ?: ""
                                    m_map["date"] = item.date ?: ""
                                    m_map["image"] = item.image ?: ""
                                    m_map["message"] = item.offer_message ?: ""
                                    m_map["item"] = item.item
                                    list.add(m_map)
                                }

                            }

                            CoroutineScope(Dispatchers.Main).launch {

                                messages.notifyDataSetChanged()
                                rv.visibility = View.VISIBLE

                            }

                        }

                    }catch (e : Exception){

                        e.printStackTrace()

                    }

                }


            }
        })

    }

    fun promoMessages(rv: RecyclerView, list : ArrayList<HashMap<String, String>>, messages: Messages){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url(DomainHelper.getMessagesLink())
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {

                CoroutineScope(Dispatchers.Main).launch {

                    rv.visibility = View.GONE

                }

            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful){

                    val data = response.body.string()

                    try {

                        val item_message = gson.fromJson(data, MessageModel::class.java)

                        if (item_message.status == "successful"){

                            list.clear()

                            for (item in item_message.images){

                                if (item.item == "promos"){

                                    val m_map : HashMap<String, String> = HashMap()
                                    m_map["title"] = item.title ?: ""
                                    m_map["date"] = item.date ?: ""
                                    m_map["image"] = item.image ?: ""
                                    m_map["message"] = item.offer_message ?: ""
                                    m_map["item"] = item.item
                                    list.add(m_map)
                                }

                            }

                            CoroutineScope(Dispatchers.Main).launch {

                                messages.notifyDataSetChanged()
                                rv.visibility = View.VISIBLE

                            }

                        }

                    }catch (e : Exception){

                        e.printStackTrace()

                    }

                }


            }
        })

    }

}