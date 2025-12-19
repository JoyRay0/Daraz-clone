package com.rk_softwares.e_commerce.model

import com.google.gson.annotations.SerializedName

data class ProductModel(

    @SerializedName("status")
    val status : String = "",

    @SerializedName("page")
    val page : Int = 0,

    @SerializedName("products")
    val products : List<Product> = emptyList()

)

data class Product(

    val id : Int = 0,
    val title : String = "",
    val description : String = "",
    val category : String = "",
    val price : Double = 0.0,
    val discountPercentage : Double = 0.0,
    val rating : Double = 0.0,
    val stock : Int = 0,
    val tags : List<String> = emptyList(),
    val brand : String = "",
    val sku : String = "",
    val weight : Int = 0,
    val dimensions : DataDimension = DataDimension(),
    val warrantyInformation : String = "",
    val shippingInformation : String = "",
    val availabilityStatus : String = "",
    val reviews : List<DataReviews> = emptyList(),
    val returnPolicy : String = "",
    val minimumOrderQuantity : Int = 0,
    val meta : DataMeta = DataMeta(),
    val images : List<String> = emptyList(),
    val thumbnail : String = ""
)

data class DataDimension(

     val width : Double = 0.0,
     val height : Double = 0.0,
     val depth : Double = 0.0
)

data class DataReviews(

     val rating : Int = 0,
     val comment : String = "",
     val date : String = "",
     val reviewerName : String = "",
     val reviewerEmail : String =""
)

data class DataMeta(

     val createdAt : String = "",
     val updatedAt : String = "",
     val barcode : String = "",
     val qrCode : String = ""

)
