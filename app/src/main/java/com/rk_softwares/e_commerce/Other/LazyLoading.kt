package com.rk_softwares.e_commerce.Other

import android.R
import android.app.Activity
import androidx.recyclerview.widget.RecyclerView

class LazyLoading(

    private val activity: Activity,
    private val rv : RecyclerView

) {

    var page = 1
    var isLoading = false
    var isLastPage = false

    fun rvScroll(){

        rv.addOnScrollListener(object  : RecyclerView.OnScrollListener(){



        })


    }

}