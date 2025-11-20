package com.rk_softwares.e_commerce.server

import android.app.Activity
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.google.gson.Gson
import com.rk_softwares.e_commerce.Other.DomainHelper
import com.rk_softwares.e_commerce.model.ProductModel
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

class SuggestionServer(

    private var activity: Activity,
    private var actSearch : AppCompatAutoCompleteTextView

) {

   private var filterList : ArrayList<String> = ArrayList()
   private lateinit var adapter : ArrayAdapter<String>

    fun searchSuggestionFromServer(query : CharSequence){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url(DomainHelper.getProductLink())
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {


            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful){

                    val data = response.body.string()

                    try {

                        val product = gson.fromJson(data, ProductModel::class.java)

                        if (product.status == "Success"){

                            filterList.clear()

                            for (item in product.data){

                                val title = item.title ?: ""

                                if (query.isEmpty()){

                                    return

                                }else{

                                    if (title.contains(query, true)){

                                        filterList.add(title)

                                    }

                                }


                            }

                            activity.runOnUiThread {

                                if (filterList.isNotEmpty()){

                                    adapter = ArrayAdapter(activity, android.R.layout.simple_dropdown_item_1line, filterList)
                                    actSearch.setAdapter(adapter)
                                    actSearch.showDropDown()

                                }else{

                                    actSearch.dismissDropDown()
                                    filterList.clear()
                                    adapter.clear()

                                }

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