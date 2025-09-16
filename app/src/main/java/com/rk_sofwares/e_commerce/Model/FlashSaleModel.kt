package com.rk_sofwares.e_commerce.Model

data class FlashSaleModel(
    val status : String ?,
    val images : List<sale_image>

)

data class sale_image(

    val sale_image : String ?,
    val text : String ?,
    val price : String ?,
    val discount : String ?

)
