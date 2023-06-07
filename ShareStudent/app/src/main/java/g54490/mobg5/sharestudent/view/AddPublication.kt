package g54490.mobg5.sharestudent.view

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.databinding.FragmentAddPublicationBinding
import g54490.mobg5.sharestudent.model.Repository
import g54490.mobg5.sharestudent.viewmodel.AddViewModel
import g54490.mobg5.sharestudent.viewmodel.AddViewModelFactory
import java.text.SimpleDateFormat
import java.util.*


class AddPublication : Fragment() {
    private lateinit var addViewModel: AddViewModel
    private lateinit var binding:FragmentAddPublicationBinding
    private lateinit var imagePath:String
    private lateinit var imageUri: Uri
    val code=100
    val galleryCode=200

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= DataBindingUtil.inflate<g54490.mobg5.sharestudent.databinding.FragmentAddPublicationBinding>(inflater, R.layout.fragment_add_publication, container, false)

        val addViewModelFactory= AddViewModelFactory()
        addViewModel= ViewModelProvider(this,addViewModelFactory)[AddViewModel::class.java]
        binding.addViewModel=addViewModel

        addViewModel.publishPress.observe(viewLifecycleOwner, Observer {
            if (it==true){
                if (imageUri != null) {
                    imageUri.queryParameterNames
                    val progressDialog = ProgressDialog(requireContext())
                    progressDialog.setTitle("Uploading...")
                    progressDialog.setMessage("Uploading your image")
                    progressDialog.show()
                    Repository.getStorage().getReference("images").child(addViewModel.getImageName())
                        .putFile(imageUri).addOnSuccessListener {
                            progressDialog.dismiss()
                            Toast.makeText(requireContext(), "image Uploaded", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener{
                            progressDialog.dismiss()
                            Toast.makeText(requireContext(), "Fail to Upload Image..", Toast.LENGTH_SHORT).show()
                        }
                }
                Repository.readData()
                //this.findNavController().navigate(AddPublicationDirections.actionAddPublication2ToHome2())
            }
            if(it==false){
                binding.titre.error="invalid"
                binding.description.error="invalid"
            }
        })



        addViewModel.takePicture.observe(viewLifecycleOwner, Observer {

        })

        addViewModel.galleryPress.observe(viewLifecycleOwner, Observer {
            if (it==true){
                val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, galleryCode)
            }
        })

        addViewModel.cameraPress.observe(viewLifecycleOwner, Observer {
            if (it==true){
                if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA),100)
                }
                var intent =Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent,code)
                addViewModel.setTakePicture()
            }
        })
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode==galleryCode){
            imageUri = data?.data!!
            binding.imageView5.setImageURI(imageUri)
        }

        if (requestCode==code){
            var picture:Bitmap?=data?.getParcelableExtra<Bitmap>("data")
            //Repository.getStorage().getReference("images").child(System.currentTimeMillis().toString())
            //.putFile(Uri.parse(picture.))
            binding.imageView5.setImageBitmap(picture)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==code && grantResults[0]==PackageManager.PERMISSION_GRANTED){

        }
    }

}