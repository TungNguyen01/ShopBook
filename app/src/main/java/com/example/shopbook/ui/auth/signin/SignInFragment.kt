package com.example.shopbook.ui.auth.signin

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.shopbook.R
import com.example.shopbook.databinding.FragmentSignInBinding
import com.example.shopbook.ui.auth.forgot.ForgotPasswordFragment
import com.example.shopbook.ui.auth.signin.viewmodel.SignInViewModel
import com.example.shopbook.ui.auth.signup.SignUpFragment
import com.example.shopbook.ui.main.MainMenuFragment

class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private lateinit var viewModel: SignInViewModel
    private var binding: FragmentSignInBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonLogin?.setOnClickListener {
            val email = binding?.edittext1?.text.toString()
            val password = binding?.edittext2?.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Gọi phương thức đăng nhập
                val fragment= MainMenuFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
            } else {
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

//        binding?.buttonLogin?.setOnClickListener {
//            val fragment= MainMenuFragment()
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.container, fragment)
//                .commit()
//        }
//        binding?.textForgotPass?.setOnClickListener {
//            val fragment= ForgotPasswordFragment()
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.container, fragment)
//                .addToBackStack("Signin")
//                .commit()
//        }
//        binding?.textRegister?.setOnClickListener {
//            val fragment= SignUpFragment()
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.container, fragment)
//                .addToBackStack("Signin")
//                .commit()
//        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        // TODO: Use the ViewModel

    }

//    private fun performLogin(email: String, password: String) {
//        val loginRequest = LoginRequest(email, password)
//
//        GlobalScope.launch(Dispatchers.Main) {
//            try {
//                val response: Response<LoginResponse> = RetrofitClient.apiService.login(loginRequest)
//                if (response.isSuccessful) {
//                    val loginResponse: LoginResponse? = response.body()
//                    if (loginResponse != null) {
//                        saveUserData(loginResponse.accessToken, loginResponse.customer)
//                        navigateToMainScreen()
//                    }
//                } else {
//                    Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
//                }
//            } catch (e: Exception) {
//                Toast.makeText(requireContext(), "An error occurred", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun saveUserData(accessToken: String, customer: Customer) {
//        val sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.putString("accessToken", accessToken)
//        editor.putString("customerName", customer.name)
//        editor.apply()
//    }

//    private fun navigateToMainScreen() {
//        val intent = Intent(requireContext(), MainActivity::class.java)
//        startActivity(intent)
//        requireActivity().finish()
//    }
}