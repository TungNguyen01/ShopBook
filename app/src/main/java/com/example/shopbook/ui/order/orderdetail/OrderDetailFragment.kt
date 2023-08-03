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
import com.example.shopbook.data.model.OrderDetail
import com.example.shopbook.databinding.FragmentOrderDetailBinding
import com.example.shopbook.ui.adapter.OrderDetailAdapter
import com.example.shopbook.utils.FormatDate
import com.example.shopbook.utils.FormatMoney

class OrderDetailFragment : Fragment() {

    private var binding: FragmentOrderDetailBinding? = null
    private var formatDate = FormatDate()
    private lateinit var viewModel: OrderDetailViewModel
    private lateinit var adapter: OrderDetailAdapter
    private val formatMoney = FormatMoney()

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

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = OrderDetailAdapter()
        binding?.loadingLayout?.root?.visibility = View.VISIBLE
        val orderId = arguments?.getString("orderId")?.toInt()
        val orderStatus = arguments?.getString("orderStatus")
        orderId?.let { orderId ->
            viewModel.getOrderDetails(orderId)
            viewModel.orderDetailList.observe(viewLifecycleOwner, Observer {
                adapter.setData(it.products)
                bindData(it, orderStatus.toString())
            })
        }
        binding?.recyclerOrderDetail?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerOrderDetail?.adapter = adapter
        binding?.imageLeftOrder?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun bindData(it: OrderDetail, orderStatus: String) {
        binding?.apply {
            textIdOrder.text = "#Order" + it.orderId
            textPro.text = resources.getString(R.string.product).capitalize()
            textOrderDate.text =
                resources.getString(R.string.createdOn) + " " + formatDate.formatDate(it.createdOn)
            textOrderAddress.text =
                resources.getString(R.string.orderAddress) + " " + it.address
            textOrderSum.text =
                resources.getString(R.string.orderQuantity) + " " + it.products.size
            textStatus.text = resources.getString(R.string.textStatus) + " "
            textOrderStatus.text = orderStatus
            textTotal.text = resources.getString(R.string.textTotal) + " "
            textTotalMoney.text = it.orderTotal?.let { orderTotal ->
                formatMoney.formatMoney(
                    orderTotal.toDouble().toLong()
                )
            }
            loadingLayout.root.visibility = View.INVISIBLE
        }
    }
}