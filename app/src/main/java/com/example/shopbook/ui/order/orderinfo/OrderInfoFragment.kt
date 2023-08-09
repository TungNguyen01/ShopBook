package com.example.shopbook.ui.order.orderinfo

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
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
import com.example.shopbook.databinding.FragmentOrderInfoBinding
import com.example.shopbook.databinding.FragmentUpdateProfileBinding
import com.example.shopbook.ui.main.adapter.CheckoutAdapter
import com.example.shopbook.ui.profile.updateprofile.UpdateProfileViewModel
import com.example.shopbook.utils.MySharedPreferences
import com.google.android.material.bottomnavigation.BottomNavigationView
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream

class OrderInfoFragment : Fragment() {

    companion object {
        fun newInstance() = OrderInfoFragment()
    }

    private var binding: FragmentOrderInfoBinding? = null
    private lateinit var viewModel: OrderInfoViewModel
    private var customer_id: Int? = null
    private var shipping_region_id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentOrderInfoBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderInfoViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCustomer()
        observeCheckout()
        binding?.apply {
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            buttonUpdate.setOnClickListener {
                updateUser()
                Toast.makeText(context, "Doi Thanh Cong", Toast.LENGTH_SHORT).show()
            }
        }
        activity?.let { MySharedPreferences.init(it.applicationContext) }
    }
    private fun observeCheckout() {
        viewModel.user.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                bindData(it)
            }
        })
    }
    private fun bindData(order: Customer) {
        binding?.apply {
            editFullname.setText(order.name)
            editPhone.setText(order.mob_phone)
            editAddress.setText(order.address)
        }
    }
    private fun updateUser() {
        binding?.apply {
            val fullName = editFullname.text.toString()
            val phone = editPhone.text.toString()
            val address = editAddress.text.toString()
            viewModel.updateCustomer(fullName, address,phone)
        }
    }
}