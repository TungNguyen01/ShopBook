package com.example.shopbook.ui.auth.signin

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.shopbook.MainActivity
import com.example.shopbook.R
import com.example.shopbook.ui.auth.signin.viewmodel.SignInViewModel

class SignInFragment : Fragment() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        emailEditText = view.findViewById(R.id.edittext1)
        passwordEditText = view.findViewById(R.id.edittext2)
        loginButton = view.findViewById(R.id.bt1)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Gọi phương thức đăng nhập
                navigateToMainScreen()
            } else {
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        return view
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

    private fun navigateToMainScreen() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}