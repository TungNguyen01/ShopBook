package com.example.shopbook.ui.order.orderhistory

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopbook.R

class OrderHistoryFragment : Fragment() {

    companion object {
        fun newInstance() = OrderHistoryFragment()
    }

    private lateinit var viewModel: OrderHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_history, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderHistoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}