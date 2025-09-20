package com.rk_sofwares.e_commerce.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.rk_sofwares.e_commerce.Other.EdgeToEdge
import com.rk_sofwares.e_commerce.R


class Fg_messages : Fragment() {

    //XML id's---------------------------------------------------------------

    //toolbar
    private lateinit var fl_toolbar : FrameLayout

    //other
    private lateinit var edge_to_edge : EdgeToEdge

    //XML id's---------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_messages, container, false)

        //identity period---------------------------------------------------

        //toolbar
        fl_toolbar = view.findViewById(R.id.fl_toolbar)

        //identity period---------------------------------------------------

        edge_to_edge = EdgeToEdge(requireActivity())
        edge_to_edge.setEdgeToEdge()
        edge_to_edge.setToolBar(fl_toolbar)


        return view
    }//on create==========================================================

}//public class============================================================