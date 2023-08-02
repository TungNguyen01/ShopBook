package com.example.shopbook.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.shopbook.R
import com.example.shopbook.data.model.Customer
import com.example.shopbook.databinding.FragmentProductDetailBinding
import com.example.shopbook.databinding.FragmentProfileBinding
import com.example.shopbook.ui.auth.signin.SignInFragment
import com.example.shopbook.ui.order.orderhistory.OrderHistoryFragment
import com.example.shopbook.ui.profile.changepass.ChangePassFragment
import com.example.shopbook.ui.profile.profilesignin.ProfileSigninFragment
import com.example.shopbook.ui.profile.updateprofile.UpdateProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.layoutLoading?.root?.visibility=View.VISIBLE
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.visibility = View.GONE
        viewModel.getCustomer()
        var email=""
//        var passw
        viewModel.profile.observe(viewLifecycleOwner, Observer {
            bindData(it)
            email= it.email.toString()
        })
        binding?.apply {
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            imageUpdate.setOnClickListener {
                val fragmentUpdate = UpdateProfileFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, fragmentUpdate)
                    .addToBackStack("profile")
                    .commit()
            }
            textLogout.setOnClickListener {
                val fragmentSignin = ProfileSigninFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, fragmentSignin)
                    .addToBackStack("profile")
                    .commit()
            }
            textChange.setOnClickListener {
                val fragmentChangePass = ChangePassFragment()
                val bundle=Bundle()
                bundle.putString("email", email)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, fragmentChangePass.apply { arguments=bundle })
                    .addToBackStack("profile")
                    .commit()
            }
            linearMyOrder.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, OrderHistoryFragment())
                    .addToBackStack("profile")
                    .commit()
            }
        }
    }

    private fun bindData(profile: Customer) {
        binding?.apply {
            Glide.with(root)
                .load(profile.avatar)
                .centerCrop()
                .into(imageAvatar)
            textName.text = profile.name
            textMail.text = profile.email
        }
        binding?.layoutLoading?.root?.visibility=View.INVISIBLE
    }
}