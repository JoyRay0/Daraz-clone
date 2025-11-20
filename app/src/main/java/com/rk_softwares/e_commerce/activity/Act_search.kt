package com.rk_softwares.e_commerce.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.rk_softwares.e_commerce.Other.EdgeToEdge
import com.rk_softwares.e_commerce.Other.InputSanitizerHelper
import com.rk_softwares.e_commerce.Other.IntentHelper
import com.rk_softwares.e_commerce.Other.ShortMessageHelper
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.adapter.HistoryAdapter
import com.rk_softwares.e_commerce.Other.ItemClick
import com.rk_softwares.e_commerce.adapter.Product
import com.rk_softwares.e_commerce.database.SearchHistory
import com.rk_softwares.e_commerce.server.SearchServer
import com.rk_softwares.e_commerce.server.SuggestionServer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Act_search : AppCompatActivity() {

    //XML id's---------------------------------------------------------------

    //search bar
    private lateinit var iv_back : AppCompatImageView
    private lateinit var act_search : AppCompatAutoCompleteTextView
    private lateinit var btn_search : AppCompatButton
    private lateinit var fl_toolbar : FrameLayout

    //search history
    private lateinit var tv_clear_history : AppCompatTextView
    private lateinit var rv_history : RecyclerView
    private lateinit var fl_history : FrameLayout

    //recommend
    private lateinit var tv_hide_recommend : AppCompatTextView
    private lateinit var rv_recommend : RecyclerView

    private lateinit var rv_product : RecyclerView

    //init
    var searchText = ""
    private var list : ArrayList<HashMap<String, String>> = ArrayList()
    private var productList : ArrayList<HashMap<String, Any>> = ArrayList()
    private var searchJob : Job? = null

    //other
    private lateinit var edge_to_edge : EdgeToEdge
    private lateinit var history: SearchHistory
    private lateinit var historyAdapter : HistoryAdapter
    private lateinit var suggestion : SuggestionServer
    private lateinit var searchServer : SearchServer
    private lateinit var productAdapter : Product


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
        fl_history = findViewById(R.id.fl_history)

        tv_hide_recommend = findViewById(R.id.tv_hide_recommend)
        rv_recommend = findViewById(R.id.rv_recommend)

        rv_product = findViewById(R.id.rv_product)

        //identity period-------------------------------------------------------

        edge_to_edge = EdgeToEdge(this)
        edge_to_edge.setEdgeToEdge()
        edge_to_edge.setStatusBarColor("#FFFFFF", true)
        edge_to_edge.setToolBar(fl_toolbar)
        edge_to_edge.setBottomNav(rv_product)

        history = SearchHistory(this)

        suggestion = SuggestionServer(this, act_search)

        productAdapter = Product(this, productList)
        rv_product.adapter = productAdapter

        searchServer = SearchServer(this)


        historyAdapter = HistoryAdapter(this, list, object : ItemClick.onItemClickedListener{

            override fun onItemClick(text: String?) {

                act_search.setText(text)

                searchData(text.toString())

                performSearch(text)

            }

        })
        rv_history.adapter = historyAdapter


        act_search.requestFocus()


        iv_back.setOnClickListener {

            IntentHelper.intent(this, Act_home::class.java)

        }

        search()
        history()

        tv_clear_history.setOnClickListener {

            lifecycleScope.launch(Dispatchers.IO){

                history.deleteAll()

                withContext(Dispatchers.Main){

                    history()

                }

            }

        }



        val openEyeDrawable = ContextCompat.getDrawable(this, R.drawable.ic_open_eye)
        val closeEyeDrawable = ContextCompat.getDrawable(this, R.drawable.ic_close_eye)
        tv_hide_recommend.text = "Hide"
        tv_hide_recommend.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, closeEyeDrawable, null)
        tv_hide_recommend.tag = "close"

        tv_hide_recommend.setOnClickListener {

            if (tv_hide_recommend.tag == "open"){

                tv_hide_recommend.text = "Hide"
                tv_hide_recommend.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, closeEyeDrawable, null)
                rv_recommend.visibility = View.GONE
                tv_hide_recommend.tag = "close"

            }else{

                tv_hide_recommend.text = "Show"
                tv_hide_recommend.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, openEyeDrawable, null)
                rv_recommend.visibility = View.VISIBLE
                tv_hide_recommend.tag = "open"

            }

        }


    }// on create===============================================================

    //search history---------------------------------------------------------------------------
    private fun search(){

        btn_search.isEnabled = false
        btn_search.alpha = 0.5f

        btn_search.setOnClickListener {

            searchData(null)
            performSearch(searchText)

        }

        act_search.setOnEditorActionListener { _, actionID, _ ->

            if (actionID == EditorInfo.IME_ACTION_SEARCH){

                searchData(null)

                performSearch(searchText)

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

                   searchJob?.cancel()
                   searchJob = lifecycleScope.launch {

                       delay(1000)
                       suggestion.searchSuggestionFromServer(text)

                   }

               }

            }

        })

    }

    private fun searchData(text : String?) {

        val keyboard = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        val search = InputSanitizerHelper.isValidString(act_search.text.toString())
        val historySearch = InputSanitizerHelper.isValidString(text ?: "")

        if (!search.isEmpty()){

            keyboard.hideSoftInputFromWindow(act_search.windowToken, 0)

            searchText = search

        }else if (!historySearch.isEmpty()){

            keyboard.hideSoftInputFromWindow(act_search.windowToken, 0)
            //ShortMessageHelper.toast(this, historySearch)

            searchText = historySearch

        }else{

            searchText = ""
        }

    }

    private fun history(){

        lifecycleScope.launch(Dispatchers.IO){

            val dataList = history.getAll()

            withContext( Dispatchers.Main){

                list.clear()
                list.addAll(dataList)
                historyAdapter.notifyDataSetChanged()
            }

        }

    }

    private fun performSearch(text: String?){

        if (!text.isNullOrEmpty()) {

            ShortMessageHelper.toast(applicationContext, text.trim())
            fl_history.visibility = View.GONE

            if (searchText == "null" || searchText.isEmpty()){

                return

            }else{

                searchServer.searchProduct(text, rv_product, productList, productAdapter)

                lifecycleScope.launch(Dispatchers.IO){

                    if (!(history.checkDuplicateData(text))){

                        history.insert(text)

                    }

                    withContext(Dispatchers.Main){

                        productList.clear()
                        history()

                    }

                }

            }

        }else ShortMessageHelper.toast(applicationContext, "Invalid Keyword")

    }

    override fun onDestroy() {
        super.onDestroy()
        history.closeDB()
    }

}// class=========================================================================