package com.gaurav145.ecomdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gaurav145.ecomdemo.Model.Product
import com.gaurav145.ecomdemo.Model.Products
import com.gaurav145.ecomdemo.Retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsViewModel  {
    val productListLiveData =MutableLiveData<List<Product>>()

    fun getAllProducts()
    {
        RetrofitInstance.api.getProducts().enqueue(object : Callback<Products> {
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