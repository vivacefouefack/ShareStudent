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
import g54490.mobg5.sharestudent.databinding.FragmentPublicationDetailBinding
import g54490.mobg5.sharestudent.viewmodel.PublicationViewModel
import g54490.mobg5.sharestudent.viewmodel.PublicationViewModelFactory

class PublicationDetail : Fragment() {
    private lateinit var binding:FragmentPublicationDetailBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding= DataBindingUtil.inflate<g54490.mobg5.sharestudent.databinding.FragmentPublicationDetailBinding>(inflater, R.layout.fragment_publication_detail, container, false)

        val viewModelFactory = PublicationViewModelFactory()
        val publicationDetailViewModel = ViewModelProvider(this, viewModelFactory)[PublicationViewModel::class.java]

        binding.viewModel=publicationDetailViewModel
        binding.lifecycleOwner = this

        publicationDetailViewModel.navigateToHome.observe(viewLifecycleOwner, Observer {
            if (it==true){
                findNavController().navigate(PublicationDetailDirections.actionPublicationDetailToHome2())
                publicationDetailViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}