package com.example.shopbook


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.content.Context

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class LoginActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.edittext1)
        passwordEditText = findViewById(R.id.edittext2)
        loginButton = findViewById(R.id.bt1)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                navigateToMainScreen()
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
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
//                        navigateToMainScreen()
//                    }
//                } else {
//                    Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
//                }
//            } catch (e: Exception) {
//                Toast.makeText(this@LoginActivity, "An error occurred", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun saveUserData(accessToken: String, customer: Customer) {
//        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.putString("accessToken", accessToken)
//        editor.putString("customerName", customer.name)
//        editor.apply()
//    }




    private fun navigateToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
