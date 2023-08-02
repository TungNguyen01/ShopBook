package com.example.shopbook.ui.profile.updateprofile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.shopbook.R
import com.example.shopbook.data.model.Customer
import com.example.shopbook.databinding.FragmentUpdateProfileBinding
import com.example.shopbook.ui.profile.permission.PermissionFragment
import com.example.shopbook.utils.MySharedPreferences
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream

class UpdateProfileFragment : Fragment() {

    companion object {
        fun newInstance() = UpdateProfileFragment()
    }

    private var binding: FragmentUpdateProfileBinding? = null
    private lateinit var viewModel: UpdateProfileViewModel
    private var customer_id: Int? = null
    private var avatar: String? = null
    private var shipping_region_id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentUpdateProfileBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpdateProfileViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.layoutLoading?.root?.visibility = View.VISIBLE
        viewModel.getCustomer()
        observeProfile()

        activity?.let { MySharedPreferences.init(it.applicationContext) }
        binding?.apply {
            cardview.setOnClickListener {
                if (context?.checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(arrayOf(Manifest.permission.READ_MEDIA_IMAGES), 1)
                } else {
                    openImageDirectory()
//                    binding?.layoutLoading?.root?.visibility = View.VISIBLE
                }

            }
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            textUpdateProfile.setOnClickListener {
                updateProfie()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray,
    ) {
        if (requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImageDirectory()
            } else {
                Toast.makeText(context, "User ko cap quyen", Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun openImageDirectory() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*" // Loại tệp tin là ảnh
        }
        startActivityForResult(intent, 1)
//        binding?.layoutLoading?.root?.visibility = View.INVISIBLE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            val picturePath = uri?.let { uriToFilePath(it) }
            Log.d("URIPATH", picturePath.toString())
            val file = File(picturePath)
            val requestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
            val multiPart = MultipartBody.Part.createFormData("image", file.name, requestBody)
            viewModel.changeAvatar(multiPart)
            MySharedPreferences.putString("imageAvatar", picturePath.toString())
            binding?.imageAvatar?.setImageURI(uri)
//            viewModel.profile.observe(viewLifecycleOwner, Observer {
//                binding?.apply {
//                    Glide.with(root)
//                        .load(it.avatar)
//                        .centerCrop()
//                        .into(imageAvatar)
//                }
//            })
        }
    }

    private fun observeProfile() {
        viewModel.profile.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                bindData(it)
                customer_id = it.customer_id
                shipping_region_id = it.shipping_region_id
                avatar = it.avatar
            }
        })
    }

    private fun bindData(profile: Customer) {
        binding?.apply {
            Glide.with(root)
                .load(profile.avatar)
                .centerCrop()
                .into(imageAvatar)
            editFullname.setText(profile.name)
            editDob.setText(profile.date_of_birth)
            editPhone.setText(profile.mob_phone)
            editAddress.setText(profile.address)
            editEmail.setText(profile.email)
            if (profile.gender.equals("Nam")) {
                radiobtnNam.isChecked = true
            } else {
                radiobtnNu.isChecked = true
            }
            layoutLoading.root.visibility = View.INVISIBLE
        }
    }

    private fun updateProfie() {
        binding?.apply {
            val fullName = editFullname.text.toString()
            val Dob = editDob.text.toString()
            var gender = "Nữ"
            if (radiobtnNam.isChecked) {
                gender = "Nam"
            }
            val phone = editPhone.text.toString()
            val address = editAddress.text.toString()
            viewModel.updateCustomer(fullName, address, Dob, gender, phone)
        }
    }

    private fun uriToFilePath(uri: Uri): String? {
        val inputStream = context?.contentResolver?.openInputStream(uri)
        inputStream?.use { inputStream ->
            val outputFile = createTempImageFile()
            val outputStream = FileOutputStream(outputFile)
            outputStream.use { outputStream ->
                val buffer = ByteArray(4 * 1024) //4KB
                var bytesRead: Int
                while (inputStream.read(buffer).also { bytesRead = it } >= 0) {
                    outputStream.write(buffer, 0, bytesRead)
                }
                return outputFile.absolutePath
            }
        }
        return null
    }

    private fun createTempImageFile(): File {
        val tempFileName = "temp_image_${System.currentTimeMillis()}.jpg"
        val storageDir = context?.getExternalFilesDir(null)
        return File.createTempFile(tempFileName, ".jpg", storageDir)
    }
}