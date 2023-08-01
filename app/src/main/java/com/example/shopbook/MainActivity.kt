package com.example.shopbook

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.shopbook.databinding.ActivityMainBinding
import com.example.shopbook.ui.auth.signin.SignInFragment
import com.example.shopbook.ui.onboarding.OnboardingFragment


class MainActivity : AppCompatActivity() {
    private lateinit var bnd: ActivityMainBinding
    private lateinit var pref: SharedPreferences
   // private var accessToken: String? = "a"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bnd = ActivityMainBinding.inflate(layoutInflater)
        val view: View = bnd.getRoot()
        setContentView(view)
        pref = getSharedPreferences("myPreference", MODE_PRIVATE)
        val support = supportFragmentManager.beginTransaction()
        if (isFirstLaunch()) {
            val fragmentOnboard = OnboardingFragment()
            support.replace(R.id.container, fragmentOnboard).commit()
            setFirstLaunch(false)
        } else {
            val fragmentSignin = SignInFragment()
            support.replace(R.id.container, fragmentSignin).commit()
        }
    }
    private fun isFirstLaunch(): Boolean {
        return pref.getBoolean("first_launch", true)
    }

    private fun setFirstLaunch(isFirstTime: Boolean) {
        var editor: SharedPreferences.Editor = pref.edit()
        editor.putBoolean("first_launch", isFirstTime)
        editor.apply()
    }


}