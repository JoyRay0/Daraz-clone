package com.rk_sofwares.e_commerce.Model

data class GIFimagesModel(

    val status : String?,
    val images : List<Gifs>

)
data class Gifs(

    val gif : String?,
    val gif_title : String?,
    val gif_information : String?,
    val gif_button : String?,

)
