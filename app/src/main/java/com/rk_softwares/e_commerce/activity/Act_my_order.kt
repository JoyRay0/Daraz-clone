package com.rk_softwares.e_commerce.activity

import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.v4.os.IResultReceiver
import android.widget.FrameLayout
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.radiobutton.MaterialRadioButton
import com.rk_softwares.e_commerce.Other.DialogHelper
import com.rk_softwares.e_commerce.Other.EdgeToEdge
import com.rk_softwares.e_commerce.Other.FragmentHelper
import com.rk_softwares.e_commerce.Other.IntentHelper
import com.rk_softwares.e_commerce.Other.ShortMessageHelper
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.fragment.Fg_my_order_all

class Act_my_order : AppCompatActivity() {

    //XML id's-----------------------------------------------------------------

    //toolbar
    private lateinit var iv_back : AppCompatImageView
    private lateinit var iv_search : AppCompatImageView
    private lateinit var iv_filter : AppCompatImageView
    private lateinit var fl_toolbar : FrameLayout

    //tab item
    private lateinit var tv_all : AppCompatTextView
    private lateinit var tv_pay : AppCompatTextView
    private lateinit var tv_ship : AppCompatTextView
    private lateinit var tv_receive : AppCompatTextView
    private lateinit var tv_review : AppCompatTextView

    private lateinit var tv_all_underLine : AppCompatTextView
    private lateinit var tv_pay_underLine : AppCompatTextView
    private lateinit var tv_ship_underLine : AppCompatTextView
    private lateinit var tv_receive_underLine : AppCompatTextView
    private lateinit var tv_review_underLine : AppCompatTextView

    //container
    private lateinit var fl_container : FrameLayout

    //init
    val selectedText = "#FF5722".toColorInt()
    val unselectedText = "#000000".toColorInt()
    val boldText = Typeface.DEFAULT_BOLD
    val normalText = Typeface.DEFAULT

    //radio item
    private var lastMonth = ""

    //other
    private lateinit var edge_to_edge : EdgeToEdge
    private lateinit var bottomDialog : DialogHelper

    //XML id's-----------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_my_order)

        //identity period------------------------------------------------------

        //toolbar
        iv_back = findViewById(R.id.iv_back)
        iv_search = findViewById(R.id.iv_search)
        iv_filter = findViewById(R.id.iv_filter)
        fl_toolbar = findViewById(R.id.fl_toolbar)

        //tab item
        tv_all = findViewById(R.id.tv_all)
        tv_pay = findViewById(R.id.tv_pay)
        tv_ship = findViewById(R.id.tv_ship)
        tv_receive = findViewById(R.id.tv_receive)
        tv_review = findViewById(R.id.tv_review)

        tv_all_underLine = findViewById(R.id.tv_all_underLine)
        tv_pay_underLine = findViewById(R.id.tv_pay_underLine)
        tv_ship_underLine = findViewById(R.id.tv_ship_underLine)
        tv_receive_underLine = findViewById(R.id.tv_receive_underLine)
        tv_review_underLine = findViewById(R.id.tv_review_underLine)

        //container
        fl_container = findViewById(R.id.fl_container)

        //identity period------------------------------------------------------

        edge_to_edge = EdgeToEdge(this)
        edge_to_edge.setEdgeToEdge()
        edge_to_edge.setStatusBarColor("#FFFFFF", true)
        edge_to_edge.setToolBar(fl_toolbar)
        edge_to_edge.setBottomNav(fl_container)

        bottomDialog = DialogHelper(this)

        iv_back.setOnClickListener {

            //IntentHelper.intent(this, Act_home::class.java)
            IntentHelper.setDataIntent(this, Act_home::class.java, "profile", "Fg_profile")

        }

        onBackPressedDispatcher.addCallback(this, true){

            IntentHelper.intent(this@Act_my_order, Act_home::class.java)
        }

        getIntentData()

