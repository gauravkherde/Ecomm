package com.gaurav145.ecommDemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.gaurav145.ecomdemo.databinding.FragmentProductDetailBinding
import com.gaurav145.ecommDemo.Model.Product
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.gaurav145.ecomdemo.R


class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    private  lateinit var productDetail : Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailBinding.inflate(layoutInflater, container, false)
/*        val callback=object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_productDetailFragment_to_homeFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)*/

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_productDetailFragment_to_homeFragment)
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        if (arguments != null)
            productDetail = (arguments?.get("ProductDetails") as Product?)!!
        init()
        return binding.root
    }

    private fun init() {
        binding.tvBrandName.text = "Brand "+productDetail.brand
        binding.tvProductName.text = productDetail.title
        Glide.with(requireContext()).load(productDetail.thumbnail).into(binding.productImage)
        binding.ratingBar.rating = productDetail.rating.toFloat()
        binding.tvFinalAmount.text = "Price: ₹" + productDetail.price.toString()
        binding.tvDiscountValue.text = "-" + productDetail.discountPercentage.toString()+ "%"

        binding.tvMrp.text = "M.R.P.: ₹"+ String.format("%.2f", (productDetail.price / (1-(productDetail.discountPercentage/100)))).toDouble()
        binding.tvDescriptionValue.text = productDetail.description

    }
}