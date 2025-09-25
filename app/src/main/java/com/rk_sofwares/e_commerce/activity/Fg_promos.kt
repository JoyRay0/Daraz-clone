package com.rk_sofwares.e_commerce.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rk_sofwares.e_commerce.Model.MessageModel
import com.rk_sofwares.e_commerce.R
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

class Fg_promos : Fragment() {

    //XML id's----------------------------------------------------------

    private lateinit var rv_items : RecyclerView

    private var m_list : ArrayList<HashMap<String, String>> = ArrayList()
    private lateinit var m_map : HashMap<String, String>

    private lateinit var messages: Messages

    //XML id's----------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_promos, container, false)

        //identity period----------------------------------------------

        rv_items = view.findViewById(R.id.rv_items)

        //identity period----------------------------------------------

        messages = Messages(requireActivity(), m_list)
        rv_items.adapter = messages

        dataFromServer()

        return view
    }// on create=====================================================

    //data from server------------------------------------------------------------
    private fun dataFromServer(){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url("https://rksoftwares.xyz/All_app/Daraz_clone/Api/All_Images.php?resource=messages")
            .build()

        client.newCall(request).enqueue(object : Callback{

            override fun onFailure(call: Call, e: IOException) {

                CoroutineScope(Dispatchers.Main).launch {

                    rv_items.visibility = View.GONE

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

                                if (item.item == "promos"){

                                    m_map = HashMap()
                                    m_map["title"] = item.title ?: ""
                                    m_map["date"] = item.date ?: ""
                                    m_map["image"] = item.image ?: ""
                                    m_map["message"] = item.offer_message ?: ""
                                    m_map["item"] = item.item
                                    m_list.add(m_map)
                                }



                            }

                            CoroutineScope(Dispatchers.Main).launch {

                                messages.notifyDataSetChanged()
                                rv_items.visibility = View.VISIBLE

                            }

                        }

                    }catch (e : Exception){

                        e.printStackTrace()

                    }

                }


            }
        })

    }

}// public class=====================================================