package com.example.shopbook.ui.productdetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.shopbook.R
import com.example.shopbook.databinding.FragmentMainMenuBinding
import com.example.shopbook.databinding.FragmentProductDetailBinding
import com.example.shopbook.ui.author.AuthorFragment
import com.example.shopbook.ui.main.search.SearchFragment
import com.example.shopbook.ui.productdetail.viewmodel.ProductdetailViewModel
import com.example.shopbook.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProductdetailFragment : Fragment() {
    private var binding: FragmentProductDetailBinding? = null

    private lateinit var viewModel: ProductdetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProductDetailBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductdetailViewModel::class.java)
        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.visibility = View.GONE

        val newText = "Nguyễn Nhật Ánh";
        val content = SpannableString(newText);
        content.setSpan(UnderlineSpan(), 0, newText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        val underlineColor = getResources().getColor(R.color.colorAuth)
        content.setSpan(
            ForegroundColorSpan(underlineColor),
            0,
            newText.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding?.textNameAuthor?.setText(content);
        var scale = getResources().getDisplayMetrics().density;
        val layoutParams = binding?.imagePro?.getLayoutParams();
        binding?.imagePro?.setLayoutParams(layoutParams);
        var check = true
        binding?.readmore?.setOnClickListener {
            if (check) {
                layoutParams?.height = (195 * scale + 0.5f).toInt()
                binding?.imagePro?.setLayoutParams(layoutParams);
                var newMaxLines = Integer.MAX_VALUE;
                binding?.textDescription?.setMaxLines(newMaxLines);
                check = false;
                binding?.readmore?.setText("Collapse.");
            } else {
                layoutParams?.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                binding?.imagePro?.setLayoutParams(layoutParams);
                val newMaxLines = 2;
                binding?.textDescription?.setMaxLines(newMaxLines);
                binding?.readmore?.setText("Read more.");
                check = true;
            }
        }
        binding?.imageLeft?.setOnClickListener{
            parentFragmentManager.popBackStack()
        }
        binding?.imageAccount?.setOnClickListener{
            val profileFragment=ProfileFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, profileFragment)
                .addToBackStack("productFragment")
                .commit()
        }
        binding?.textNameAuthor?.setOnClickListener{
            val authorFragment=AuthorFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, authorFragment)
                .addToBackStack("productFragment")
                .commit()
        }
    }
}