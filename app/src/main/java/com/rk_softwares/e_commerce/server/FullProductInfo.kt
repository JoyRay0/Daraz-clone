package com.rk_softwares.e_commerce.server

import android.app.Activity
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rk_softwares.e_commerce.Other.DomainHelper
import com.rk_softwares.e_commerce.adapter.Product
import com.rk_softwares.e_commerce.model.NewModel
import com.rk_softwares.e_commerce.model.PostModel
import com.rk_softwares.e_commerce.model.ProductModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okio.IOException
import java.lang.Exception

class FullProductInfo(
    activity: Activity
) {

    fun fullItem(
        list: ArrayList<HashMap<String, Any>> = arrayListOf(),
        itemTitle : String,
        id : Int,
        sku : String
    ){

        val client = OkHttpClient()

        val gson = Gson()

        val post = gson.toJson(PostModel(id = id, title = itemTitle, sku = sku))

        val requestBody = post.toRequestBody("application/json; charset=utf-8".toMediaType())

        val request = Request.Builder()
            .url(DomainHelper.getProductLink())
            .post(requestBody)
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

                                val productMap : HashMap<String, Any> = HashMap()

                                productMap["id"] = item.id ?: 0
                                productMap["title"] = item.title ?: ""
                                productMap["description"] = item.description ?: ""
                                productMap["category"] = item.category ?: ""
                                productMap["price"] = item.price ?: 0.0
                                productMap["discountPercentage"] = item.discountPercentage ?: ""
                                productMap["rating"] = item.rating ?: 0.0
                                productMap["stock"] = item.stock ?: 0
                                productMap["brand"] = item.brand ?: ""
                                productMap["sku"] = item.sku ?: ""
                                productMap["weight"] = item.weight ?: 0
                                productMap["warrantyInformation"] = item.warrantyInformation ?: ""
                                productMap["shippingInformation"] = item.shippingInformation ?: ""
                                productMap["availabilityStatus"] = item.availabilityStatus ?: ""
                                productMap["returnPolicy"] = item.returnPolicy ?: ""
                                productMap["minimumOrderQuantity"] = item.minimumOrderQuantity ?: ""
                                productMap["thumbnail"] = item.thumbnail ?: ""

                                for (tag in item.tags){

                                    productMap["tags"] = tag.id ?: ""

                                }

                                list.add(productMap)

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