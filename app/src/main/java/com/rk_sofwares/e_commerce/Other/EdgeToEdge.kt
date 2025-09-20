package com.rk_sofwares.e_commerce.Other

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updatePadding


class EdgeToEdge(
    private var activity: Activity
) {

    //edge to edge
    fun setEdgeToEdge(){

        WindowCompat.setDecorFitsSystemWindows(activity.window, false)
        activity.window.statusBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(activity.window, activity.window.decorView).isAppearanceLightStatusBars = true
    }

    //toolbar
    fun setToolBar(toolbar : View){

        ViewCompat.setOnApplyWindowInsetsListener(toolbar) { v, insets ->
            v.setPadding(
                0,
                insets.systemWindowInsetTop, // status bar height
                0,
                0
            )
            insets
        }
    }

    //bottom navigation
    fun setBottomNav(bottom_navigation : View){

        ViewCompat.setOnApplyWindowInsetsListener(bottom_navigation) { v, insets ->
            v.updatePadding(bottom = insets.systemWindowInsetBottom)
            insets
        }

    }

    //status bar
    fun statusBarColor(color : String){

        activity.window.statusBarColor = color.toColorInt()
        WindowInsetsControllerCompat(activity.window, activity.window.decorView).isAppearanceLightStatusBars = false
    }

}