        iv_filter.setOnClickListener {

            filterDialog()

        }


    }//on create===============================================================

    private fun getIntentData(){

        val item = intent.getStringExtra("tab") ?: "all"

        when(item){

            "Fg_pay" -> selectedTabs(tv_pay, tv_pay_underLine, Fg_my_order_all(), tv_ship, tv_receive, tv_review, tv_all, tv_ship_underLine, tv_receive_underLine, tv_review_underLine, tv_all_underLine)
            "Fg_ship" -> selectedTabs(tv_ship, tv_ship_underLine, Fg_my_order_all(), tv_receive, tv_review, tv_all, tv_pay, tv_receive_underLine, tv_review_underLine, tv_all_underLine, tv_pay_underLine)
            "Fg_receive" -> selectedTabs(tv_receive, tv_receive_underLine, Fg_my_order_all(), tv_review, tv_all, tv_pay, tv_ship, tv_review_underLine, tv_all_underLine, tv_pay_underLine, tv_ship_underLine)
            "Fg_review" -> selectedTabs(tv_review, tv_review_underLine, Fg_my_order_all(), tv_all, tv_pay, tv_ship, tv_receive, tv_all_underLine, tv_pay_underLine, tv_ship_underLine, tv_receive_underLine)
            else -> selectedTabs(tv_all, tv_all_underLine, Fg_my_order_all(), tv_pay, tv_ship, tv_receive, tv_review, tv_pay_underLine, tv_ship_underLine, tv_receive_underLine, tv_review_underLine)
        }

        tabClicked(tv_all, tv_all_underLine, tv_pay, tv_ship, tv_receive, tv_review, tv_pay_underLine, tv_ship_underLine, tv_receive_underLine, tv_review_underLine)
        tabClicked(tv_pay, tv_pay_underLine, tv_ship, tv_receive, tv_review, tv_all, tv_ship_underLine, tv_receive_underLine, tv_review_underLine, tv_all_underLine)
        tabClicked(tv_ship, tv_ship_underLine, tv_receive, tv_review, tv_all, tv_pay, tv_receive_underLine, tv_review_underLine, tv_all_underLine, tv_pay_underLine)
        tabClicked(tv_receive, tv_receive_underLine, tv_review, tv_all, tv_pay, tv_ship, tv_review_underLine, tv_all_underLine, tv_pay_underLine, tv_ship_underLine)
        tabClicked(tv_review, tv_review_underLine, tv_all, tv_pay, tv_ship, tv_receive, tv_all_underLine, tv_pay_underLine, tv_ship_underLine, tv_receive_underLine)

    }

    private fun selectedTabs(
        tv : AppCompatTextView,
        tvu : AppCompatTextView,
        fragment : Fragment,
        unSelectedText1 : AppCompatTextView,
        unSelectedText2 : AppCompatTextView,
        unSelectedText3 : AppCompatTextView,
        unSelectedText4 : AppCompatTextView,
        unSelectedUnderline1 : AppCompatTextView,
        unSelectedUnderline2 : AppCompatTextView,
        unSelectedUnderline3 : AppCompatTextView,
        unSelectedUnderline4 : AppCompatTextView
    ){

        tv.setTextColor(selectedText)
        tv.typeface = boldText
        tvu.visibility = View.VISIBLE

        supportFragmentManager.beginTransaction().replace(R.id.fl_container, fragment).commit()

        unSelectedTabs(unSelectedText1, unSelectedUnderline1)
        unSelectedTabs(unSelectedText2, unSelectedUnderline2)
        unSelectedTabs(unSelectedText3, unSelectedUnderline3)
        unSelectedTabs(unSelectedText4, unSelectedUnderline4)

    }

    private fun unSelectedTabs(tv : AppCompatTextView, tvu : AppCompatTextView){

        tv.setTextColor(unselectedText)
        tv.typeface = normalText
        tvu.visibility = View.GONE

    }

    private fun tabClicked(
        sel : AppCompatTextView,
        selU: AppCompatTextView,
        unSelectedText1 : AppCompatTextView,
        unSelectedText2 : AppCompatTextView,
        unSelectedText3 : AppCompatTextView,
        unSelectedText4 : AppCompatTextView,
        unSelectedUnderline1 : AppCompatTextView,
        unSelectedUnderline2 : AppCompatTextView,
        unSelectedUnderline3 : AppCompatTextView,
        unSelectedUnderline4 : AppCompatTextView
        ){

        sel.setOnClickListener {

            selectedTabs(sel, selU, Fg_my_order_all(), unSelectedText1, unSelectedText2, unSelectedText3, unSelectedText4, unSelectedUnderline1, unSelectedUnderline2, unSelectedUnderline3, unSelectedUnderline4)

        }

    }

    // filter-------------------------------------------------------------------------
    private fun filterDialog(){

        val dialog = bottomDialog.customBottomDialog(R.layout.lay_filter_dialog)

        val iv_close = dialog.findViewById<AppCompatImageView>(R.id.iv_close)
        val rb_last1month = dialog.findViewById<MaterialRadioButton>(R.id.rb_last1month)
        val rb_last3month = dialog.findViewById<MaterialRadioButton>(R.id.rb_last3month)
        val rb_last6month = dialog.findViewById<MaterialRadioButton>(R.id.rb_last6month)
        val rb_start_date = dialog.findViewById<MaterialRadioButton>(R.id.rb_start_date)
        val rb_end_date = dialog.findViewById<MaterialRadioButton>(R.id.rb_end_date)
        val btn_reset = dialog.findViewById<MaterialButton>(R.id.btn_reset)
        val btn_confirm = dialog.findViewById<MaterialButton>(R.id.btn_confirm)

        val selectedBackground = ContextCompat.getDrawable(this,R.drawable.sh_last_month_checked)
        val unSelectedBackground = getDrawable(R.drawable.sh_last_month_unchecked)

        btn_confirm.isEnabled = false
        btn_confirm.alpha = 0.5f

        radioChecked(rb_last1month, rb_last3month, rb_last6month, "last 1 month", selectedBackground, unSelectedBackground, btn_confirm)
        radioChecked(rb_last3month, rb_last1month, rb_last6month, "last 3 month", selectedBackground, unSelectedBackground, btn_confirm)
        radioChecked(rb_last6month, rb_last3month, rb_last1month, "last 6 month", selectedBackground, unSelectedBackground, btn_confirm)


        btn_reset.setOnClickListener {

            lastMonth = ""
            radioUnChecked(rb_last1month, rb_last3month, rb_last6month, rb_start_date, rb_end_date, unSelectedBackground)

            btn_confirm.isEnabled = false
            btn_confirm.alpha = 0.5f

        }

        btn_confirm.setOnClickListener {

            if (lastMonth.isEmpty()){

                ShortMessageHelper.toast(this, "Empty")

            }else{

                ShortMessageHelper.toast(this, lastMonth)
                lastMonth = ""
                dialog.dismiss()

            }

        }

        iv_close.setOnClickListener {

            dialog.dismiss()

        }

        dialog.show()

    }

    private fun radioChecked(rb : MaterialRadioButton, rb2 : MaterialRadioButton, rb3 : MaterialRadioButton, month : String, selected : Drawable?, unSelected : Drawable?, btn : MaterialButton){

        val unChecked = arrayOf(rb2, rb3)

        rb.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked){
                rb.background = selected

                unChecked.forEach {

                    it.isChecked = false
                    it.background = unSelected

                }

                lastMonth = month
                btn.isEnabled = true
                btn.alpha = 1f
            }

        }

    }

    private fun radioUnChecked(rb1 : MaterialRadioButton, rb2 : MaterialRadioButton, rb3 : MaterialRadioButton, rb4 : MaterialRadioButton, rb5 : MaterialRadioButton, unSelected : Drawable?){

        val unchecked = arrayOf(rb1, rb2, rb3, rb4, rb5)

        unchecked.forEach {

            it.isChecked = false
            it.background = unSelected

        }

    }


}//class=======================================================================