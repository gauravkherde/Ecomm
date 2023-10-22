package com.gaurav145.ecommDemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.gaurav145.ecomdemo.databinding.ItemProductBinding
import com.gaurav145.ecommDemo.Model.Product


class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    lateinit var context: Context
     var productList= ArrayList<Product>()
    lateinit var onItemClick: ((Product) -> Unit)
    fun setProduct(product: ArrayList<Product>, context: Context) {
        this.productList = product
        this.context = context
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView).load(productList[position].thumbnail)
            .into(holder.binding.rvProductImage)
        holder.binding.tvProductName.text = productList[position].title
        holder.binding.tvProductPrice.text = "Price: Rs "+productList[position].price.toString()
        holder.binding.cardProductItem.setOnClickListener {
                onItemClick.invoke(productList[position])
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }


}