package com.rk_softwares.e_commerce.model

data class CategoriesModel(

    val status : String,
    val images : List<c_images>

)

data class c_images(

    val cate_image : String?,
    val text : String ?

)
