package com.rk_softwares.e_commerce.model

data class ItemModel(

    val status : String ?,
    val images : List<Image_item>

)

data class Image_item(

    val image : String ?,
    val text : String ?
)
