package com.example.shopbook.ui.category.categoryindex

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopbook.R
import com.example.shopbook.databinding.FragmentCategoryIndexBinding
import com.example.shopbook.ui.adapter.CategoryIndexAdapter
import com.example.shopbook.ui.adapter.OnItemClickListener
import com.example.shopbook.ui.category.CategoryBookFragment
import com.example.shopbook.utils.MySpanLookSize
import com.google.android.material.bottomnavigation.BottomNavigationView

class CategoryIndexFragment : Fragment() {

    private lateinit var binding: FragmentCategoryIndexBinding
    private lateinit var adapter: CategoryIndexAdapter
//    private var widthRecycler = 0

    companion object {
        fun newInstance() = CategoryIndexFragment()
    }

    private lateinit var viewModel: CategoryIndexViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CategoryIndexViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCategoryIndexBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.visibility = View.GONE
        adapter = CategoryIndexAdapter()
        val layoutManager = GridLayoutManager(context, 2)
        viewModel.categoryList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.setData(it)
            }
            binding.loadingLayout.root.visibility = View.INVISIBLE
        })
        viewModel.getAllCategory()
        navToProductInCategory()
//        binding.recyclerCategory.viewTreeObserver.addOnGlobalLayoutListener(object :
//            ViewTreeObserver.OnGlobalLayoutListener {
//            override fun onGlobalLayout() {
//                widthRecycler = binding.recyclerCategory.width
//                Log.d("WIDTHH", widthRecycler.toString())
//                binding.recyclerCategory.viewTreeObserver.removeOnGlobalLayoutListener(this)
//            }
//        })
        val widthRecycler=binding.recyclerCategory.width
        Log.d("WIDTH", widthRecycler.toString())
        layoutManager.spanSizeLookup = MySpanLookSize(adapter, 1, 2)
        binding.recyclerCategory.adapter = adapter
        binding.recyclerCategory.layoutManager = layoutManager
        binding.imageLeft.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun navToProductInCategory() {
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val bundle = Bundle()
                val categoryId = adapter.getCategory(position).categoryId
                bundle.putString("categoryId", categoryId.toString())
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, CategoryBookFragment().apply { arguments = bundle })
                    .addToBackStack("CategoryIndex")
                    .commit()
            }

        })
    }
}