package com.rk_sofwares.e_commerce.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.cardview.widget.CardView
import com.rk_sofwares.e_commerce.Other.StorageHelper
import com.rk_sofwares.e_commerce.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class Fg_change_password : Fragment() {

    //xml id's--------------------------------------------------------------

    //items
    private lateinit var ed_email : AppCompatEditText
    private lateinit var ed_new_password : AppCompatEditText
    private lateinit var ed_confirm_password : AppCompatEditText
    private lateinit var cd_reset : CardView

    //other
    private lateinit var changePassword : StorageHelper

    //xml id's--------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_change_password, container, false)

        //identity period--------------------------------------------------

        ed_email = view.findViewById(R.id.ed_email)
        ed_new_password = view.findViewById(R.id.ed_new_password)
        ed_confirm_password = view.findViewById(R.id.ed_confirm_password)
        cd_reset = view.findViewById(R.id.cd_reset)

        //identity period--------------------------------------------------

        changePassword = StorageHelper(requireActivity(), "change_password")

        changePassword()

        return view
    }//on create======================================================

    //chaining password---------------------------------------------------
    private fun changePassword(){

        cd_reset.setOnClickListener {

            val email = ed_email.text.toString().trim()
            val newPassword = ed_new_password.text.toString().trim()
            val confirmPassword = ed_confirm_password.text.toString().trim()

            if (email == null ||email.isEmpty()){

                ed_email.error = "Email required"

            }else if (newPassword == null || newPassword.isEmpty()){

                ed_new_password.error = "New password required"

            }else if (confirmPassword == null || confirmPassword.isEmpty()){

                ed_confirm_password.error = "Confirm password required"

            }else{

                if (newPassword == confirmPassword){

                    changePassword.deleteData("user_password")

                    changePassword.setData("user_password", confirmPassword)


                    Toast.makeText(requireActivity(), "Password changed successful", Toast.LENGTH_LONG).show()

                    CoroutineScope(Dispatchers.Main).launch{

                        ed_email.text?.clear()
                        ed_new_password.text?.clear()
                        ed_confirm_password.text?.clear()
                        delay(2000)

                    }

                }else{

                    Toast.makeText(requireActivity(), "Password not matched", Toast.LENGTH_SHORT).show()

                }

            }

        }

    }

}//public class==========================================================