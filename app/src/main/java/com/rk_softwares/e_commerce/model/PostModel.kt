package com.rk_softwares.e_commerce.model

data class PostModel (
    val id : Int?,
    val title : String?,
    val sku : String?
)

data class SkuModel(
    val sku : String?
)

data class ChatMessage(
    val id : Long,
    val message : String,
    val isMe : Boolean
)

data class UserAddress(
    val name : String,
    val number : String,
    val city : String,
    val district : String,
    val address : String,
    val addressCategory : String,
)