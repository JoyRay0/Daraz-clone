package com.rk_sofwares.e_commerce.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.rk_sofwares.e_commerce.Other.IntentHelper
import com.rk_sofwares.e_commerce.R
import com.rk_sofwares.e_commerce.activity.Act_child_settings

class Fg_account_security : Fragment() {

    //xml id's---------------------------------------------------------------------

    private lateinit var tv_system_setting : AppCompatTextView
    private lateinit var tv_account_delection : AppCompatTextView

    //xml id's---------------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fg_profile_setting_account_security, container, false)

        //identity period------------------------------------------------------------

        tv_system_setting = view.findViewById(R.id.tv_system_setting);
        tv_account_delection = view.findViewById(R.id.tv_account_delection);

        //identity period------------------------------------------------------------

        sendIntent()

        return view
    }// on create============================================================

    private fun sendIntent(){

        val system = "systemSetting"
        val deletion = "accountDeletion"

        tv_system_setting.setOnClickListener {

            IntentHelper.setDataIntent(requireActivity(), Act_child_settings::class.java, "system", system);

        }

        tv_account_delection.setOnClickListener {

            IntentHelper.setDataIntent(requireActivity(), Act_child_settings::class.java, "deleted", deletion);

        }

    }


}//public class==========================================================