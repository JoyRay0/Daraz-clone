package com.rk_softwares.e_commerce.Other

import android.app.Activity
import android.content.Context
import android.content.Intent

object IntentHelper{

    fun setDataIntent(act: Context, sendClass : Class<*>?, key : String, data : String){

        val intent = Intent(act, sendClass)
        intent.putExtra(key, data)
        act.startActivity(intent)

    }

    fun intent(act: Activity, sendClass: Class<*>){

        act.startActivity(Intent(act, sendClass))

    }

}