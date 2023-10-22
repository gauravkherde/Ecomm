package com.gaurav145.ecommDemo.Retrofit

import com.gaurav145.ecommDemo.Model.Products
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("products")
    fun getProducts(@Query("limit")limit:Int=100 ): Call<Products>

}