package com.rk_softwares.e_commerce.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import com.rk_softwares.e_commerce.R
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.rk_softwares.e_commerce.Other.ItemClick

class RecommendAdapter(

    private var context: Context,
    private var list : ArrayList<String>,
    private var listener : ItemClick.onItemClickedListener

) : RecyclerView.Adapter<RecommendAdapter.holder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {

        val view = LayoutInflater.from(context).inflate(R.layout.lay_history_adapter, parent, false)

        return holder(view)
    }

    override fun onBindViewHolder(holder: RecommendAdapter.holder, position: Int) {

        val item = list[position]
        val text = item

        holder.text.text = text

        holder.cv_history.setOnClickListener {

            listener.onItemClick(text)

        }

    }

    override fun getItemCount(): Int {

      return  list.size
    }

    class holder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val text = itemView.findViewById<AppCompatTextView>(R.id.tv_history)
        val cv_history = itemView.findViewById<CardView>(R.id.cv_history)

    }

}

