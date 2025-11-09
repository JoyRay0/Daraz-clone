package com.rk_softwares.e_commerce.activity

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.compose.runtime.mutableStateOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.rk_softwares.e_commerce.Other.EdgeToEdge
import com.rk_softwares.e_commerce.Other.InputSanitizerHelper
import com.rk_softwares.e_commerce.Other.IntentHelper
import com.rk_softwares.e_commerce.Other.ShortMessageHelper
import com.rk_softwares.e_commerce.R

class Act_search : AppCompatActivity() {

    //XML id's---------------------------------------------------------------

    //search bar
    private lateinit var iv_back : AppCompatImageView
    private lateinit var act_search : AppCompatAutoCompleteTextView
    private lateinit var btn_search : AppCompatButton
    private lateinit var fl_toolbar : FrameLayout


    private lateinit var tv_clear_history : AppCompatTextView
    private lateinit var rv_history : RecyclerView
    private lateinit var rv_product : RecyclerView

    //init

    //other
    private lateinit var edge_to_edge : EdgeToEdge

    //XML id's---------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_search)

        //identity period-------------------------------------------------------
        iv_back = findViewById(R.id.iv_back)
        act_search = findViewById(R.id.act_search)
        btn_search = findViewById(R.id.btn_search)
        fl_toolbar = findViewById(R.id.fl_toolbar)

        tv_clear_history = findViewById(R.id.tv_clear_history)
        rv_history = findViewById(R.id.rv_history)
        rv_product = findViewById(R.id.rv_product)

        //identity period-------------------------------------------------------

        edge_to_edge = EdgeToEdge(this)
        edge_to_edge.setEdgeToEdge()
        edge_to_edge.setStatusBarColor("#FFFFFF", true)
        edge_to_edge.setToolBar(fl_toolbar)
        edge_to_edge.setBottomNav(rv_product)

        iv_back.setOnClickListener {

            IntentHelper.intent(this, Act_home::class.java)

        }

        search()

    }// on create===============================================================

    private fun search(){

        btn_search.isEnabled = false
        btn_search.alpha = 0.5f

        btn_search.setOnClickListener {

            if (searchData().isNotEmpty()) {

                ShortMessageHelper.toast(this, searchData().trim())

            }else ShortMessageHelper.toast(this, "Invalid Keyword")

        }

        act_search.setOnEditorActionListener { _, actionID, _ ->

            if (actionID == EditorInfo.IME_ACTION_SEARCH){

                if (searchData().isNotEmpty()) {

                    ShortMessageHelper.toast(this, searchData().trim())

                }else ShortMessageHelper.toast(this, "Invalid Keyword")

                return@setOnEditorActionListener true
            }

            false
        }

        act_search.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {

               if (text.isNullOrEmpty()){

                   btn_search.isEnabled = false
                   btn_search.alpha = 0.5f

               }else{

                   btn_search.isEnabled = true
                   btn_search.alpha = 1f

               }

            }


        })

    }

    private fun searchData() : String{

        val keyboard = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        val search = InputSanitizerHelper.isValidString(act_search.text.toString())

        if (search.isNotEmpty()){

            keyboard.hideSoftInputFromWindow(act_search.windowToken, 0)

            return search
        }else return ""

    }

}// class=========================================================================