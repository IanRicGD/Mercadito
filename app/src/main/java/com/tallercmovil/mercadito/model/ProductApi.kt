package com.tallercmovil.mercadito.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ProductApi {

    @GET
    fun getProducts(
        @Url url: String?
    ): Call<List<Producto>>

    @GET("cm/2022-2/product_detail.php")
    fun getProductDetail(
        @Query("id") id : String?
    ): Call<ProductDetail>

}