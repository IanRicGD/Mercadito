package com.tallercmovil.mercadito.model

import com.google.gson.annotations.SerializedName

class ProductDetail {

    @SerializedName("name")
    var name : String? = null

    @SerializedName("imag_url")
    var imagUrl : String? = null

    @SerializedName("desc")
    var desc : String? = null

}