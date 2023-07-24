package com.example.shopbook.ui.onboarding

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopbook.R
import com.example.shopbook.databinding.FragmentOnboardingBinding
import com.example.shopbook.ui.auth.signin.SignInFragment
import com.example.shopbook.ui.main.MainMenuFragment

class OnboardingFragment : Fragment() {

    companion object {
        fun newInstance() = OnboardingFragment()
    }

    private var binding:FragmentOnboardingBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding=FragmentOnboardingBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        binding?.textStart?.setOnClickListener {
            val fragment= SignInFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
    }

}