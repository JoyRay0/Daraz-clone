package com.rk_softwares.e_commerce.Other

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.OnBackPressedCallback

object IntentHelper{

    fun setDataIntent(context: Context ,sendClass : Class<*>, key : String, data : String){

        val intent = Intent(context, sendClass)
        intent.putExtra(key, data)
        context.startActivity(intent)


    }

    fun intent(act: Activity, sendClass: Class<*>){

        act.startActivity(Intent(act, sendClass))
        act.finishAffinity()

    }

}