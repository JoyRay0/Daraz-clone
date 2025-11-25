package com.rk_softwares.e_commerce.Other

object ItemClick {

    interface  onItemClickedListener {
        fun onItemClick(text : String?)

    }

    interface DataListener{

        fun sendData(data : String)

    }

}