package com.rk_sofwares.e_commerce.model

data class ViewpagerModel(
    val status : String,
    val images : List<V_images>

)
data class V_images(

    val vp_image : String,
    val text : String

)
