package com.rk_sofwares.e_commerce.Model

data class ProfileitemModel(

    val status : String?,
    val images : List<Items>
)

data class Items(

    val image : String?,
    val text : String?,
    val item : String?

)
