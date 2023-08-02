package com.example.shopbook.ui.auth.forgot

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
import com.example.shopbook.data.model.ForgotResponse
import com.example.shopbook.ui.auth.signin.SignInFragment
import com.example.shopbook.data.api.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ForgotPasswordFragment : Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var sendButton: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)

        emailEditText = view.findViewById(R.id.edittext1)
        sendButton = view.findViewById(R.id.bt1)
        sendButton.setOnClickListener {
            val email = emailEditText.text.toString()
            if (email.isNotEmpty()) {
                performForgot(email)
            } else {
                Toast.makeText(requireContext(), "Please enter email", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
    private fun performForgot(email : String) {
        lifecycleScope.launch {
            try {
                val response : Response<ForgotResponse> = RetrofitClient.apiService.forgot(email)
                if (response.isSuccessful) {
                    val forgotResponse: ForgotResponse? = response.body()
                    Log.d("tung", forgotResponse.toString())
                    if (forgotResponse != null) {
                        navigateToSignInScreen()
                    }
                } else {
                    val a = "loi"
                    Log.d("tung", a)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "failed", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                val a = "sai"
                Log.d("tung", a)
            }
        }
    }
    private fun navigateToSignInScreen() {
        val fragment = SignInFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack("forgotFragment")
        transaction.commit()
    }
}