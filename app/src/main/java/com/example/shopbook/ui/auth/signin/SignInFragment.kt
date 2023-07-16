package com.example.shopbook.ui.auth.signin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopbook.R
import com.example.shopbook.ui.auth.signin.viewmodel.SignInViewModel

class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private lateinit var viewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        // TODO: Use the ViewModel
    }

}