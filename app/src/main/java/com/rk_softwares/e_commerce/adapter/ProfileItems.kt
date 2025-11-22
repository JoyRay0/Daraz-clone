package com.rk_softwares.e_commerce.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.rk_softwares.e_commerce.Other.IntentHelper
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.activity.Act_home
import com.squareup.picasso.Picasso

class ProfileItems() : RecyclerView.Adapter<ProfileItems.profileItemHolder>() {

    private lateinit var context : Context
    private lateinit var i_list : ArrayList<HashMap<String, String>>

    constructor(context: Context, list : ArrayList<HashMap<String, String>>) : this(){

        this.context = context
        this.i_list = list

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): profileItemHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.lay_adapter_profile_short_item, parent, false)

        return profileItemHolder(view)

    }

    override fun onBindViewHolder(holder: profileItemHolder, position: Int) {

        val item = i_list[position]

        val image = item["image"] ?: "No image"
        val text = item["text"] ?: "N/A"
        val click_item = item["item"] ?: "No item"

        Picasso.get().load(image).error(R.drawable.img_loading_daraz).into(holder.iv_item_image)
        holder.tv_item_text.text = text

        holder.ll_click.setOnClickListener {

            if (click_item == "message"){

                IntentHelper.setDataIntent(context, Act_home::class.java, "item", "Fg_message")

            }

        }


    }
    override fun getItemCount(): Int {

      return  i_list.size
    }

    class profileItemHolder(view: View) : RecyclerView.ViewHolder(view){

        val iv_item_image = view.findViewById<AppCompatImageView>(R.id.iv_item_image)
        val tv_item_text = view.findViewById<AppCompatTextView>(R.id.tv_item_text)
        val ll_click = view.findViewById<LinearLayout>(R.id.ll_click)

    }

}