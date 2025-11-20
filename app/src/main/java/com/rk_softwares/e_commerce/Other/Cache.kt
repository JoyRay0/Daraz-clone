package com.rk_softwares.e_commerce.Other

import android.content.Context

class Cache {

    private var context: Context
    private var pref : String


    constructor(context: Context, pref: String){

        this.context = context
        this.pref = pref

    }


     fun setCache(key : String ,data : String){

        var sp = context.getSharedPreferences(pref, Context.MODE_PRIVATE)
        sp.edit().putString(key, data).putLong("${key}_time", System.currentTimeMillis()).apply()

    }

    fun getCache( key : String, maxAgeMinutes : Int): String ?{

        val sp = context.getSharedPreferences(pref, Context.MODE_PRIVATE)
        val data = sp.getString(key, null)
        val time = sp.getLong("${key}_time", 0)

        val now = System.currentTimeMillis()
        val age = (now - time) / 1000 / 60

        if (data != null && age <= maxAgeMinutes){

            return data
        }else{

            return null
        }

    }

    fun deleteCache(key : String){

        val sp = context.getSharedPreferences(pref, Context.MODE_PRIVATE)
        sp.edit().remove(key).apply()

    }

    fun deleteAllCache(){

        val sp = context.getSharedPreferences(pref, Context.MODE_PRIVATE)
        sp.edit().clear().apply()

    }

}