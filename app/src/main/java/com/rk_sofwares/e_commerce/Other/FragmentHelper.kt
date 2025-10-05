package com.rk_sofwares.e_commerce.Other

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rk_sofwares.e_commerce.fragment.Fg_home_cart
import com.rk_sofwares.e_commerce.fragment.Fg_home_home
import com.rk_sofwares.e_commerce.fragment.Fg_home_messages
import com.rk_sofwares.e_commerce.fragment.Fg_home_profile

class FragmentHelper(

    private var activity : AppCompatActivity

) {
    fun setFragment( value : String ,fragmentID : Int, nav : View?){

        val intentValue = arrayOf("Fg_home",
            "Fg_cart", "Fg_messages", "Fg_profile")


        if (intentValue.contains(value)){

            val fragment = when(value){

                "Fg_home" -> Fg_home_home()
                "Fg_cart" -> Fg_home_cart()
                "Fg_messages" -> Fg_home_messages()
                "Fg_profile" -> Fg_home_profile()

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