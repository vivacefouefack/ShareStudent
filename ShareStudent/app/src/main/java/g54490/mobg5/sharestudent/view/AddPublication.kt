package g54490.mobg5.sharestudent.view

import android.Manifest
import android.app.Activity.RESULT_OK
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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.databinding.FragmentAddPublicationBinding
import g54490.mobg5.sharestudent.model.Repository
import g54490.mobg5.sharestudent.viewmodel.AddViewModel
import g54490.mobg5.sharestudent.viewmodel.AddViewModelFactory

class AddPublication : Fragment() {
    private lateinit var addViewModel: AddViewModel
    private lateinit var binding:FragmentAddPublicationBinding
    private var imageUri: Uri=Uri.EMPTY
    private val code=100
    private val galleryCode=200

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_add_publication, container, false)

        val addViewModelFactory= AddViewModelFactory(requireContext())
        addViewModel= ViewModelProvider(this,addViewModelFactory)[AddViewModel::class.java]
        binding.addViewModel=addViewModel

        var connectionIssue=false
        addViewModel.onFailureCreatePublication.observe(viewLifecycleOwner) {
            if(it==true){
                connectionIssue=true
            }
        }

        addViewModel.publishPress.observe(viewLifecycleOwner) {
            if (it == true) {
                if (connectionIssue){
                    Toast.makeText(requireContext(), getString(R.string.onFailureCreatePublication), Toast.LENGTH_LONG).show()
                    connectionIssue=false
                }else{
                    Repository.getStorage().getReference("images").child(addViewModel.getImageName())
                        .putFile(imageUri).addOnSuccessListener {
                            binding.imageView5.setImageURI(Uri.parse(""))
                            this.findNavController().navigate(AddPublicationDirections.actionAddPublication2ToHome2())
                        }.addOnFailureListener {
                            Toast.makeText(requireContext(), "Fail to Upload Image..", Toast.LENGTH_SHORT).show()
                        }

                }
            }
            if (it == false) {
                binding.titre.error = getString(R.string.invalid)
                binding.description.error = getString(R.string.invalid)
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
                        arrayOf(Manifest.permission.CAMERA), 100
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
            val picture:Bitmap?=data?.getParcelableExtra(getString(R.string.dataName))
            if (picture != null) {
                addViewModel.saveMediaToStorage(picture)
            }
            imageUri = addViewModel.captureImageUri
            binding.imageView5.setImageBitmap(picture)
        }
    }

    override fun  onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}