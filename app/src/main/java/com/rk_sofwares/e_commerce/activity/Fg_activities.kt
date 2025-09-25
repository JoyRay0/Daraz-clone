package com.rk_sofwares.e_commerce.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
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

class Fg_activities : Fragment() {

    //XML id's-------------------------------------------------------

    private lateinit var rv_items : RecyclerView
    private lateinit var rbtn_all : AppCompatTextView
    private lateinit var rbtn_coins : AppCompatTextView
    private lateinit var rbtn_live : AppCompatTextView
    private lateinit var rbtn_service : AppCompatTextView

    private var m_list : ArrayList<HashMap<String, String>> = ArrayList()
    private lateinit var m_map : HashMap<String, String>

    private lateinit var messages: Messages

    //XML id's-------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_activities, container, false)

        //identity period-------------------------------------------------------

        rv_items = view.findViewById(R.id.rv_items)
        rbtn_all = view.findViewById(R.id.rbtn_all)
        rbtn_coins = view.findViewById(R.id.rbtn_coins)
        rbtn_live = view.findViewById(R.id.rbtn_live)
        rbtn_service = view.findViewById(R.id.rbtn_service)

        //identity period-------------------------------------------------------

        messages = Messages(requireActivity(), m_list)
        rv_items.adapter = messages


        selectedItem()
        dataFromServer()

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

        rbtn_all.setOnClickListener {

            rbtn_all.setTextColor(selectedTextColor)
            rbtn_all.background = selectedBackground


            rbtn_coins.setTextColor(unSelectedTextColor)
            rbtn_live.setTextColor(unSelectedTextColor)
            rbtn_service.setTextColor(unSelectedTextColor)


            rbtn_coins.background = unSelectedBackground
            rbtn_live.background = unSelectedBackground
            rbtn_service.background = unSelectedBackground

        }

        rbtn_coins.setOnClickListener {

            rbtn_coins.setTextColor(selectedTextColor)
            rbtn_coins.background = selectedBackground

            rbtn_all.setTextColor(unSelectedTextColor)
            rbtn_live.setTextColor(unSelectedTextColor)
            rbtn_service.setTextColor(unSelectedTextColor)

            rbtn_all.background = unSelectedBackground
            rbtn_live.background = unSelectedBackground
            rbtn_service.background = unSelectedBackground

        }

        rbtn_live.setOnClickListener {

            rbtn_live.setTextColor(selectedTextColor)
            rbtn_live.background = selectedBackground

            rbtn_all.setTextColor(unSelectedTextColor)
            rbtn_coins.setTextColor(unSelectedTextColor)
            rbtn_service.setTextColor(unSelectedTextColor)

            rbtn_all.background = unSelectedBackground
            rbtn_coins.background = unSelectedBackground
            rbtn_service.background = unSelectedBackground

        }


        rbtn_service.setOnClickListener {

            rbtn_service.setTextColor(selectedTextColor)
            rbtn_service.background = selectedBackground

            rbtn_all.setTextColor(unSelectedTextColor)
            rbtn_coins.setTextColor(unSelectedTextColor)
            rbtn_live.setTextColor(unSelectedTextColor)


            rbtn_all.background = unSelectedBackground
            rbtn_coins.background = unSelectedBackground
            rbtn_live.background = unSelectedBackground

        }

    }

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

                                if (item.item == "activities"){

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

}// public class================================================================