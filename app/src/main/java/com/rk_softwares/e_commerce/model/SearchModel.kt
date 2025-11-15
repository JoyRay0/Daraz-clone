package com.rk_softwares.e_commerce.model

data class SearchModel(

    val status : String,
    val message : String,
    val itemCount : Int,
    val data : List<Data>

)

data class Data(

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
     val dimensions : SearchDimensions?,
     val warrantyInformation : String?,
     val shippingInformation : String?,
     val availabilityStatus : String?,
     val reviews : List<SearchReview>?,
     val returnPolicy : String?,
     val minimumOrderQuantity : Int?,
     val meta : SearchMeta?,
     val images : List<String>?,
     val thumbnail : String?

    )

data class SearchDimensions(

     val width : Double?,
     val height : Double?,
     val depth : Double?
)

data class SearchReview(

     val rating : Int?,
     val comment : String?,
     val date : String?,
     val reviewerName : String?,
     val reviewerEmail : String?
)

data class SearchMeta(

     val createdAt : String?,
     val updatedAt : String?,
     val barcode : String?,
     val qrCode : String?

)
