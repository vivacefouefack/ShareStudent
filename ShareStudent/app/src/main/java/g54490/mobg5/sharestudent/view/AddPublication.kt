package g54490.mobg5.sharestudent.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.viewmodel.AddViewModel
import g54490.mobg5.sharestudent.viewmodel.AddViewModelFactory
import g54490.mobg5.sharestudent.viewmodel.LoginViewModel
import g54490.mobg5.sharestudent.viewmodel.LoginViewModelFactory

class AddPublication : Fragment() {
    private lateinit var addViewModel: AddViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding= DataBindingUtil.inflate<g54490.mobg5.sharestudent.databinding.FragmentAddPublicationBinding>(inflater, R.layout.fragment_add_publication, container, false)

        val addViewModelFactory= AddViewModelFactory()
        addViewModel= ViewModelProvider(this,addViewModelFactory)[AddViewModel::class.java]

        binding.addViewModel=addViewModel

        addViewModel.publishButton.observe(viewLifecycleOwner, Observer {
            if (it==true){
                this.findNavController().navigate(AddPublicationDirections.actionAddPublication2ToHome2())
            }
        })

        return binding.root
    }

}