package com.example.shopbook.ui.profile.updateprofile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopbook.R
import com.example.shopbook.databinding.FragmentUpdateProfileBinding
import com.example.shopbook.ui.profile.permission.PermissionFragment

class UpdateProfileFragment : Fragment() {

    companion object {
        fun newInstance() = UpdateProfileFragment()
    }

    private var binding:FragmentUpdateProfileBinding?=null
    private lateinit var viewModel: UpdateProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentUpdateProfileBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpdateProfileViewModel::class.java)
        // TODO: Use the ViewModel
        binding?.imageLeft?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding?.cardview?.setOnClickListener {
            val fragmentPermission=PermissionFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragmentPermission)
                .addToBackStack("updateProfile")
                .commit()
        }
        binding?.imageLeft?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

}