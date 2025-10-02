package com.rk_sofwares.e_commerce.Other

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rk_sofwares.e_commerce.activity.Fg_cart
import com.rk_sofwares.e_commerce.activity.Fg_home
import com.rk_sofwares.e_commerce.activity.Fg_messages
import com.rk_sofwares.e_commerce.activity.Fg_profile
import com.rk_sofwares.e_commerce.adapter.ItemAdapter

class FragmentHelper(

    private var activity : AppCompatActivity

) {
    fun setFragment( value : String ,fragmentID : Int, nav : View?){

        val intentValue = arrayOf("Fg_home",
            "Fg_cart", "Fg_messages", "Fg_profile")


        if (intentValue.contains(value)){

            val fragment = when(value){

                "Fg_home" -> Fg_home()
                "Fg_cart" -> Fg_cart()
                "Fg_messages" -> Fg_messages()
                "Fg_profile" -> Fg_profile()

                else -> null

            }

            if (fragment != null){

                activity.supportFragmentManager.beginTransaction().replace(fragmentID, fragment).commit()

                if (nav != null){
                    nav.visibility = View.GONE
                }


            }

        }

    }

}