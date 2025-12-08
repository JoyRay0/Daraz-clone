package com.rk_softwares.e_commerce.server

import android.app.Activity
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rk_softwares.e_commerce.Other.DomainHelper
import com.rk_softwares.e_commerce.adapter.Product
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

class FullProductInfo(
    activity: Activity
) {

    fun fullItem(
        list: ArrayList<HashMap<String, Any>> = arrayListOf(),
        page : Int = 1
    ){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url(DomainHelper.getProductLink()+page)
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {


            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful){

                    val data = response.body.string() ?: return

                    try {

                        val product = gson.fromJson(data, ProductModel::class.java)

                        Log.d("cart", data)

                        if (product.status == "Success"){

                            for (item in product.data){

                                val p_map : HashMap<String, Any> = HashMap()
                                p_map["short_image"] = item.thumbnail ?: ""
                                p_map["title"] = item.title ?: ""
                                p_map["price"] = item.price ?: ""
                                p_map["discount"] = item.discountPercentage ?: ""
                                p_map["rating"] = item.rating ?: ""
                                p_map["in_stock"] = item.availabilityStatus ?: ""
                                p_map["reviews"] = item.reviews ?: ""
                                list.add(p_map)

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