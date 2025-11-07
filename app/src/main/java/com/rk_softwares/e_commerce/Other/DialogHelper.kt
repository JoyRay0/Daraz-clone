package com.rk_softwares.e_commerce.Other

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import androidx.core.graphics.drawable.toDrawable

class DialogHelper(

    private var context: Context

) {

    fun customBottomDialog(layout : Int) : Dialog{

        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layout)

        dialog.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
        dialog.window?.setGravity(Gravity.BOTTOM)

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return dialog

    }

}