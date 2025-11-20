package com.rk_softwares.e_commerce.Other

import android.content.Context

class StorageHelper(

    private var context: Context,
    private var perf : String

) {

    fun setData(key : String, data : String){

        var sp = context.getSharedPreferences(perf, Context.MODE_PRIVATE)
        sp.edit().putString(key, data).apply()

    }

    fun getData(key : String): String?{

        val sp = context.getSharedPreferences(perf, Context.MODE_PRIVATE)
        val data = sp.getString(key, null)

        if (data != null) return data

        else return null

    }

    fun deleteData(key : String){

        var sp = context.getSharedPreferences(perf, Context.MODE_PRIVATE)
        sp.edit().remove(key).apply()
    }

}