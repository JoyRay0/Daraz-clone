package com.rk_softwares.e_commerce.Other

import androidx.fragment.app.Fragment

object ItemClick {

    interface  onItemClickedListener {
        fun onItemClick(text : String?)

    }

    interface DataListener{

        fun sendData(data : String)

    }

    interface ClassListener{

        fun className(sendClass : Class<*>, fragment: String? = null)

    }

}