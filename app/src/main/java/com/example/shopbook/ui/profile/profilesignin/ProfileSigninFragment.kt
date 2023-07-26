
package com.example.shopbook.ui.profile.profilesignin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopbook.R

class ProfileSigninFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileSigninFragment()
    }

    private lateinit var viewModel: ProfileSigninViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_signin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileSigninViewModel::class.java)
        // TODO: Use the ViewModel
    }

}