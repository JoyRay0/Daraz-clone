package com.rk_softwares.e_commerce.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.rk_softwares.e_commerce.R
import com.squareup.picasso.Picasso

class ProductImageAdapter(

    private var context: Context,
    private var list: ArrayList<HashMap<String, String>>

) : RecyclerView.Adapter<ProductImageAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val view = LayoutInflater.from(context).inflate(R.layout.lay_product_image, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val mainImage = list[position]
        val image = mainImage["image"] ?: ""

        Picasso
            .get()
            .load(image)
            .placeholder(R.drawable.img_loading_daraz)
            .error(R.drawable.img_loading_daraz)
            .into(holder.iv_product_image)

    }

    override fun getItemCount(): Int {

        return list.size
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view){

        val iv_product_image = view.findViewById<AppCompatImageView>(R.id.iv_product_image)

    }

}