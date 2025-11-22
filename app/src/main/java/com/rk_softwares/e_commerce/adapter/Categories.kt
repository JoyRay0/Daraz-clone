package com.rk_softwares.e_commerce.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.rk_softwares.e_commerce.R
import com.squareup.picasso.Picasso

class Categories() : RecyclerView.Adapter<Categories.catHolder>() {

    private lateinit var context : Context
    private lateinit var itemList : ArrayList<HashMap<String, String>>

    constructor(context: Context, list : ArrayList<HashMap<String, String>>):this(){

        this.context = context
        this.itemList = list

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): catHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.lay_adapter_child_categories_design, parent, false)

        return catHolder(view)
    }

    override fun onBindViewHolder(holder: catHolder, position: Int) {

        val item = itemList[position]

        if (!item["text"].isNullOrEmpty() && !item["image"].isNullOrEmpty()){

            holder.tv_cat_name.text = item["text"] ?: ""
            Picasso.get().load(item["image"] ?: "").into(holder.iv_cat)

        }else{

            Toast.makeText(context, "Please try again", Toast.LENGTH_SHORT).show()

        }

    }

    override fun getItemCount(): Int {

        return itemList.size
    }

    class catHolder (view : View) : RecyclerView.ViewHolder(view){

        val iv_cat = view.findViewById<AppCompatImageView>(R.id.iv_cat)
        val tv_cat_name = view.findViewById<AppCompatTextView>(R.id.tv_cat_name)

    }
}