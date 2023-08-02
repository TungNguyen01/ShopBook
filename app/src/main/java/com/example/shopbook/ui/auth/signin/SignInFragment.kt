package com.example.shopbook.ui.auth.signin


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.shopbook.R
import com.example.shopbook.ui.auth.forgot.ForgotPasswordFragment
import com.example.shopbook.ui.auth.signup.SignUpFragment
import com.example.shopbook.ui.main.MainMenuFragment
import com.example.shopbook.data.repository.auth.AuthRepository
import com.example.shopbook.data.repository.auth.AuthRepositoryImp
import com.example.shopbook.ui.auth.signin.viewmodel.SignInViewModel

class SignInFragment : Fragment() {
    private val viewModel: SignInViewModel by viewModels()
    private val authRepository: AuthRepository = AuthRepositoryImp()
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerTextView: TextView
    private lateinit var forgotTextView: TextView
    private lateinit var loginButton: Button
    private lateinit var pref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPreferences = context?.getSharedPreferences("my_app_pref", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()

        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        emailEditText = view.findViewById(R.id.edittext1)
        passwordEditText = view.findViewById(R.id.edittext2)
        registerTextView = view.findViewById(R.id.text_register)
        forgotTextView = view.findViewById(R.id.text_forgot_pass)
        loginButton = view.findViewById(R.id.button_login)
        val accessToken = sharedPreferences?.getString("access_token", null)
        Log.d("tung", accessToken.toString())
        if(accessToken != null){
            navigateToMainScreen()
        }
        viewModel.accessToken.observe(viewLifecycleOwner, Observer{
            if(it!=null){
                editor?.putString("access_token", it.toString())
                Log.d("tung", it.toString())
                editor?.apply()
                Log.d("SHAREEEE", sharedPreferences?.getString("access_token", "").toString())
            }
        })

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.performLogin(email, password)
               // onSignInSuccess()
            } else {
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loginSuccess.observe(viewLifecycleOwner, Observer {
            if (it) {
                navigateToMainScreen()
            }
        })

        registerTextView.setOnClickListener {
            navigateToSignUpFragment()
        }
        forgotTextView.setOnClickListener {
            navigateToForGotPassWordFragment()
        }
        return view
    }

    private fun navigateToSignUpFragment() {
        val fragment = SignUpFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack("signinFragment")
        transaction.commit()
    }
    private fun navigateToForGotPassWordFragment() {
        val fragment = ForgotPasswordFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack("signinFragment")
        transaction.commit()
    }
    fun navigateToMainScreen() {
        val fragment = MainMenuFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack("signinFragment")
        transaction.commit()
    }
}

