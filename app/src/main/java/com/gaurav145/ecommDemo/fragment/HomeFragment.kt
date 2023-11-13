package com.gaurav145.ecommDemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
        viewModel.getAllProducts(100)
        observeAllProducts()
        prepareRecyclerView()
        onProductItemClick()
        initSpinner()
        return binding.root
    }

    private fun initSpinner() {

        val languages =  resources.getStringArray(R.array.Categories)

         val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, languages)
        binding.spinnerCategories.adapter = adapter

        binding.spinnerCategories.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                Toast.makeText(requireContext(), "Selected item " + "" + languages[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

    }

    private fun onProductItemClick() {
        productAdapter.onItemClick={
            val productDetailFragment = ProductDetailFragment()
            val args = Bundle()
            args.putSerializable("ProductDetails", it)
            productDetailFragment.arguments = args
           // fragmentManager!!.beginTransaction().replace(R.id.mainFragment, productDetailFragment).commit()
           findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment,args)
        }
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

}