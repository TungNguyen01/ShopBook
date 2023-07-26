package com.example.shopbook.ui.auth.signin

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.shopbook.R
import com.example.shopbook.data.model.LoginResponse
import com.example.shopbook.databinding.FragmentSignInBinding
import com.example.shopbook.ui.auth.forgot.ForgotPasswordFragment
import com.example.shopbook.ui.auth.signin.viewmodel.SignInViewModel
import com.example.shopbook.ui.auth.signup.SignUpFragment
import com.example.shopbook.ui.main.MainMenuFragment
import com.example.shopbook.utils.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

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
        loginButton = view.findViewById(R.id.button_login)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                performLogin(email, password)
            } else {
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun performLogin(email: String, password: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response : Response<LoginResponse> = RetrofitClient.apiService.login(email, password)
                if (response.isSuccessful) {
                    val loginResponse: LoginResponse? = response.body()
                    Log.d("tung", loginResponse.toString())
                    if (loginResponse != null) {
                        navigateToMainScreen()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.localizedMessage?.let { Log.d("tung", it) }
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "An error occurred", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigateToMainScreen() {
        val fragment = MainMenuFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
