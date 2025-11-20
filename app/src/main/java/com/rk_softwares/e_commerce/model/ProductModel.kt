package com.rk_softwares.e_commerce.model

data class ProductModel(

    val status : String,
    val page : Int,
    val data : List<Product>

)

data class Product(

     val title : String?,
     val description : String?,
     val category : String?,
     val price : Double?,
     val discountPercentage : Double?,
     val rating : Double?,
     val stock : Int?,
     val tags : List<String>?,
     val brand : String?,
     val sku : String?,
     val weight : Int?,
     val dimensions : DataDimension?,
     val warrantyInformation : String?,
     val shippingInformation : String?,
     val availabilityStatus : String?,
     val reviews : List<DataReviews>?,
     val returnPolicy : String?,
     val minimumOrderQuantity : Int?,
     val meta : DataMeta?,
     val images : List<String>?,
     val thumbnail : String?
)

data class DataDimension(

     val width : Double?,
     val height : Double?,
     val depth : Double?
)

data class DataReviews(

     val rating : Int?,
     val comment : String?,
     val date : String?,
     val reviewerName : String?,
     val reviewerEmail : String?
)

data class DataMeta(

     val createdAt : String?,
     val updatedAt : String?,
     val barcode : String?,
     val qrCode : String?

)
