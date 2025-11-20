package com.rk_softwares.e_commerce.model

data class CouponModel(

    val status : String,
    val images : List<image>

)

data class image(
    val oth_image : String,
    val text : String

)
