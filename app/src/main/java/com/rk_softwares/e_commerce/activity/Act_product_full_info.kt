package com.rk_softwares.e_commerce.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.card.MaterialCardView
import com.rk_softwares.e_commerce.ComposeUi.Question
import com.rk_softwares.e_commerce.ComposeUi.Store
import com.rk_softwares.e_commerce.ComposeUi.rating_reviews
import com.rk_softwares.e_commerce.Other.DialogHelper
import com.rk_softwares.e_commerce.Other.EdgeToEdge
import com.rk_softwares.e_commerce.Other.IntentHelper
import com.rk_softwares.e_commerce.Other.KeyHelper
import com.rk_softwares.e_commerce.Other.ShortMessageHelper
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.adapter.ProductImageAdapter
import com.rk_softwares.e_commerce.ComposeUi.vouchers
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class Act_product_full_info : AppCompatActivity() {

    //XML id's----------------------------------------------------------------

    //toolbar
    private lateinit var iv_back : AppCompatImageView
    private lateinit var cd_search : MaterialCardView
    private lateinit var iv_share : AppCompatImageView
    private lateinit var iv_cart : AppCompatImageView
    private lateinit var iv_menu : AppCompatImageView

    private lateinit var fl_toolbar : FrameLayout


    //bottom bar
    private lateinit var fl_bottom : FrameLayout


    //image

    private lateinit var fl_image : FrameLayout
    private lateinit var vp_product_image : ViewPager2
    private lateinit var dotsIndicator : DotsIndicator

    private var mainImageList : ArrayList<HashMap<String, String>> = ArrayList()
    private lateinit var h : HashMap<String, String>

    private lateinit var cv_voucher : ComposeView


    //other
    private lateinit var productImageAdapter: ProductImageAdapter
    private lateinit var dialogHelper : DialogHelper
    private lateinit var edge_to_edge : EdgeToEdge

    //init
    private var fgName = ""
    private var classData : Class<*>? = null
    private var list : ArrayList<String> = ArrayList()
    private var rList : ArrayList<HashMap<String, Any>> = ArrayList()


    //XML id's----------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_product_full_info)

        init()

        test()

        buttons()


    }// on create=============================================================

    private fun init(){

        //toolbar
        iv_back = findViewById(R.id.iv_back)
        cd_search = findViewById(R.id.cd_search)
        iv_share = findViewById(R.id.iv_share)
        iv_cart = findViewById(R.id.iv_cart)
        iv_menu = findViewById(R.id.iv_menu)
        fl_toolbar = findViewById(R.id.fl_toolbar)

        //bottom
        fl_bottom = findViewById(R.id.fl_bottom)

        //vp image
        vp_product_image = findViewById(R.id.vp_product_image)
        dotsIndicator = findViewById(R.id.dotsIndicator)

        cv_voucher = findViewById(R.id.cv_voucher)


        productImageAdapter = ProductImageAdapter(this, mainImageList)
        vp_product_image.adapter = productImageAdapter
        dotsIndicator.attachTo(vp_product_image)
        vp_product_image.currentItem = 1

        dialogHelper = DialogHelper(this)

        edge_to_edge = EdgeToEdge(this)
        edge_to_edge.setEdgeToEdge()
        edge_to_edge.setBottomNav(fl_bottom)
        edge_to_edge.setToolBar(fl_toolbar)
        edge_to_edge.setStatusBarColor("#FFFFFF", true)

    }

    private fun buttons(){

        val back = intent.getStringExtra(KeyHelper.getFullInfoBack())

        onBackPressedDispatcher.addCallback(this, true){

            when{

                 back == "Fg_home" -> IntentHelper.setDataIntent(this@Act_product_full_info,
                    Act_home::class.java, KeyHelper.getHomeInfo(), "Fg_home")

            }

        }

        iv_back.setOnClickListener {

            when{

                 back == "Fg_home" -> IntentHelper.setDataIntent(this,
                    Act_home::class.java, KeyHelper.getHomeInfo(), "Fg_home")

            }

        }

        iv_cart.setOnClickListener {

            IntentHelper.setDataIntent(this,
                Act_home::class.java, KeyHelper.getHomeInfo(), "Fg_cart")

        }

        cd_search.setOnClickListener {

            IntentHelper.intent(this, Act_search::class.java)

        }

        iv_menu.setOnClickListener {

            popUpMenu()

        }

        iv_share.setOnClickListener {

            val packageName = getPackageName()

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, packageName)
            startActivity(Intent.createChooser(intent, "Share via"))

        }

        val image = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%2Fid%2FOIP.aeAckh3-h_jVt3vY4Mz8SAHaFE%3Fpid%3DApi&f=1&ipt=7fad7d0cfe6617d28df020b42f839d0ce480885c0c8488aea155613bbe1f136e&ipo=images"

        list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%2Fid%2FOIP.iF1vjDVZtP53C_uUNZjz3AHaFW%3Fpid%3DApi&f=1&ipt=9284869913a54b27ea65f0c382ae35ece41c7ff800d27eb368b8c32d6ab75f34&ipo=images")
        list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%2Fid%2FOIP.aeAckh3-h_jVt3vY4Mz8SAHaFE%3Fpid%3DApi&f=1&ipt=7fad7d0cfe6617d28df020b42f839d0ce480885c0c8488aea155613bbe1f136e&ipo=images")

        val test = "https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/thumbnail.webp"

        rList.add(hashMapOf("name" to "Joy Ray", "comment" to "Very Very nice product", "rating" to 5, "image" to test))
        rList.add(hashMapOf("name" to "Rada krishna", "comment" to "Rade Rade", "rating" to 2, "image" to image))
        rList.add(hashMapOf("name" to "Krishna", "comment" to "Hare Krishna", "rating" to 3, "image" to image))

        cv_voucher.setContent {

            MaterialTheme {

                Column {

                    vouchers(list =  list ,onClick = {

                        ShortMessageHelper.toast(this@Act_product_full_info, "Compose Worked")

                    })


                    rating_reviews(totalReviewCount = "3".toInt(), totalStar = 2.7, list = rList,onClick = {

                        ShortMessageHelper.toast(this@Act_product_full_info, "Box Worked")

                    })

                    Question(questionCount = 2, viewAllClick = {

                        ShortMessageHelper.toast(this@Act_product_full_info, "All Questions")

                    }, askQues = {

                        ShortMessageHelper.toast(this@Act_product_full_info, "Ask Your Question")

                    })

                    val store = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.A_ZnEOdcx2Qm-YsGKIpGAQHaE8%3Fpid%3DApi&f=1&ipt=239285e12e1010f79951f9ee93f1bfa9b56881c5f3012ddaf5246b56b04b5150&ipo=images"

                    Store(storeName = "Rada Krishna", image = store, btnClick = {

                        ShortMessageHelper.toast(this@Act_product_full_info, "welcome to my store")

                    })

                }

            }



        }

    }

    private fun test(){

        h = HashMap()
        h["image"] = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.U_VJuupQohwnzXcKMztqWgHaEo%3Fpid%3DApi&f=1&ipt=28e8e921527e4d72d39e4d0ef69172340304663963a39ca383fa055f29a48059&ipo=images"
        mainImageList.add(h)

        h = HashMap()
        h["image"] = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.OpswJwVFceXjSTwTY34dBAHaFj%3Fpid%3DApi&f=1&ipt=b3d11c682f0ff5dcbaf53755b9eb4d82d85d1e97bbcb0bd4189333d053865559&ipo=images"
        mainImageList.add(h)

        h = HashMap()
        h["image"] = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.Tfy0fg9WaDKlz_IRcdEUxwHaE0%3Fpid%3DApi&f=1&ipt=b515183e684490e047b5ade94b3f108bba64585e8b26629a4020fc093b3d40c0&ipo=images"
        mainImageList.add(h)

        h = HashMap()
        h["image"] = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.uEmted3yi3LdtAOfqLGTHAHaEo%3Fpid%3DApi&f=1&ipt=a6d65080ec207816b5ef9128f031c2b633b9b32972ca3b5b5149b34bd950ab54&ipo=images"
        mainImageList.add(h)

        //
        h = HashMap()
        h["image"] = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.eKE8nrMRCK3bdvd62kWJ_wHaEK%3Fpid%3DApi&f=1&ipt=f1ecafdea285d360be37bff067cbe48f43ed940237365e0d629e2715d1e3d1da&ipo=images"
        mainImageList.add(h)

        h = HashMap()
        h["image"] = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.CdT_NgacELXhCgGLM68WFAHaE8%3Fpid%3DApi&f=1&ipt=3d012a625fd3b1a009e8d01b8e8d501a37e451c3e66663cd07c7e0c717bd1e64&ipo=images"
        mainImageList.add(h)

        h = HashMap()
        h["image"] = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.HgQBYVVQqcV0FqTylOsCQQHaEK%3Fpid%3DApi&f=1&ipt=c77468eaef4339c4f452b9ace48fc57cfad1143ffdfc15adbb52adeff4a79873&ipo=images"
        mainImageList.add(h)

        h = HashMap()
        h["image"] = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.NhL7lJAykJh7QrkatWrSvAHaE7%3Fpid%3DApi&f=1&ipt=b38da1bb76f000786d65e244ebd5c1c36629c26bfcaa915ebaa1547032ae6965&ipo=images"
        mainImageList.add(h)

    }

    private fun popUpMenu(){

        val view = LayoutInflater.from(this).inflate(R.layout.lay_dialog_full_info, null)

        val popUpWindow = PopupWindow(
            view,
            0,
            0,
            true
        )
        popUpWindow.width = 320
        popUpWindow.height = LinearLayout.LayoutParams.WRAP_CONTENT

        popUpWindow.elevation = 20f

        //val marginX = (70 * resources.displayMetrics.density).toInt()
        //val marginY = (10 * resources.displayMetrics.density).toInt()


        popUpWindow.showAsDropDown(iv_menu,  70, 20)

        val tv_home = view.findViewById<AppCompatTextView>(R.id.tv_home)
        val tv_messages = view.findViewById<AppCompatTextView>(R.id.tv_messages)
        val tv_account = view.findViewById<AppCompatTextView>(R.id.tv_account)
        val tv_help = view.findViewById<AppCompatTextView>(R.id.tv_help)

        tv_home.setOnClickListener {

            IntentHelper.intent(this, Act_home::class.java)
            popUpWindow.dismiss()

        }

        tv_messages.setOnClickListener {

            IntentHelper.setDataIntent(this, Act_home::class.java, KeyHelper.getHomeInfo(),"Fg_message")
            popUpWindow.dismiss()

        }

        tv_account.setOnClickListener {

            IntentHelper.setDataIntent(this, Act_home::class.java, KeyHelper.getHomeInfo(),"Fg_account")
            popUpWindow.dismiss()

        }

        tv_help.setOnClickListener {

            ShortMessageHelper.toast(this, "working")
            popUpWindow.dismiss()
        }



    }

}//class======================================================================

