package com.rk_softwares.e_commerce.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.rk_softwares.e_commerce.ComposeUi.BottomSheetDialog
import com.rk_softwares.e_commerce.ComposeUi.LoadingPage
import com.rk_softwares.e_commerce.ComposeUi.MoreItem
import com.rk_softwares.e_commerce.ComposeUi.ProductDetails
import com.rk_softwares.e_commerce.ComposeUi.ProductPrice
import com.rk_softwares.e_commerce.ComposeUi.Question
import com.rk_softwares.e_commerce.ComposeUi.Store
import com.rk_softwares.e_commerce.ComposeUi.RatingReviews
import com.rk_softwares.e_commerce.Other.DialogHelper
import com.rk_softwares.e_commerce.Other.EdgeToEdge
import com.rk_softwares.e_commerce.Other.IntentHelper
import com.rk_softwares.e_commerce.Other.KeyHelper
import com.rk_softwares.e_commerce.Other.ShortMessageHelper
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.adapter.ProductImageAdapter
import com.rk_softwares.e_commerce.ComposeUi.vouchers
import com.rk_softwares.e_commerce.adapter.Product
import com.rk_softwares.e_commerce.database.Wishlist
import com.rk_softwares.e_commerce.model.DataDimension
import com.rk_softwares.e_commerce.model.DataReviews
import com.rk_softwares.e_commerce.server.CartServer
import com.rk_softwares.e_commerce.server.FullProductInfoServer
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Act_product_full_info : AppCompatActivity() {

    //XML id's----------------------------------------------------------------

    //toolbar
    private lateinit var iv_back : AppCompatImageView
    private lateinit var cd_search : MaterialCardView
    private lateinit var iv_share : AppCompatImageView
    private lateinit var iv_cart : AppCompatImageView
    private lateinit var iv_menu : AppCompatImageView

    private lateinit var fl_toolbar : FrameLayout


    private lateinit var cv_loading : ComposeView

    //bottom bar
    private lateinit var fl_bottom : FrameLayout

    private lateinit var ll_store : LinearLayout
    private lateinit var ll_chat : LinearLayout
    private lateinit var btn_buy_now : MaterialButton
    private lateinit var btn_add_cart : MaterialButton



    //body

    private lateinit var fl_image : FrameLayout
    private lateinit var vp_product_image : ViewPager2
    private lateinit var dotsIndicator : DotsIndicator

    private var mainImageList : ArrayList<HashMap<String, String>> = ArrayList()
    private lateinit var h : HashMap<String, String>

    private lateinit var cv_voucher : ComposeView

    private lateinit var rv_other_product : RecyclerView

    //other
    private lateinit var productImageAdapter: ProductImageAdapter
    private lateinit var dialogHelper : DialogHelper
    private lateinit var edge_to_edge : EdgeToEdge
    private lateinit var cartServer: CartServer
    private lateinit var productAdapter : Product

    private lateinit var fullProductInfoServer : FullProductInfoServer

    private lateinit var wishlist_stg : Wishlist

    //init
    private var fgName = ""
    private var classData : Class<*>? = null

    private var ChatImageUrl : String = ""
    private var ChatTitle : String = ""
    private var ChatPrice : String = ""


    private var list : ArrayList<String> = ArrayList()
    private var rList : ArrayList<HashMap<String, Any>> = ArrayList()
    private var plist : MutableList<com.rk_softwares.e_commerce.model.Product> = arrayListOf()
    private var otherProductList : ArrayList<HashMap<String, Any>> = ArrayList()


    //XML id's----------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_product_full_info)

        init()

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

        cv_loading = findViewById(R.id.cv_loading)

        //bottom
        fl_bottom = findViewById(R.id.fl_bottom)
        ll_store = findViewById(R.id.ll_store)
        ll_chat = findViewById(R.id.ll_chat)
        btn_buy_now = findViewById(R.id.btn_buy_now)
        btn_add_cart = findViewById(R.id.btn_add_cart)


        //vp image
        vp_product_image = findViewById(R.id.vp_product_image)
        dotsIndicator = findViewById(R.id.dotsIndicator)
        fl_image = findViewById(R.id.fl_image)

        cv_voucher = findViewById(R.id.cv_voucher)

        rv_other_product = findViewById(R.id.rv_other_product)


        dialogHelper = DialogHelper(this)

        edge_to_edge = EdgeToEdge(this)
        edge_to_edge.setEdgeToEdge()
        edge_to_edge.setBottomNav(fl_bottom)
        edge_to_edge.setToolBar(fl_toolbar)
        edge_to_edge.setStatusBarColor("#FFFFFF", true)

        cartServer = CartServer(this)
        //cartServer.suggestedItem(rv = null, list = plist, adapter = null, page = 3)

        productAdapter = Product(this, otherProductList)
        rv_other_product.adapter = productAdapter

        wishlist_stg = Wishlist(this)


        val title = intent.getStringExtra("titles").toString()
        val id = intent.getStringExtra("ids").toString()
        val sku = intent.getStringExtra("skus").toString()

        cv_loading.visibility = View.VISIBLE
        cv_voucher.visibility = View.GONE
        fl_image.visibility = View.GONE

        cv_loading.setContent {

            LoadingPage()

        }

        fullProductInfoServer = FullProductInfoServer(this)

        lifecycleScope.launch{

            fullProductInfoServer.fullItem( title, id, sku, pageLoaded = { loaded ->

                if (loaded) {

                    cv_voucher.visibility = View.VISIBLE
                    fl_image.visibility = View.VISIBLE

                    cv_loading.visibility = View.GONE

                }

            } ,onResult = {it ->
                compose(
                    it.id,
                    it.sku,
                    it.brand,
                    it.category,
                    it.description,
                    it.reviews,
                    it.thumbnail,
                    it.price,
                    it.discountPercentage,
                    it.title,
                    it.rating,
                    it.reviews.size,
                    it.availabilityStatus,
                    it.stock,
                    it.shippingInformation,
                    it.returnPolicy,
                    it.warrantyInformation,
                    it.weight,
                    it.dimensions,
                    it.thumbnail
                )

                ll_chat.isEnabled = true
                ll_chat.alpha = 1f

                ll_store.isEnabled = true
                ll_store.alpha = 1f

                btn_buy_now.isEnabled = true
                btn_buy_now.alpha = 1f

                btn_add_cart.isEnabled = true
                btn_add_cart.alpha = 1f

                ChatImageUrl = it.thumbnail
                ChatTitle = it.title
                ChatPrice = it.price.toString()


                productImageAdapter = ProductImageAdapter(this@Act_product_full_info, it.images, it.sku)
                vp_product_image.adapter = productImageAdapter
                dotsIndicator.attachTo(vp_product_image)
                vp_product_image.currentItem = 1

                cartServer.suggestedItem(rv_other_product, otherProductList, productAdapter, 5)

            })

        }//background task

        ll_chat.isEnabled = false
        ll_chat.alpha = 0.5f

        ll_store.isEnabled = false
        ll_store.alpha = 0.5f

        btn_buy_now.isEnabled = false
        btn_buy_now.alpha = 0.5f

        btn_add_cart.isEnabled = false
        btn_add_cart.alpha = 0.5f


    }

    private fun buttons(){

        iv_back.setOnClickListener { finish() }

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

        ll_chat.setOnClickListener {

            val intent = Intent(this, Act_chat::class.java)
            intent.putExtra("image", ChatImageUrl)
            intent.putExtra("title", ChatTitle)
            intent.putExtra("price", ChatPrice)
            startActivity(intent)

        }


    }

    private fun compose(
        id : Int,
        sku : String,
        brand : String,
        category : String,
        description : String,
        totalReview : List<DataReviews>,
        reviewImage : String,
        price : Double,
        discount : Double,
        title : String,
        totalRating : Double,
        starCount : Int,
        stock : String,
        stockCount : Int,
        shppingInfo : String,
        returnPolicy : String,
        warranty : String,
        weight : Int,
        dimension: DataDimension,
        thumbnail : String
        ) {

        list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%2Fid%2FOIP.iF1vjDVZtP53C_uUNZjz3AHaFW%3Fpid%3DApi&f=1&ipt=9284869913a54b27ea65f0c382ae35ece41c7ff800d27eb368b8c32d6ab75f34&ipo=images")
        list.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%2Fid%2FOIP.aeAckh3-h_jVt3vY4Mz8SAHaFE%3Fpid%3DApi&f=1&ipt=7fad7d0cfe6617d28df020b42f839d0ce480885c0c8488aea155613bbe1f136e&ipo=images")

        cv_voucher.setContent {

            var deliveryDialog by remember { mutableStateOf(false) }
            var topText by remember { mutableStateOf("") }
            var srText by remember { mutableStateOf("") } // shipping, return Text

            MaterialTheme {

                Column {

                    ProductPrice(price, discount, title, totalRating, starCount, stock, stockCount, addToWishListBtn = {

                        lifecycleScope.launch(Dispatchers.IO){

                            if(!wishlist_stg.checkDuplicateData(sku)){

                                wishlist_stg.insert(sku = sku, imageUrl = thumbnail, title = title, price = price, discount = discount)

                                withContext(Dispatchers.Main){

                                    ShortMessageHelper.toast(this@Act_product_full_info, "Added to wishlist")

                                }

                            }else{

                                withContext(Dispatchers.Main){

                                    ShortMessageHelper.toast(this@Act_product_full_info, "Already in wishlist")

                                }

                            }

                        }

                    }, shareProductBtn = {

                        ShortMessageHelper.toast(this@Act_product_full_info, "Product shared")

                    }, deliveryDialogBtn = {

                        deliveryDialog = true
                        topText = "Delivery"
                        srText = shppingInfo

                    }, returnDialogBtn = {

                        deliveryDialog = true
                        topText = "Service"
                        srText = returnPolicy

                    })


                    vouchers(list =  list ,onClick = {

                        ShortMessageHelper.toast(this@Act_product_full_info, "Compose Worked")

                    })

                    RatingReviews(totalStar = totalRating, list = totalReview, image = reviewImage, onClick = {

                        topText = "Review"
                        deliveryDialog = true

                    })

                    Question(totalReview.size, viewAllClick = {

                        ShortMessageHelper.toast(this@Act_product_full_info, "All Questions")

                    }, askQues = {

                        ShortMessageHelper.toast(this@Act_product_full_info, "Ask Your Question")

                    })

                    val store = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.A_ZnEOdcx2Qm-YsGKIpGAQHaE8%3Fpid%3DApi&f=1&ipt=239285e12e1010f79951f9ee93f1bfa9b56881c5f3012ddaf5246b56b04b5150&ipo=images"

                    Store(storeName = "Rada Krishna", image = store, visitStoreClick = {

                        ShortMessageHelper.toast(this@Act_product_full_info, "welcome to my store")

                    }, followClick = {

                        ShortMessageHelper.toast(this@Act_product_full_info, "Following")

                    })

                    MoreItem(goToStoreClick = {

                        ShortMessageHelper.toast(this@Act_product_full_info, "go to Store")

                    })

                    ProductDetails(brand = brand, hText = "N/A", description = description, category = category, specBtn = {

                        deliveryDialog = true
                        topText = "Specifications"
                    })


                }

                BottomSheetDialog(
                    showDialog = deliveryDialog ,
                    topText = topText,
                    text = srText,
                    brandText = brand,
                    warrantyText = warranty,
                    weightG = weight,
                    dimension = dimension,
                    imageUrl = reviewImage,
                    totalRatingCount = totalRating,
                    reviews = totalReview,
                    onDismiss = {

                    deliveryDialog = false

                })

            }


        }

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

    override fun onDestroy() {
        super.onDestroy()
        wishlist_stg.closeDB()
    }

}//class======================================================================

