package com.rk_sofwares.e_commerce.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rk_sofwares.e_commerce.Model.ProductModel
import com.rk_sofwares.e_commerce.Other.EdgeToEdge
import com.rk_sofwares.e_commerce.R
import com.rk_sofwares.e_commerce.adapter.Product
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


class Fg_cart : Fragment() {

    //XML id's-------------------------------------------------------

    private lateinit var fl_toolbar : FrameLayout
    private lateinit var ll_empty_cart : LinearLayout
    private lateinit var rv_cart_item : RecyclerView
    private lateinit var btn_start_shopping : AppCompatTextView
    private lateinit var rv_suggestion_item : RecyclerView

    //others
    private lateinit var edge_to_edge : EdgeToEdge
    private var p_list : ArrayList<HashMap<String, Any>> = ArrayList()
    private lateinit var p_map : HashMap<String, Any>
    private lateinit var pAdapter : Product

    //XML id's-------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_cart, container, false)

        //identity period-----------------------------------------------------

        fl_toolbar = view.findViewById(R.id.fl_toolbar)
        ll_empty_cart = view.findViewById(R.id.ll_empty_cart)
        rv_cart_item = view.findViewById(R.id.rv_cart_item)
        btn_start_shopping = view.findViewById(R.id.btn_start_shopping)
        rv_suggestion_item = view.findViewById(R.id.rv_suggestion_item)

        //identity period-----------------------------------------------------

        edge_to_edge = EdgeToEdge(requireActivity())
        edge_to_edge.setToolBar(fl_toolbar)

        btn_start_shopping.setOnClickListener {

            startActivity(Intent(requireActivity(), Act_home::class.java))

        }

        pAdapter = Product(requireActivity(), p_list)
        rv_suggestion_item.adapter = pAdapter

        suggestItem()

        return view
    }//on create===============================================================

    //suggestion for user-------------------------------------------------------
    private fun suggestItem(){

        val client = OkHttpClient()

        val gson = Gson()

        val request = Request.Builder()
            .url("https://dummyjson.com/products")
            .build()

        client.newCall(request).enqueue(object : Callback{

            override fun onFailure(call: Call, e: IOException) {

                CoroutineScope(Dispatchers.Main).launch {

                    rv_suggestion_item.visibility = View.GONE

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

                            CoroutineScope(Dispatchers.Main).launch {

                                rv_suggestion_item.visibility = View.VISIBLE
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

}//public class=================================================================