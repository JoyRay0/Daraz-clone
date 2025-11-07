package com.rk_softwares.e_commerce.Other

import android.app.Activity
import android.graphics.Color
import android.view.View
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat


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
    /*
    fun setBottomNav(bottom_navigation: View){

        ViewCompat.setOnApplyWindowInsetsListener(bottom_navigation) { v, insets ->
            v.updatePadding(bottom = insets.mandatorySystemGestureInsets.bottom)
            insets
        }

    }

     */

    //bottom navigation
    fun setBottomNav(nav : View){

        ViewCompat.setOnApplyWindowInsetsListener(nav) { v, insets ->
            // gesture / navigation safe padding
            v.setPadding(
                v.paddingLeft,
                v.paddingTop,
                v.paddingRight,
                insets.systemGestureInsets.bottom  // এখানে bottom inset safe
            )
            insets
        }

    }

    //status bar
    fun setStatusBarColor(color : String, isLight : Boolean){

        activity.window.statusBarColor = color.toColorInt()
        WindowInsetsControllerCompat(activity.window, activity.window.decorView).isAppearanceLightStatusBars = isLight
    }



}