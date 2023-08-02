package com.example.shopbook.ui.profile.changepass

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.shopbook.R
import com.example.shopbook.databinding.FragmentChangePassBinding

class ChangePassFragment : Fragment() {

    companion object {
        fun newInstance() = ChangePassFragment()
    }

    private lateinit var viewModel: ChangePassViewModel
    private var binding: FragmentChangePassBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentChangePassBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChangePassViewModel::class.java)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.imageLeft?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding?.apply {
            textUpdatePassword.setOnClickListener {
                val email = arguments?.getString("email").toString()
                val oldPass = editCurrentPass.text.toString()
                val newPass = editNewPass.text.toString()
                val confirmPass = editConfirm.text.toString()

//                when{
//                     -> Toast.makeText(context, "Mật khẩu lớn hơn =5 kí tự")
//                    new_pass==confirm_pass->viewModel.changePassword(email, old_pass, new_pass)
//                }
                if (newPass == confirmPass) {
                    if(oldPass.length<5 || newPass.length<5){
                        Toast.makeText(context, "Mật khẩu lớn hơn =5 kí tự", Toast.LENGTH_SHORT).show()
                    }else{
                        viewModel.changePassword(email, oldPass, newPass)
                        viewModel.message.observe(viewLifecycleOwner, Observer {
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        })
                    }
                }
                else {
                    Toast.makeText(context, "Confirm failure", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
