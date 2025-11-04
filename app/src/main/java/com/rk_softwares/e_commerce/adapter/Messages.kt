package com.rk_softwares.e_commerce.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.rk_softwares.e_commerce.R
import com.squareup.picasso.Picasso

class Messages() : RecyclerView.Adapter<Messages.mHolder>() {

    private var m_list : ArrayList<HashMap<String, String>> = ArrayList()
    private lateinit var context: Context

    var isDotVisible : Boolean = false

    constructor(context: Context, list : ArrayList<HashMap<String, String>>):this(){

        this.context = context
        this.m_list = list

    }

    fun updateVisibility(isVisible : Boolean){

        this.isDotVisible = isVisible
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.lay_messages, parent, false)

        return mHolder(view)

    }

    override fun onBindViewHolder(holder: mHolder, position: Int){

        val item = m_list[position]

        val title = item["title"] ?: "N/A"
        val date = item["date"] ?: "0/0/0000"
        val main_image = item["image"] ?: "No image"
        val shot_message = item["message"] ?: "No Message"
        val short_icon = item["item"] ?: "N/A"

        if (short_icon == "activities"){

            holder.cv_image.setCardBackgroundColor("#FFAB37".toColorInt())
            holder.iv_activitiesOrPromos.setImageResource(R.drawable.ic_activity)

        }else{

            holder.cv_image.setCardBackgroundColor("#E91E63".toColorInt())
            holder.iv_activitiesOrPromos.setImageResource(R.drawable.ic_speaker)

        }

        holder.tv_title.text = title
        holder.tv_date.text = date
        holder.tv_short_message.text = shot_message
        Picasso.get().load(main_image).error(R.drawable.img_loading_daraz).into(holder.iv_image)


        holder.cb_box.visibility = View.GONE
        holder.iv_delete.visibility = View.GONE
        holder.cb_box.isChecked = false



        if (isDotVisible){

            holder.tv_dot.visibility = View.GONE

        }else{

            holder.tv_dot.visibility = View.VISIBLE
        }

        holder.cv_message.setOnLongClickListener {

            holder.cb_box.visibility = View.VISIBLE
            holder.iv_delete.visibility = View.VISIBLE
            holder.cb_box.isChecked = true

            true
        }

        holder.cb_box.setOnClickListener {

            holder.cb_box.visibility = View.GONE
            holder.iv_delete.visibility = View.GONE

        }

        holder.cv_message.setOnClickListener {

            if (holder.cb_box.visibility != View.VISIBLE){

                //Toast.makeText(context, "worled", Toast.LENGTH_SHORT).show()
            }

        }

        holder.iv_delete.setOnClickListener {

            val pos = holder.adapterPosition
            if (pos != RecyclerView.NO_POSITION && pos < m_list.size){

                m_list.removeAt(pos)
                notifyItemRemoved(pos)
                notifyDataSetChanged()

            }

        }

    }

    override fun getItemCount(): Int {

        return m_list.size
    }

    class mHolder (view: View) : RecyclerView.ViewHolder(view){


        val cv_message = view.findViewById<CardView>(R.id.cv_message)
        val cv_image = view.findViewById<CardView>(R.id.cv_image)
        val iv_activitiesOrPromos = view.findViewById<AppCompatImageView>(R.id.iv_activitiesOrPromos)
        val tv_title = view.findViewById<AppCompatTextView>(R.id.tv_title)
        val tv_date = view.findViewById<AppCompatTextView>(R.id.tv_date)
        val iv_image = view.findViewById<AppCompatImageView>(R.id.iv_image)
        val tv_short_message = view.findViewById<AppCompatTextView>(R.id.tv_short_message)
        val cb_box = view.findViewById<AppCompatCheckBox>(R.id.cb_box)
        val iv_delete = view.findViewById<AppCompatImageView>(R.id.iv_delete)
        val tv_dot = view.findViewById<AppCompatTextView>(R.id.tv_dot)


    }

}