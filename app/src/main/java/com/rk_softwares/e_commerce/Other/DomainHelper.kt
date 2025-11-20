package com.rk_softwares.e_commerce.Other

import android.content.Context

object DomainHelper {

    private var  domain : String = "https://rksoftwares.fun/All_app/Daraz_clone/Api/"

    private var all_image : String = "All_Images.php?resource="
    private var search : String = "search.php?query="
    private var suggestion : String = "products.php?page="

    private var itemLink : String = "item"
    private var couponLink : String = "other_images"
    private var flashSaleLink : String = "flash_sale_images"
    private var categoryLink : String = "category_image"
    private var gifLink : String = "gifs"
    private var messageLink : String = "messages"
    private var viewpagerLink : String = "viewpager"
    private var profileLink : String = "profile_item_images"


    fun getItemLink(): String{

        return domain+all_image+itemLink

    }

    fun getCouponLink(): String{

        return domain+all_image+couponLink

    }

    fun getFlashSaleLink(): String{

        return domain+all_image+flashSaleLink

    }

    fun getGifLink(): String{

        return domain+all_image+gifLink

    }

    fun getMessagesLink(): String{

        return domain+all_image+messageLink

    }

    fun getViewpagerLink(): String{

        return domain+all_image+viewpagerLink

    }

    fun getProfileItemLink(): String{

        return domain+all_image+profileLink

    }

    fun getProductLink(): String{

        return domain+suggestion

    }

    fun getSearchLink() : String{

        return domain+search

    }

    fun getCategoryLink(): String{

        return domain+all_image+categoryLink

    }


}