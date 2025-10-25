package com.rk_sofwares.e_commerce.model

data class MessageModel(

    val status : String?,
    val images : List<Messaage>

)

data class Messaage(

    val title : String?,
    val date : String?,
    val image : String?,
    val offer_message : String?,
    val item : String?,

)
