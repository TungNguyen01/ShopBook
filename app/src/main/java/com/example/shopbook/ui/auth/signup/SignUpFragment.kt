package com.example.shopbook.ui.auth.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopbook.R
import com.example.shopbook.databinding.FragmentSignUpBinding
import com.example.shopbook.ui.auth.signup.viewmodel.SignUpViewModel

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var viewModel: SignUpViewModel
    private var binding: FragmentSignUpBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        // TODO: Use the ViewModel
        binding?.imgback?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

}