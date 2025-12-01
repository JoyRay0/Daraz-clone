package com.rk_softwares.e_commerce.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.rk_softwares.e_commerce.Other.IntentHelper
import com.rk_softwares.e_commerce.Other.ItemClick
import com.rk_softwares.e_commerce.Other.ShortMessageHelper
import com.rk_softwares.e_commerce.Other.StorageHelper
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.activity.Act_home
import com.rk_softwares.e_commerce.adapter.IntroAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class Fg_user_gide : Fragment() {

    //XML id's---------------------------------------------------------

    private lateinit var vp_into : ViewPager2
    private lateinit var dotsIndicator : DotsIndicator
    private lateinit var btn_next : MaterialButton
    private lateinit var btn_skip : MaterialButton

    //other
    private var introList : ArrayList<HashMap<String, String>> = ArrayList()
    private lateinit var introMap : HashMap<String, String>

    private lateinit var introAdapter : IntroAdapter
    private lateinit var storageHelper: StorageHelper

    //init
    private var buttonText = "Next"

    //XML id's---------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_user_gide, container, false)

        init(view)

        intoItem()
        introAdapter = IntroAdapter(requireActivity(), introList)
        vp_into.adapter = introAdapter
        dotsIndicator.attachTo(vp_into)




        button()

        return view
    }//on create============================================================

    private fun init(view: View){

        vp_into = view.findViewById(R.id.vp_into)
        dotsIndicator = view.findViewById(R.id.dotsIndicator)
        btn_next = view.findViewById(R.id.btn_next)
        btn_skip = view.findViewById(R.id.btn_skip)

        storageHelper = StorageHelper(requireActivity(), "introduction")

    }

    private fun intoItem(){

        introMap = HashMap()
        introMap["image"] = R.drawable.img_daraz.toString()
        introMap["bold_text"] = "Welcome to Daraz"
        introMap["normal_text"] = "Your reliable online shopping partner."
        introList.add(introMap)

        introMap = HashMap()
        introMap["image"] = R.drawable.img_cardboard_box.toString()
        introMap["bold_text"] = "All products in one place"
        introMap["normal_text"] = "Discover your desired items with the best deals every day."
        introList.add(introMap)

        introMap = HashMap()
        introMap["image"] = R.drawable.img_online_shopping.toString()
        introMap["bold_text"] = "Find the Best Deals"
        introMap["normal_text"] = "Shop top offers, discounts, and exclusive coupons curated for you."
        introList.add(introMap)

        introMap = HashMap()
        introMap["image"] = R.drawable.img_rocket.toString()
        introMap["bold_text"] = "Explore the App"
        introMap["normal_text"] = "Let’s get started on your shopping journey!"
        introList.add(introMap)

    }

    private fun button(){

       vp_into.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){

           override fun onPageSelected(position: Int) {
               super.onPageSelected(position)

               if (position == introList.size - 1){

                   btn_next.text = "Finish"
                   buttonText = "Finish"
                   btn_skip.visibility = View.GONE

               }else{

                   btn_next.text = "Next"
                   buttonText = "Next"
                   btn_skip.visibility = View.VISIBLE

               }

           }

       })

        btn_next.setOnClickListener {

            if (buttonText == "Finish"){

                hideIntro()

            }else{

                if (vp_into.currentItem < introList.size - 1) vp_into.currentItem += 1

            }


        }

        btn_skip.setOnClickListener {

            hideIntro()

        }

    }

    private fun hideIntro(){

        var act = activity as Act_home

        storageHelper.setData("intro", "showed")

        act.introduction()


    }

}// class====================================================================