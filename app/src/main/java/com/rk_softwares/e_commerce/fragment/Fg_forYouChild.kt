package com.rk_softwares.e_commerce.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rk_softwares.e_commerce.model.ProductModel
import com.rk_softwares.e_commerce.Other.Cache
import com.rk_softwares.e_commerce.Other.DomainHelper
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.adapter.Product
import com.rk_softwares.e_commerce.server.CartServer
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

    //other
    private lateinit var cache : Cache
    private lateinit var cartServer: CartServer

    // XML id's------------------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_home_tab_item_for_you, container, false)

        //identity period-------------------------------------------

        rv_for_you = view.findViewById(R.id.rv_for_you)

        //identity period-------------------------------------------

        pAdapter = Product(requireActivity(), p_list)
        rv_for_you.adapter = pAdapter

        cache = Cache(requireActivity(), "for_you")
        cartServer = CartServer(requireActivity())

        lifecycleScope.launch {

            cartServer.suggestedItem(rv_for_you, p_list, pAdapter)
            delay(2000)

        }

        return view
    }//on create====================================================

}// public class====================================================