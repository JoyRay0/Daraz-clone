package com.rk_softwares.e_commerce.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.adapter.ProductImageAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class Act_product_full_info : AppCompatActivity() {

    //XML id's----------------------------------------------------------------

    //toolbar

    //bottom bar


    //image

    private lateinit var fl_image : FrameLayout
    private lateinit var vp_product_image : ViewPager2
    private lateinit var dotsIndicator : DotsIndicator

    private var mainImageList : ArrayList<HashMap<String, String>> = ArrayList()
    private lateinit var h : HashMap<String, String>


    //other
    private lateinit var productImageAdapter: ProductImageAdapter

    //XML id's----------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_product_full_info)

        //identity period-----------------------------------------------------

        vp_product_image = findViewById(R.id.vp_product_image)
        dotsIndicator = findViewById(R.id.dotsIndicator)

        //identity period-----------------------------------------------------

        productImageAdapter = ProductImageAdapter(this, mainImageList)
        vp_product_image.adapter = productImageAdapter
        dotsIndicator.attachTo(vp_product_image)
        vp_product_image.currentItem = 1

        test()


    }// on create=============================================================

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
}//class======================================================================

