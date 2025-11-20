package com.rk_softwares.e_commerce.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.rk_softwares.e_commerce.Other.ItemClick
import com.rk_softwares.e_commerce.R

class HistoryAdapter() : RecyclerView.Adapter<HistoryAdapter.holder>() {

    private lateinit var context: Context
    private lateinit var list : ArrayList<HashMap<String, String>>
    private lateinit var listener: ItemClick.onItemClickedListener

    constructor(context: Context, list : ArrayList<HashMap<String, String>>, listener: ItemClick.onItemClickedListener) : this(){

        this.context = context
        this.list = list
        this.listener = listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val view = LayoutInflater.from(context).inflate(R.layout.lay_history_adapter, parent, false)

        return holder(view)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.holder, position: Int) {

        val item = list[position]

        val history = item["history_data"]

        Log.e("data", list.toString())
        holder.text.text = history

        holder.cv_history.setOnClickListener {

           listener.onItemClick(history.toString())

        }

    }

    override fun getItemCount(): Int {

        return list.size
    }

    class holder(view : View) : RecyclerView.ViewHolder(view){

        val text = view.findViewById<AppCompatTextView>(R.id.tv_history)
        val cv_history = view.findViewById<CardView>(R.id.cv_history)

    }

}
