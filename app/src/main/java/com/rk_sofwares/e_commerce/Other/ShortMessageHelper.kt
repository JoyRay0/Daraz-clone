package com.rk_sofwares.e_commerce.Other

import android.content.Context
import android.widget.Toast

object ShortMessageHelper {

    fun toast(context: Context, toastText : String){

        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()

    }

}