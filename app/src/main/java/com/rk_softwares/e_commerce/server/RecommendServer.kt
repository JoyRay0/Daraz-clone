package com.rk_softwares.e_commerce.server

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rk_softwares.e_commerce.Other.DomainHelper
import com.rk_softwares.e_commerce.Other.ShortMessageHelper
import com.rk_softwares.e_commerce.adapter.RecommendAdapter
import com.rk_softwares.e_commerce.database.SearchHistory
import com.rk_softwares.e_commerce.model.SearchModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import java.lang.Exception
import java.net.URLEncoder

class RecommendServer(

    private val activity: Activity

) {

    fun searchRecommend(category : String, list : ArrayList<String>, ra : RecommendAdapter, rv : RecyclerView){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url(DomainHelper.getCategoryText() + URLEncoder.encode(category, "UTF-8"))
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {

                activity.runOnUiThread {

                    rv.visibility = View.GONE

                }

            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful){

                    val data = response.body.string()

                    try {

                        val search = gson.fromJson(data, SearchModel::class.java)

                        list.clear()

                        if (search.status == "Success"){

                            for (item in search.data){

                                list.add(item.title ?: "")

                            }

                            Log.d("recommend", search.data.toString())

                            activity.runOnUiThread {

                                rv.visibility = View.VISIBLE

                                ra.notifyDataSetChanged()

                            }

                        }else{

                            ShortMessageHelper.toast(activity, search.message)

                        }

                    }catch (e : Exception){

                        e.printStackTrace()

                    }

                }


            }
        })

    }

}