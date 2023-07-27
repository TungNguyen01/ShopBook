package com.example.shopbook.ui.order.orderdetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopbook.R
import com.example.shopbook.databinding.FragmentOrderDetailBinding
import com.example.shopbook.ui.adapter.BookAdapter
import com.example.shopbook.ui.adapter.OrderDetailAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class OrderDetailFragment : Fragment() {

    private var binding:FragmentOrderDetailBinding?=null

    private lateinit var viewModel: OrderDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentOrderDetailBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderDetailViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.visibility = View.GONE
        val orderDetailList = viewModel.getOrderDetails()
        val adapter = OrderDetailAdapter(orderDetailList)
        binding?.recyclerOrderDetail?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerOrderDetail?.adapter = adapter
    }
}