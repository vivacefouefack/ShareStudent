package g54490.mobg5.sharestudent.view

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.databinding.FragmentAddPublicationBinding
import g54490.mobg5.sharestudent.model.Repository
import g54490.mobg5.sharestudent.viewmodel.AddViewModel
import g54490.mobg5.sharestudent.viewmodel.AddViewModelFactory
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class AddPublication : Fragment() {
    private lateinit var addViewModel: AddViewModel
    private lateinit var binding:FragmentAddPublicationBinding
    private lateinit var imageUri: Uri
    private lateinit var captureImageUri: Uri
    private val code=100
    private val galleryCode=200

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_add_publication, container, false)

        val addViewModelFactory= AddViewModelFactory()
        addViewModel= ViewModelProvider(this,addViewModelFactory)[AddViewModel::class.java]
        binding.addViewModel=addViewModel

        addViewModel.publishPress.observe(viewLifecycleOwner) {
            if (it == true) {
                val progressDialog = ProgressDialog(requireContext())
                progressDialog.setTitle("Uploading...")
                progressDialog.setMessage("Uploading your image")
                progressDialog.show()
                Repository.getStorage().getReference("images").child(addViewModel.getImageName())
                    .putFile(imageUri).addOnSuccessListener {
                        progressDialog.dismiss()
                        Toast.makeText(requireContext(), "image Uploaded", Toast.LENGTH_SHORT)
                            .show()
                    }.addOnFailureListener {
                        progressDialog.dismiss()
                        Toast.makeText(
                            requireContext(),
                            "Fail to Upload Image..",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                Repository.readData()
            }
            if (it == false) {
                binding.titre.error = "invalid"
                binding.description.error = "invalid"
            }
        }

        addViewModel.galleryPress.observe(viewLifecycleOwner) {
            if (it == true) {
                val gallery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, galleryCode)
            }
        }

        addViewModel.cameraPress.observe(viewLifecycleOwner) {
            if (it == true) {
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.CAMERA),
                        100
                    )
                }
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, code)
            }
        }
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode==galleryCode){
            imageUri = data?.data!!
            binding.imageView5.setImageURI(imageUri)
        }

        if (resultCode == RESULT_OK && requestCode==code){
            val picture:Bitmap?=data?.getParcelableExtra("data")
            saveMediaToStorage(picture!!)
            imageUri = captureImageUri
            binding.imageView5.setImageBitmap(picture)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        /*if (requestCode==code && grantResults[0]==PackageManager.PERMISSION_GRANTED){

        }*/
    }

    private fun saveMediaToStorage(bitmap: Bitmap) {
        addViewModel.setImageName(System.currentTimeMillis().toString()+".jpg")
        val filename=addViewModel.getImageName()
        var fos: OutputStream? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            context?.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                 val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                    captureImageUri=imageUri!!
                fos = imageUri.let { resolver.openOutputStream(it) }
            }
        } else {
            val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }
    }


}