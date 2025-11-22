package com.rk_softwares.e_commerce.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rk_softwares.e_commerce.Other.Cache
import com.rk_softwares.e_commerce.Other.LazyLoading
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.adapter.Product
import com.rk_softwares.e_commerce.server.CartServer


class Fg_tab_item_for_upu : Fragment() {

    // XML id's------------------------------------------------------------------------

    private lateinit var rv_for_you : RecyclerView
    private lateinit var pAdapter : Product
    private var p_list : ArrayList<HashMap<String, Any>> = ArrayList()

    //other
    private lateinit var cache : Cache
    private lateinit var cartServer: CartServer
    private lateinit var lazy : LazyLoading

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

        lazy = LazyLoading(rv_for_you){ page  ->

            cartServer.suggestedItem(rv_for_you, p_list, pAdapter, page) {

                lazy.finishLoading()


            }

            Log.d("item", page.toString())

        }
        lazy.lazyScroll()

        cartServer.suggestedItem(rv_for_you, p_list, pAdapter, 1) {
            lazy.finishLoading()
        }

        return view
    }//on create====================================================

}// public class====================================================