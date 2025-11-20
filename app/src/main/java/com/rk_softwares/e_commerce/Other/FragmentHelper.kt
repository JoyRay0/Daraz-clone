package com.rk_softwares.e_commerce.Other

import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.rk_softwares.e_commerce.fragment.Fg_home_cart
import com.rk_softwares.e_commerce.fragment.Fg_home_home
import com.rk_softwares.e_commerce.fragment.Fg_home_messages
import com.rk_softwares.e_commerce.fragment.Fg_home_profile
import com.rk_softwares.e_commerce.fragment.Fg_my_order_all

class FragmentHelper(

    private var activity : AppCompatActivity

) {
    fun setFragment( value : String ,fragmentID : Int, nav : FrameLayout?){

        val intentValue = arrayOf("Fg_home",
            "Fg_cart", "Fg_messages", "Fg_profile", "Fg_pay", "Fg_ship", "Fg_receive", "Fg_review")


        if (intentValue.contains(value)){

            val fragment = when(value){

                "Fg_home" -> Fg_home_home()
                "Fg_cart" -> Fg_home_cart()
                "Fg_messages" -> Fg_home_messages()
                "Fg_profile" -> Fg_home_profile()
                "Fg_pay" -> Fg_my_order_all()
                "Fg_ship" -> Fg_my_order_all()
                "Fg_receive" -> Fg_my_order_all()
                "Fg_review" -> Fg_my_order_all()

                else -> null

            }

            if (fragment != null){

                activity.supportFragmentManager.beginTransaction().replace(fragmentID, fragment).commit()

                if (nav != null){

                    nav.visibility = View.VISIBLE

                }else{

                    nav?.visibility = View.GONE

                }


            }

        }

    }

}