package com.rk_softwares.e_commerce.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.rk_softwares.e_commerce.R
import com.squareup.picasso.Picasso
import java.util.ArrayList
import java.util.HashMap

class ViewpagerAdapter() : RecyclerView.Adapter<ViewpagerAdapter.viewHolder>() {

    private lateinit var context: Context
    private lateinit var vp_list : ArrayList<HashMap<String, String>>

    constructor(context: Context, list : ArrayList<HashMap<String, String>>) : this(){

        this.context = context
        this.vp_list = list

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.lay_viewpager_image, parent, false)

        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int){

        val item = vp_list[position]

        if (!item["image"].isNullOrEmpty()){

            Picasso.get().load(item["image"]).into(holder.vp_image)

        }else{

            Toast.makeText(context, "Please try again", Toast.LENGTH_SHORT).show()

        }

    }

    override fun getItemCount(): Int {

        return vp_list.size
    }

    class viewHolder(view : View) : RecyclerView.ViewHolder(view){

        val vp_image = view.findViewById<AppCompatImageView>(R.id.vp_image);

    }

}