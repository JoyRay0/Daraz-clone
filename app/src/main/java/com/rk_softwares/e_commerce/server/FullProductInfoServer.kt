package com.rk_softwares.e_commerce.server

import android.app.Activity
import android.util.Log
import com.google.gson.Gson
import com.rk_softwares.e_commerce.Other.DomainHelper
import com.rk_softwares.e_commerce.model.PostModel
import com.rk_softwares.e_commerce.model.ProductModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okio.IOException
import java.lang.Exception
import com.rk_softwares.e_commerce.model.Product

class FullProductInfoServer(
   val activity: Activity
) {

    fun fullItem(
        itemTitle : String = "",
        id : String = "",
        sku : String = "",
        pageLoaded : (Boolean) -> Unit = {},
        onResult : (Product) -> Unit = {}
    ) {

        val client = OkHttpClient()

        val gson = Gson()


        //if ((itemTitle != "null" || itemTitle.isNotEmpty()) && (id != "null" || id.isNotEmpty()) && (sku != "null" || sku.isNotEmpty())) {

        //}

        val post = gson.toJson(PostModel(id = id.toInt(), title = itemTitle, sku = sku))

        val requestBody = post.toRequestBody("application/json; charset=utf-8".toMediaType())

        val request = Request.Builder()
            .url(DomainHelper.getProductInfoLink())
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {

                pageLoaded(false)

            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful){

                    val data = response.body.string() ?: return

                    try {

                        val productModel = gson.fromJson(data, ProductModel::class.java)


                        activity.runOnUiThread {

                            //list.clear()

                            val item = productModel.products.firstOrNull() ?: return@runOnUiThread

                            onResult(item)

                            pageLoaded(true)

                        }



                    }catch (e : Exception){

                        e.printStackTrace()


                    }

                }


            }
        })


    }

}