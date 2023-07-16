package com.example.shopbook.ui.main.shoppingbag

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopbook.R
import com.example.shopbook.ui.main.shoppingbag.viewmodel.ShoppingbagViewModel

class ShoppingbagFragment : Fragment() {

    companion object {
        fun newInstance() = ShoppingbagFragment()
    }

    private lateinit var viewModel: ShoppingbagViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shoppingbag, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShoppingbagViewModel::class.java)
        // TODO: Use the ViewModel
    }

}