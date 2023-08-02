package com.example.shopbook.ui.order.orderdetail

import android.annotation.SuppressLint
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopbook.R
import com.example.shopbook.databinding.FragmentOrderDetailBinding
import com.example.shopbook.ui.adapter.BookAdapter
import com.example.shopbook.ui.adapter.OrderDetailAdapter
import com.example.shopbook.utils.FormatDate
import com.example.shopbook.utils.FormatMoney
import com.google.android.material.bottomnavigation.BottomNavigationView

class OrderDetailFragment : Fragment() {

    private var binding: FragmentOrderDetailBinding? = null
    private var formatDate = FormatDate()
    private lateinit var viewModel: OrderDetailViewModel
    private val formatMoney= FormatMoney()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentOrderDetailBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderDetailViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.loadingLayout?.root?.visibility=View.VISIBLE
        val orderId = arguments?.getString("orderId")?.toInt()
        val orderStatus = arguments?.getString("orderStatus")
        orderId?.let {
            viewModel.getOrderDetails(it)
            viewModel.orderDetailList.observe(viewLifecycleOwner, Observer {
                val adapter = OrderDetailAdapter(it.products)
                binding?.apply {
                    recyclerOrderDetail.layoutManager = LinearLayoutManager(context)
                    recyclerOrderDetail.adapter = adapter
                    textIdOrder.text="#Order"+it.orderId
                    textPro.text=resources.getString(R.string.product).capitalize()
                    textOrderDate.text =
                        resources.getString(R.string.createdOn) + " " + formatDate.formatDate(it.createdOn)
                    textOrderAddress.text =
                        resources.getString(R.string.orderAddress) + " " + it.address
                    textOrderSum.text =
                        resources.getString(R.string.orderQuantity) + " " + it.products.size
                    textStatus.text=resources.getString(R.string.textStatus)+" "
                    textOrderStatus.text = orderStatus
                    textTotal.text=resources.getString(R.string.textTotal)+" "
                    textTotalMoney.text = it.orderTotal?.let { orderTotal -> formatMoney.formatMoney(orderTotal.toDouble().toLong()) }
                    loadingLayout.root.visibility=View.INVISIBLE
                }
            })
        }
        binding?.imageLeftOrder?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}