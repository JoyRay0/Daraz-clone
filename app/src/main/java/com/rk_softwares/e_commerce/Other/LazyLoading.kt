package com.rk_softwares.e_commerce.Other

import android.R
import android.app.Activity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LazyLoading(

    private val rv : RecyclerView,
    private val onLoad : (page : Int) -> Unit

) {

    private var page = 1
    private var isLoading = false
    private var isLastPage = false

    fun lazyScroll(){

       rv.addOnScrollListener(object : RecyclerView.OnScrollListener(){

           override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
               super.onScrolled(recyclerView, dx, dy)

               val lm = recyclerView.layoutManager as GridLayoutManager
               val visibleItemCount = lm.childCount
               val totalItemCount = lm.itemCount
               val firstVisibleItemPosition = lm.findFirstVisibleItemPosition()

               if (!isLoading && !isLastPage){

                   if (visibleItemCount + firstVisibleItemPosition >= totalItemCount - 2 && firstVisibleItemPosition >= 0){

                       loadMore()

                   }

               }

           }

       })

    }

    fun loadMore(){

        isLoading = true
        page++

        onLoad(page)

    }

    fun finishLoading(){

        isLoading = false
    }
}