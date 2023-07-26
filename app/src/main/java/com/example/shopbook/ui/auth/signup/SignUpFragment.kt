package com.example.shopbook.ui.auth.signup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.shopbook.R
import com.example.shopbook.data.model.AccessTokenResponse
import com.example.shopbook.ui.auth.signin.SignInFragment
import com.example.shopbook.utils.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpFragment : Fragment() {
    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var cfpasswordEditText: EditText
    private lateinit var registerButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        usernameEditText = view.findViewById(R.id.edittext1)
        emailEditText = view.findViewById(R.id.edittext2)
        passwordEditText = view.findViewById(R.id.edittext3)
        cfpasswordEditText = view.findViewById(R.id.edittext4)
        registerButton = view.findViewById(R.id.bt1)

        registerButton.setOnClickListener {
            val name = usernameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val cfpassword = cfpasswordEditText.text.toString()
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && cfpassword == password) {
                registerUser(email,name,  password)
            } else {
                Toast.makeText(requireContext(), "Nhap lai", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
    private fun registerUser(email: String,name: String,  password: String) {
        val apiService = RetrofitClient.apiService
        val call = apiService.register(email,name,  password)

        call.enqueue(object : Callback<AccessTokenResponse> {
            override fun onResponse(call: Call<AccessTokenResponse>, response: Response<AccessTokenResponse>) {
                if (response.isSuccessful) {
                    val accessTokenResponse = response.body()
                    Log.d("son", (response.body()).toString())
                    if (accessTokenResponse != null) {
                        navigateToSignIn()
                    }
                } else {
                    // Xử lý khi request không thành công
                    val b = "loi"
                    Log.d("son", b)
                }
            }

            override fun onFailure(call: Call<AccessTokenResponse>, t: Throwable) {
                // Xử lý khi request thất bại
            }
        })
    }


    private fun navigateToSignIn() {
        val fragment = SignInFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack("signupFragment")
        transaction.commit()
    }

}