package com.rk_sofwares.e_commerce.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.rk_sofwares.e_commerce.R
import com.rk_sofwares.e_commerce.database.Address
import java.util.ArrayList
import java.util.HashMap

class AddressAdapter() : RecyclerView.Adapter<AddressAdapter.holder>() {

    private lateinit var context: Context
    private lateinit var list : ArrayList<HashMap<String, String>>
    private lateinit var addressDB : Address

    constructor(context: Context, list : ArrayList<HashMap<String, String>>): this(){

        this.context = context
        this.list = list
        this.addressDB = Address(context)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {

        val view = LayoutInflater.from(context).inflate(R.layout.lay_address, parent, false)

        return holder(view)
    }

    override fun onBindViewHolder(holder: AddressAdapter.holder, position: Int) {

        val item = list[position]

        val name = item["name"] ?: ""
        val number =  item["number"] ?: ""
        val address = item["address"] ?: ""
        val add_cate = item["address_category"] ?: ""
        val region = item["region"] ?: ""
        val city = item["city"] ?: ""
        val district = item["district"] ?: ""
        val shipping = item["shipping_address"] ?: ""
        val billing = item["billing_address"] ?: ""

        holder.tv_name.text = name
        holder.tv_number.text = number
        holder.tv_address.text = address
        holder.tv_regin_city_district.text = region+","+city+","+district
        holder.tv_add_cate.text = add_cate
        holder.tv_shipping_billing.text = shipping+"&"+billing

        holder.iv_delete.setOnClickListener {

            val position = holder.adapterPosition

            if (position != RecyclerView.NO_POSITION && position < list.size){

                list.removeAt(position)
                notifyItemRemoved(position)
                notifyDataSetChanged()

                if (addressDB.deleteOne(name)){

                    Toast.makeText(context, "Address delete successfully", Toast.LENGTH_SHORT).show()

                }

            }

        }

    }

    override fun getItemCount(): Int {

        return list.size
    }

    class holder (view : View) : RecyclerView.ViewHolder(view){

        val tv_name = view.findViewById<AppCompatTextView>(R.id.tv_name)
        val tv_number = view.findViewById<AppCompatTextView>(R.id.tv_number)
        val tv_address = view.findViewById<AppCompatTextView>(R.id.tv_address)
        val tv_regin_city_district = view.findViewById<AppCompatTextView>(R.id.tv_regin_city_district)
        val tv_add_cate = view.findViewById<AppCompatTextView>(R.id.tv_add_cate)
        val tv_shipping_billing = view.findViewById<AppCompatTextView>(R.id.tv_shipping_billing)
        val iv_delete = view.findViewById<AppCompatImageView>(R.id.iv_delete)


    }

}