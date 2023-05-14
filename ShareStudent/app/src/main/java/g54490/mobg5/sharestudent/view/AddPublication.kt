package g54490.mobg5.sharestudent.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.databinding.FragmentAddPublicationBinding
import g54490.mobg5.sharestudent.viewmodel.AddViewModel
import g54490.mobg5.sharestudent.viewmodel.AddViewModelFactory



class AddPublication : Fragment() {
    private lateinit var addViewModel: AddViewModel
    private lateinit var binding:FragmentAddPublicationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= DataBindingUtil.inflate<g54490.mobg5.sharestudent.databinding.FragmentAddPublicationBinding>(inflater, R.layout.fragment_add_publication, container, false)

        val addViewModelFactory= AddViewModelFactory()
        addViewModel= ViewModelProvider(this,addViewModelFactory)[AddViewModel::class.java]
        binding.addViewModel=addViewModel

        addViewModel.publishButton.observe(viewLifecycleOwner, Observer {
            if (it==true){


                //this.findNavController().navigate(AddPublicationDirections.actionAddPublication2ToHome2())
            }
        })

        addViewModel.takePicture.observe(viewLifecycleOwner, Observer {
            if (it==true){
                if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA),100)
                }
                var intent =Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent,100)
                addViewModel.setTakePicture()
            }
        })
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==100){
            var picture:Bitmap?=data?.getParcelableExtra<Bitmap>("data")
            binding.imageView5.setImageBitmap(picture)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==100 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

        }
    }

}