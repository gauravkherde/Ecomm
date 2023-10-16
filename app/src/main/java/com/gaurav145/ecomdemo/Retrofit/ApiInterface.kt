package com.gaurav145.ecomdemo.Retrofit

import com.gaurav145.ecomdemo.Model.Products
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("products")
    fun getProducts(): Call<Products>

}