package com.rk_sofwares.e_commerce.Other

import android.content.Context
import android.content.Intent

object IntentHelper{

    fun setDataIntent(context: Context ,sendClass : Class<*>, key : String, data : String){

        val intent = Intent(context, sendClass)
        intent.putExtra(key, data)
        context.startActivity(intent)

    }

}