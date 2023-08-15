package com.example.shopbook.ui.order.checkout

import android.Manifest
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.shopbook.R
import com.example.shopbook.data.model.Customer
import com.example.shopbook.databinding.FragmentCheckOutBinding
import com.example.shopbook.databinding.FragmentProfileBinding
import com.example.shopbook.databinding.FragmentShoppingBagBinding
import com.example.shopbook.databinding.FragmentUpdateProfileBinding
import com.example.shopbook.ui.main.adapter.BagAdapter
import com.example.shopbook.ui.main.adapter.CheckoutAdapter
import com.example.shopbook.ui.order.orderinfo.OrderInfoFragment
import com.example.shopbook.ui.profile.updateprofile.UpdateProfileViewModel
import com.example.shopbook.utils.MySharedPreferences
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.delay

class CheckOutFragment : Fragment() {
    private var binding: FragmentCheckOutBinding? = null
    private lateinit var checkoutAdapter: CheckoutAdapter
    private lateinit var viewModel: CheckOutViewModel
    companion object {
        fun newInstance() = CheckOutFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCheckOutBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CheckOutViewModel::class.java)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val info = viewModel.profile.value
        viewModel.getCustomer()
        viewModel.getCart()
        checkoutAdapter = CheckoutAdapter()
        observeCheckout()
        viewModel.cart.observe(viewLifecycleOwner, {cart ->
            checkoutAdapter.updateData(cart)
        })
        binding?.recyclerviewCheckout?.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = checkoutAdapter
        }
        val idCart = viewModel.idcart.value

        binding?.apply {
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            textviewUpdate.setOnClickListener {
                val orderinfoFragment = OrderInfoFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, orderinfoFragment)
                    .addToBackStack("HomeFragment")
                    .commit()
            }

            buttonCheckout.setOnClickListener {
                viewModel.createOrder(viewModel.idcart.value.toString(), 1, textviewDiachi.text.toString(), textviewName.text.toString(), textviewPhone.text.toString())
                Toast.makeText(context, "Thanh toán thàn công", Toast.LENGTH_SHORT).show()
            }
        }
        activity?.let { MySharedPreferences.init(it.applicationContext) }
    }
    private fun observeCheckout() {
        viewModel.profile.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Log.d("tungtung", it.toString())
                bindData(it)
            }
        })
    }
    private fun bindData(checkout: Customer) {
        binding?.apply {
            textviewName.text = checkout.name
            textviewPhone.text = checkout.mob_phone
            textviewDiachi.text = checkout.address
        }
    }
}