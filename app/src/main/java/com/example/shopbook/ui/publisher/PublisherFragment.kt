package com.example.shopbook.ui.publisher

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopbook.R
import com.example.shopbook.databinding.FragmentPublisherBinding

class PublisherFragment : Fragment() {

    private var binding: FragmentPublisherBinding? = null
    companion object {
        fun newInstance() = PublisherFragment()
    }

    private lateinit var viewModel: PublisherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PublisherViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding=FragmentPublisherBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}