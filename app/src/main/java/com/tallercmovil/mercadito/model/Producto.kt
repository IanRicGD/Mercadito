package com.tallercmovil.mercadito.model

import com.google.gson.annotations.SerializedName

class Producto {

    @SerializedName("id")
    var id : String? = null

    @SerializedName("name")
    var name : String? = null

    @SerializedName("thumbnail_url")
    var thumbnailUrl : String? = null

    @SerializedName("price")
    var price : String? = null

    @SerializedName("provider")
    var provider : String? = null

    @SerializedName("delivery")
    var delivery : String? = null


}