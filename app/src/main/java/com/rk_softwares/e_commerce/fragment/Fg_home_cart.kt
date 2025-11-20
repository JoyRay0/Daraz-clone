package com.rk_softwares.e_commerce.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rk_softwares.e_commerce.model.ProductModel
import com.rk_softwares.e_commerce.Other.DomainHelper
import com.rk_softwares.e_commerce.Other.EdgeToEdge
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.activity.Act_home
import com.rk_softwares.e_commerce.adapter.Product
import com.rk_softwares.e_commerce.server.CartServer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import java.lang.Exception

class Fg_home_cart : Fragment() {

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
    private lateinit var cartServer : CartServer

    //XML id's-------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_home_cart, container, false)

        //identity period-----------------------------------------------------

        fl_toolbar = view.findViewById(R.id.fl_toolbar)
        ll_empty_cart = view.findViewById(R.id.ll_empty_cart)
        rv_cart_item = view.findViewById(R.id.rv_cart_item)
        btn_start_shopping = view.findViewById(R.id.btn_start_shopping)
        rv_suggestion_item = view.findViewById(R.id.rv_suggestion_item)

        //identity period-----------------------------------------------------

        edge_to_edge = EdgeToEdge(requireActivity())
        edge_to_edge.setToolBar(fl_toolbar)

        cartServer = CartServer(requireActivity())

        btn_start_shopping.setOnClickListener {

            startActivity(Intent(requireActivity(), Act_home::class.java))

        }

        pAdapter = Product(requireActivity(), p_list)
        rv_suggestion_item.adapter = pAdapter



        lifecycleScope.launch(Dispatchers.IO){

            //cartServer.suggestedItem(rv_suggestion_item, p_list, pAdapter)

            withContext( Dispatchers.Main){

                p_list.clear()

            }

        }

        return view
    }//on create===============================================================

}//public class=================================================================