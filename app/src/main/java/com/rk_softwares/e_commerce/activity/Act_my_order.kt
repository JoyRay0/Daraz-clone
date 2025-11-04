package com.rk_softwares.e_commerce.activity

import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import android.graphics.Typeface
import androidx.core.graphics.toColorInt
import com.rk_softwares.e_commerce.R

class Act_my_order : AppCompatActivity() {

    //XML id's-----------------------------------------------------------------

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

    //XML id's-----------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_my_order)

        //identity period------------------------------------------------------

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

        //identity period------------------------------------------------------

        tabItem()

    }//on create===============================================================

    private fun tabItem(){

        val selectedText = "#FF5722".toColorInt()
        val unselectedText = "#000000".toColorInt()
        val boldText = Typeface.DEFAULT_BOLD
        val normalText = Typeface.DEFAULT

        tv_all.setTextColor(selectedText)
        tv_all_underLine.visibility = View.VISIBLE
        tv_all.typeface = boldText

        unselectedTab(tv_pay, tv_pay_underLine, normalText, unselectedText)
        unselectedTab(tv_ship, tv_ship_underLine, normalText, unselectedText)
        unselectedTab(tv_receive, tv_receive_underLine, normalText, unselectedText)
        unselectedTab(tv_review, tv_review_underLine, normalText, unselectedText)

        selectedTabItem(selectedText, unselectedText, boldText, normalText,tv_all, tv_all_underLine, tv_pay, tv_ship, tv_receive, tv_review, tv_pay_underLine, tv_ship_underLine, tv_receive_underLine, tv_review_underLine)
        selectedTabItem(selectedText, unselectedText, boldText, normalText,tv_pay, tv_pay_underLine, tv_ship, tv_receive, tv_review, tv_all, tv_ship_underLine, tv_receive_underLine, tv_review_underLine, tv_all_underLine)
        selectedTabItem(selectedText, unselectedText, boldText, normalText,tv_ship, tv_ship_underLine, tv_receive, tv_review, tv_all, tv_pay, tv_receive_underLine, tv_review_underLine, tv_all_underLine, tv_pay_underLine)
        selectedTabItem(selectedText, unselectedText, boldText, normalText,tv_receive, tv_receive_underLine, tv_review, tv_all, tv_pay, tv_ship, tv_review_underLine, tv_all_underLine, tv_pay_underLine, tv_ship_underLine)
        selectedTabItem(selectedText, unselectedText, boldText, normalText,tv_review, tv_review_underLine, tv_all, tv_pay, tv_ship, tv_receive, tv_all_underLine, tv_pay_underLine, tv_ship_underLine, tv_receive_underLine)

    }

    private fun selectedTabItem(
        selTextColor : Int,
        unSelTextColor : Int,
        boldText : Typeface,
        normalText : Typeface,
        selected1 : AppCompatTextView,
        selectedUnderLine1 : AppCompatTextView,
        unSelected1 : AppCompatTextView,
        unSelected2: AppCompatTextView,
        unSelected3 : AppCompatTextView,
        unSelected4: AppCompatTextView,
        unSelectedUnderLine1: AppCompatTextView,
        unSelectedUnderLine2: AppCompatTextView,
        unSelectedUnderLine3: AppCompatTextView,
        unSelectedUnderLine4: AppCompatTextView
    ){

        selected1.setOnClickListener {

            selected1.setTextColor(selTextColor)
            selectedUnderLine1.visibility = View.VISIBLE
            selected1.typeface = boldText

            unSelected1.typeface = normalText
            unSelected2.typeface = normalText
            unSelected3.typeface = normalText
            unSelected4.typeface = normalText

            unSelected1.setTextColor(unSelTextColor)
            unSelected2.setTextColor(unSelTextColor)
            unSelected3.setTextColor(unSelTextColor)
            unSelected4.setTextColor(unSelTextColor)

            unSelectedUnderLine1.visibility = View.GONE
            unSelectedUnderLine2.visibility = View.GONE
            unSelectedUnderLine3.visibility = View.GONE
            unSelectedUnderLine4.visibility = View.GONE

        }

    }

    private fun unselectedTab(tv : AppCompatTextView, underLine : AppCompatTextView, normal : Typeface, color : Int){

        tv.setTextColor(color)
        underLine.visibility = View.GONE
        tv.typeface = normal

    }

}//class=======================================================================