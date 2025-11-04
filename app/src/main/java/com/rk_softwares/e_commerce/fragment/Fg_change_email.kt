package com.rk_softwares.e_commerce.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.rk_softwares.e_commerce.Other.StorageHelper
import com.rk_softwares.e_commerce.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.http2.Http2Reader


class Fg_change_email : Fragment() {

    //XML id's--------------------------------------------------------------------

    private lateinit var ed_old : AppCompatEditText
    private lateinit var ed_new : AppCompatEditText
    private lateinit var cd_change : CardView

    //other
    private lateinit var emailStore : StorageHelper

    //XML id's--------------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_change_email, container, false)

        //identity period----------------------------------------------------------

        ed_old = view.findViewById(R.id.ed_old)
        ed_new = view.findViewById(R.id.ed_new)
        cd_change = view.findViewById(R.id.cd_change)

        //identity period----------------------------------------------------------

        emailStore = StorageHelper(requireActivity(), "change_email")

        changeEmail()

        return view
    }// on create===========================================================

    //change your email----------------------------------------------------------
    private fun changeEmail(){

        cd_change.setOnClickListener {

            val old_email = ed_old.text.toString().trim()
            val new_email = ed_new.text.toString().trim()

            if (old_email.isNullOrEmpty()){

                ed_old.error = "This filed is required"

            }else if (new_email.isNullOrEmpty()){

                ed_new.error = "This filed is required"

            }else{

                if (old_email == new_email){

                    Toast.makeText(requireActivity(), "Same email can not be accepted", Toast.LENGTH_SHORT).show()

                }else{

                    emailStore.deleteData("email")
                    emailStore.setData("email", new_email)

                    lifecycleScope.launch {

                        delay(2000)
                        ed_old.text?.clear()
                        ed_new.text?.clear()

                    }
                    
                    Toast.makeText(requireActivity(), "Email Change Successful", Toast.LENGTH_SHORT).show()

                }

            }


        }

    }

}//public class============================================================