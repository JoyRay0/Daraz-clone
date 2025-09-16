package com.rk_sofwares.e_commerce.Model

data class CouponModel(

    val status : String,
    val images : List<image>

)

data class image(
    val oth_image : String,
    val text : String

)
