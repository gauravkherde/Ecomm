package com.gaurav145.ecommDemo.Retrofit

import com.gaurav145.ecommDemo.Model.Products
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("products")
    fun getProducts(): Call<Products>

}