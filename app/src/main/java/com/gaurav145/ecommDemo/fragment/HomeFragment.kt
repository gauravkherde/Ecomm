package com.gaurav145.ecommDemo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.GridLayoutManager
import com.gaurav145.ecomdemo.R
import com.gaurav145.ecomdemo.databinding.FragmentHomeBinding
import com.gaurav145.ecommDemo.Model.Product
import com.gaurav145.ecommDemo.ViewModel.ProductsViewModel
import com.gaurav145.ecommDemo.adapter.ProductAdapter


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var productAdapter: ProductAdapter
    lateinit var viewModel: ProductsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[ProductsViewModel::class.java]
        productAdapter = ProductAdapter()
        viewModel.getAllProducts()
        observeAllProducts()
        prepareRecyclerView()
        return binding.root
    }

    private fun observeAllProducts() {
        viewModel.observeAllProducts().observe(viewLifecycleOwner, Observer {
            productAdapter.setProduct(it as ArrayList<Product>, requireContext())

        })
    }

    private fun prepareRecyclerView() {
        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = productAdapter
        }
    }

    companion object {

    }
}