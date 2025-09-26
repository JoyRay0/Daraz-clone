package com.rk_sofwares.e_commerce.Other

import android.app.Activity
import android.app.ActivityManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updatePadding
import com.rk_sofwares.e_commerce.R


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
    fun setBottomNav(bottom_navigation: View){

        ViewCompat.setOnApplyWindowInsetsListener(bottom_navigation) { v, insets ->
            v.updatePadding(bottom = insets.mandatorySystemGestureInsets.bottom)
            insets
        }

    }

    fun noBottomNav(no_nav : View){

        ViewCompat.setOnApplyWindowInsetsListener(no_nav) { v, insets ->
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
    fun statusBarColor(color : String, isLight : Boolean){

        activity.window.statusBarColor = color.toColorInt()
        WindowInsetsControllerCompat(activity.window, activity.window.decorView).isAppearanceLightStatusBars = isLight
    }



}