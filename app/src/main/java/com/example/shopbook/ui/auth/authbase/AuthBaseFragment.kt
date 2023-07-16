package com.example.shopbook.ui.auth.authbase

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopbook.R
import com.example.shopbook.ui.auth.authbase.viewmodel.AuthBaseViewModel

class AuthBaseFragment : Fragment() {

    companion object {
        fun newInstance() = AuthBaseFragment()
    }

    private lateinit var viewModel: AuthBaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth_base, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AuthBaseViewModel::class.java)
        // TODO: Use the ViewModel
    }

}