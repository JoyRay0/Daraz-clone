package com.rk_sofwares.e_commerce.Model

data class ItemModel(

    val status : String ?,
    val images : List<Image_item>

)

data class Image_item(

    val image : String ?,
    val text : String ?
)
