package com.rk_sofwares.e_commerce.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.rk_sofwares.e_commerce.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.util.ArrayList

class ItemAdapter() : RecyclerView.Adapter<ItemAdapter.viewHolder>() {

    private lateinit var context : Context
    private lateinit var itemList : ArrayList<HashMap<String, String>>

     constructor(context: Context, list : ArrayList<HashMap<String, String>>) : this(){

        this.context = context
        this.itemList = list

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.lay_item_img, parent, false)

        return viewHolder(view)

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        val item = itemList[position]

        //Log.i("item", item.size.toString())

        Picasso.get().load(item["image"] ?: "null").into(holder.iv_item_image)
        holder.tv_item_title.text = item["text"] ?: "null"



    }

    override fun getItemCount(): Int {

        Log.i("item", itemList.size.toString())
       return itemList.size
    }

    class viewHolder(view: View) : RecyclerView.ViewHolder(view){

        val iv_item_image : CircleImageView = view.findViewById(R.id.iv_item_image)
        val tv_item_title : AppCompatTextView = view.findViewById(R.id.tv_item_title)

    }

}