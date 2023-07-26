package com.example.shopbook.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopbook.R
import com.example.shopbook.databinding.FragmentProductDetailBinding
import com.example.shopbook.databinding.FragmentProfileBinding
import com.example.shopbook.ui.auth.signin.SignInFragment
import com.example.shopbook.ui.profile.changepass.ChangePassFragment
import com.example.shopbook.ui.profile.profilesignin.ProfileSigninFragment
import com.example.shopbook.ui.profile.updateprofile.UpdateProfileFragment

class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null
    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentProfileBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding?.imageLeft?.setOnClickListener{
            parentFragmentManager.popBackStack()
        }
        binding?.imageUpdate?.setOnClickListener{
            val fragmentUpdate=UpdateProfileFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragmentUpdate)
                .addToBackStack("profile")
                .commit()
        }
        binding?.textChange?.setOnClickListener{
            val fragmentChangePass=ChangePassFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragmentChangePass)
                .addToBackStack("profile")
                .commit()
        }
        binding?.textLogout?.setOnClickListener {
            val fragmentSignin=ProfileSigninFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragmentSignin)
                .addToBackStack("profile")
                .commit()
        }
    }

}