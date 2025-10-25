package com.rk_sofwares.e_commerce.Other

import android.content.Context

object DomainHelper {

    private var  domain : String = "https://rksoftwares.fun/All_app/Daraz_clone/Api/All_Images.php?resource="

    private var itemLink : String = "item"
    private var couponLink : String = "other_images"
    private var flashSaleLink : String = "flash_sale_images"
    private var categoryLink : String = "category_image"
    private var gifLink : String = "gifs"
    private var messageLink : String = "messages"
    private var viewpagerLink : String = "viewpager"
    private var profileLink : String = "profile_item_images"
    private var productLink : String = "https://dummyjson.com/products"

    fun getItemLink(): String{

        return domain+itemLink

    }

    fun getCouponLink(): String{

        return domain+couponLink

    }

    fun getFlashSaleLink(): String{

        return domain+flashSaleLink

    }

    fun getGifLink(): String{

        return domain+gifLink

    }

    fun getMessagesLink(): String{

        return domain+messageLink

    }

    fun getViewpagerLink(): String{

        return domain+viewpagerLink

    }

    fun getProfileItemLink(): String{

        return domain+profileLink

    }

    fun getProductLink(): String{

        return productLink

    }

    fun getCategoryLink(): String{

        return domain+categoryLink

    }


}