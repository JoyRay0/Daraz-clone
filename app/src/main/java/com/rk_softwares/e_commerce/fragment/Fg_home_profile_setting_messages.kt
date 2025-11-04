package com.rk_softwares.e_commerce.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import com.rk_softwares.e_commerce.Other.StorageHelper
import com.rk_softwares.e_commerce.R


class Fg_home_profile_setting_messages : Fragment() {

    //XML id's-------------------------------------------------------------

    private lateinit var sc_promo : SwitchCompat
    private lateinit var sc_order : SwitchCompat
    private lateinit var sc_act : SwitchCompat
    private lateinit var sc_seller : SwitchCompat
    private lateinit var sc_chat : SwitchCompat
    private lateinit var sc_email : SwitchCompat
    private lateinit var sc_sms : SwitchCompat
    private lateinit var sc_whatsapp : SwitchCompat

    //other
    private lateinit var storage : StorageHelper

    //XML id's-------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_profile_setting_messages, container, false)

        //identity period--------------------------------------------------------

        sc_promo = view.findViewById(R.id.sc_promo)
        sc_order = view.findViewById(R.id.sc_order)
        sc_act = view.findViewById(R.id.sc_act)
        sc_seller = view.findViewById(R.id.sc_seller)
        sc_chat = view.findViewById(R.id.sc_chat)
        sc_email = view.findViewById(R.id.sc_email)
        sc_sms = view.findViewById(R.id.sc_sms)
        sc_whatsapp = view.findViewById(R.id.sc_whatsapp)

        //identity period--------------------------------------------------------

        storage = StorageHelper(requireActivity(), "setting_message")

        getSwitchData("promo", "promoOff", "promoOn", sc_promo, false)
        getSwitchData("order", "orderOff", "orderOn", sc_order, true)
        getSwitchData("act", "actOff", "actOn", sc_act, true)
        getSwitchData("seller", "sellerOff", "sellerOn", sc_seller, false)
        getSwitchData("chat", "chatOff", "chatOn", sc_chat, true)
        getSwitchData("email", "emailOff", "emailOn", sc_email, false)
        getSwitchData("sms", "smsOff", "smsOn", sc_sms, false)
        getSwitchData("whatsapp", "whatsappOff", "whatsappOn", sc_whatsapp, false)

        setSwitchData(sc_promo, "promo", "promoOn", "promoOff")
        setSwitchData(sc_order, "order", "orderOn", "orderOff")
        setSwitchData(sc_act, "act", "actOn", "actOff")
        setSwitchData(sc_seller, "seller", "sellerOn", "sellerOff")
        setSwitchData(sc_chat, "chat", "chatOn", "chatOff")
        setSwitchData(sc_email, "email", "emailOn", "emailOff")
        setSwitchData(sc_sms, "sms", "smsOn", "smsOff")
        setSwitchData(sc_whatsapp, "whatsapp", "whatsappOn", "whatsappOff")

        return view
    }// on create==================================================================

    private fun setSwitchData(sc : SwitchCompat, cacheKey : String, switchOn : String, switchOff : String){

        sc.setOnCheckedChangeListener { _,isChecked ->

            val data = if (sc.isChecked) switchOn else switchOff

            storage.deleteData(cacheKey)
            storage.setData(cacheKey, data)

            allMessages()

        }

    }

    private fun getSwitchData(cacheKey : String, switchOff : String, switchOn : String ,sc : SwitchCompat, switchBool : Boolean){

        val data = storage.getData(cacheKey)

        if (data == null){

            sc.isChecked = switchBool

            val value = if (switchBool) switchOn else switchOff

            storage.setData(cacheKey, value)

        }else{

            if (data == switchOn) sc.isChecked = true else sc.isChecked = false

        }

    }

    private fun allMessages(){

        if ((sc_promo.isChecked) || (sc_order.isChecked) || (sc_act.isChecked) || (sc_seller.isChecked) ||
            (sc_chat.isChecked) || (sc_email.isChecked) || (sc_sms.isChecked) || (sc_whatsapp.isChecked)){

            storage.setData("all_message", "On")

        }else{

            storage.setData("all_message", "Off")
        }

    }

}// public class===================================================================