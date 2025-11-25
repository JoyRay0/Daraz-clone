package com.rk_softwares.e_commerce.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.rk_softwares.e_commerce.R
import com.squareup.picasso.Picasso

class IntroAdapter(

    private var context: Context,
    private var list : ArrayList<HashMap<String, String>>

) : RecyclerView.Adapter<IntroAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val view = LayoutInflater.from(context).inflate(R.layout.lay_adapter_intro, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val item = list[position]
        val image = item["image"]
        val boldText = item["bold_text"]
        val normalText = item["normal_text"]

        Picasso.get().load(image?.toInt() ?: 0).into(holder.iv_image)
        holder.tv_bold_text.text = boldText.toString()
        holder.tv_normal_text.text = normalText.toString()

        Log.d("intro", list.size.toString())

    }

    override fun getItemCount(): Int {

        return list.size
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view){

        val iv_image = view.findViewById<AppCompatImageView>(R.id.iv_image)
        val tv_bold_text = view.findViewById<AppCompatTextView>(R.id.tv_bold_text)
        val tv_normal_text = view.findViewById<AppCompatTextView>(R.id.tv_normal_text)


    }

}