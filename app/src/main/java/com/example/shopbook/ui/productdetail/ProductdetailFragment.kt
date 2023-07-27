package com.example.shopbook.ui.productdetail

import android.annotation.SuppressLint
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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
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
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductdetailViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.visibility = View.GONE

        val newText = "Nguyễn Nhật Ánh";
        val content = SpannableString(newText);
        content.setSpan(UnderlineSpan(), 0, newText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        val underlineColor = resources.getColor(R.color.colorAuth)
        content.setSpan(
            ForegroundColorSpan(underlineColor),
            0,
            newText.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
//        var scale = resources.displayMetrics.density;
//        val layoutParams = binding?.imagePro?.layoutParams;
        var check = true
        binding?.apply {
            textNameAuthor.text = content
//            imagePro.layoutParams = layoutParams
            val layoutParams = constraintImageProduct.layoutParams as ConstraintLayout.LayoutParams
            readmore.setOnClickListener {
                if (check) {
                    layoutParams.dimensionRatio = "12:7" // Replace "16:9" with your desired aspect ratio
                    constraintImageProduct.layoutParams = layoutParams
//                    layoutParams?.height = (195 * scale + 0.5f).toInt()
//                    imagePro.layoutParams = layoutParams
                    val newMaxLines = Integer.MAX_VALUE
                    textDescription.setMaxLines(newMaxLines)
                    check = false
                    readmore.text = "Collapse."
                } else {
                    layoutParams.dimensionRatio = "6:4" // Replace "16:9" with your desired aspect ratio
                    constraintImageProduct.layoutParams = layoutParams
//                    layoutParams?.height = ViewGroup.LayoutParams.WRAP_CONTENT
//                    imagePro.layoutParams = layoutParams
                    val newMaxLines = 2
                    textDescription.setMaxLines(newMaxLines)
                    readmore.text = "Read more."
                    check = true
                }
            }
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            imageAccount.setOnClickListener {
                val profileFragment = ProfileFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, profileFragment)
                    .addToBackStack("productFragment")
                    .commit()
            }
            textNameAuthor.setOnClickListener {
                val authorFragment = AuthorFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, authorFragment)
                    .addToBackStack("productFragment")
                    .commit()
            }
            Glide.with(root)
                .load("https://cdn0.fahasa.com/media/catalog/product/8/9/8935280913738-dd.jpg")
                .centerCrop()
//                .placeholder(R.drawable.placeholder_image)
//                .error(R.drawable.error_image)
                .into(imagePro)
        }
    }
}