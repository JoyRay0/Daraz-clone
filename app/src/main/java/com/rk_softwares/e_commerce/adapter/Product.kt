package com.rk_softwares.e_commerce.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.rk_softwares.e_commerce.Other.IntentHelper
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.activity.Act_product_full_info
import com.squareup.picasso.Picasso

class Product() : RecyclerView.Adapter<Product.productHolder>() {

    private lateinit var context : Context
    private lateinit var Plist : ArrayList<HashMap<String, Any>>


    constructor(context: Context, list : ArrayList<HashMap<String, Any>>) : this(){

        this.context = context
        this.Plist = list

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.lay_show_item, parent, false)


        return productHolder(view)
    }

    override fun onBindViewHolder(holder: productHolder, position: Int) {

        val item = Plist[position]

        if (item["short_image"].toString().isNotEmpty() && item["title"].toString().isNotEmpty() && item["price"].toString().isNotEmpty()
            && item["discount"].toString().isNotEmpty() && item["rating"].toString().isNotEmpty() && item["in_stock"].toString().isNotEmpty()){

            Picasso.get().load(item["short_image"].toString()).into(holder.iv_item_image)

            holder.tv_title.text = item["title"].toString()
            holder.tv_price.text = item["price"].toString()
            holder.tv_discount.text = item["discount"].toString()
            holder.tv_rating.text = item["rating"].toString()
            holder.tv_stock.text = item["in_stock"].toString()

        }else{

            Picasso.get().load(R.drawable.img_loading_daraz).into(holder.iv_item_image)

            holder.tv_title.text = "N/A"
            holder.tv_price.text = "0"
            holder.tv_discount.text = "0"
            holder.tv_rating.text = "0.0"
            holder.tv_stock.text = "out of stock"

        }



        holder.cv_btn.setOnClickListener {

            val til = item["title"] ?: ""
            val ids = item["id"] ?: ""
            val skus = item["sku"] ?: ""

            val intent = Intent(context, Act_product_full_info::class.java)
            intent.putExtra("titles", til.toString())
            intent.putExtra("ids", ids.toString())
            intent.putExtra("skus", skus.toString())
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {

        return Plist.size
    }

    class productHolder (view : View) : RecyclerView.ViewHolder(view){

        val cv_btn = view.findViewById<CardView>(R.id.cv_btn)
        val iv_item_image = view.findViewById<AppCompatImageView>(R.id.iv_item_image)
        val tv_title = view.findViewById<AppCompatTextView>(R.id.tv_title)
        val tv_price = view.findViewById<AppCompatTextView>(R.id.tv_price)
        val tv_discount = view.findViewById<AppCompatTextView>(R.id.tv_discount)
        val tv_rating = view.findViewById<AppCompatTextView>(R.id.tv_rating)
        val tv_stock = view.findViewById<AppCompatTextView>(R.id.tv_stock)

    }

}