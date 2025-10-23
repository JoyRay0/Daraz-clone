package com.rk_sofwares.e_commerce.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rk_sofwares.e_commerce.Model.ProductModel
import com.rk_sofwares.e_commerce.Other.Cache
import com.rk_sofwares.e_commerce.R
import com.rk_sofwares.e_commerce.adapter.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import java.lang.Exception


class Fg_tab_item_for_upu : Fragment() {

    // XML id's------------------------------------------------------------------------

    private lateinit var rv_for_you : RecyclerView
    private lateinit var pAdapter : Product
    private var p_list : ArrayList<HashMap<String, Any>> = ArrayList()
    private lateinit var p_map : HashMap<String, Any>
    private lateinit var cache : Cache

    // XML id's------------------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_home_tab_item_for_you, container, false)

        //identity period-------------------------------------------

        rv_for_you = view.findViewById(R.id.rv_for_you)

        //identity period-------------------------------------------

        pAdapter = Product(requireActivity(), p_list)
        rv_for_you.adapter = pAdapter

        cache = Cache(requireActivity(), "for_you")

        CoroutineScope(Dispatchers.Main).launch {

            data_from_cache()
            delay(2000)

        }



        return view
    }//on create====================================================

    //all_product-----------------------------------------------------------
    private fun for_you(){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url("https://dummyjson.com/products")
            .build()

        client.newCall(request).enqueue(object : Callback{

            override fun onFailure(call: Call, e: IOException) {

                CoroutineScope(Dispatchers.Main).launch {



                }

            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful){

                    val data = response.body.string()


                    try {

                        val product = gson.fromJson(data, ProductModel::class.java)


                        if (product.products.isNotEmpty()){

                            p_list.clear()

                            for (item in product.products){

                                p_map = HashMap()
                                p_map["short_image"] = item.thumbnail ?: ""
                                p_map["title"] = item.title ?: ""
                                p_map["price"] = item.price ?: ""
                                p_map["discount"] = item.discountPercentage ?: ""
                                p_map["rating"] = item.rating ?: ""
                                p_map["in_stock"] = item.availabilityStatus ?: ""
                                p_list.add(p_map)

                            }

                            cache.setCache("short_product", gson.toJson(product))


                            //cache.setCache("cate_image", gson.toJson(c_img))

                            CoroutineScope(Dispatchers.Main).launch {

                                pAdapter.notifyDataSetChanged()

                            }

                        }else{

                            //Log.i("item", p_list.size.toString())

                        }



                    }catch (e : Exception){

                        e.printStackTrace()

                    }

                }


            }
        })

    }

    //data from cache--------------------------------------------------------
    private fun data_from_cache(){

        val cache_short_product = cache.getCache("short_product", 5)

        Log.i("cache", cache_short_product.toString())

        if (!cache_short_product.isNullOrEmpty()){

            val gson = Gson()

            val product = gson.fromJson(cache_short_product, ProductModel::class.java)


            if (product.products.isNotEmpty()) {

                p_list.clear()

                for (item in product.products) {

                    p_map = HashMap()
                    p_map["short_image"] = item.thumbnail ?: ""
                    p_map["title"] = item.title ?: ""
                    p_map["price"] = item.price ?: ""
                    p_map["discount"] = item.discountPercentage ?: ""
                    p_map["rating"] = item.rating ?: ""
                    p_map["in_stock"] = item.availabilityStatus ?: ""
                    p_list.add(p_map)

                }

                pAdapter.notifyDataSetChanged()
            }

        }else{

            for_you()

        }
    }

}// public class====================================================