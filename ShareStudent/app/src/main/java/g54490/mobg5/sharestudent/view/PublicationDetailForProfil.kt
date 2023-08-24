package g54490.mobg5.sharestudent.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.databinding.FragmentPublicationDetailForProfilBinding
import g54490.mobg5.sharestudent.viewmodel.DetailForProfilViewModel
import g54490.mobg5.sharestudent.viewmodel.DetailForProfilViewModelFactory

class PublicationDetailForProfil : Fragment() {
    private lateinit var binding: FragmentPublicationDetailForProfilBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_publication_detail_for_profil, container, false)
        val viewModelFactory = DetailForProfilViewModelFactory()
        val detailViewModel = ViewModelProvider(this, viewModelFactory)[DetailForProfilViewModel::class.java]
        binding.viewModel=detailViewModel
        binding.lifecycleOwner = this

        detailViewModel.canDelete.observe(viewLifecycleOwner){
            if (it==true){
                detailViewModel.deletePublication()
                this.findNavController().navigate(PublicationDetailForProfilDirections.actionPublicationDetailForProfilToProfileFragment())
            }
        }

        return binding.root
    }


}