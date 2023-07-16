package com.example.shopbook.ui.productdetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopbook.R
import com.example.shopbook.ui.productdetail.viewmodel.ProductdetailViewModel

class ProductdetailFragment : Fragment() {

    companion object {
        fun newInstance() = ProductdetailFragment()
    }

    private lateinit var viewModel: ProductdetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_productdetail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductdetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}