package com.gaurav145.ecommDemo.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gaurav145.ecommDemo.Model.Product
import com.gaurav145.ecommDemo.Model.Products
import com.gaurav145.ecommDemo.Retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsViewModel :ViewModel() {
    val productListLiveData =MutableLiveData<List<Product>>()

    fun getAllProducts(limit :Int)
    {
        RetrofitInstance.api.getProducts(limit).enqueue(object : Callback<Products> {
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                if (response!=null&& response.isSuccessful)
                {
                    productListLiveData.postValue(response.body()!!.products)
                }
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun observeAllProducts() :LiveData<List<Product>>{
        return  productListLiveData
    }
}