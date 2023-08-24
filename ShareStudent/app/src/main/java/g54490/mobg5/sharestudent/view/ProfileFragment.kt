package g54490.mobg5.sharestudent.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.databinding.FragmentProfileBinding
import g54490.mobg5.sharestudent.model.Repository
import g54490.mobg5.sharestudent.viewmodel.ProfileViewModel
import g54490.mobg5.sharestudent.viewmodel.ProfileViewModelFactory

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View{

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        val profileViewModelFactory= ProfileViewModelFactory()
        profileViewModel= ViewModelProvider(this,profileViewModelFactory)[ProfileViewModel::class.java]
        binding.profileViewModel=profileViewModel

        val adapter = PublicationAdapter(PublicationListener { publicationId ->
            profileViewModel.onPublicationClicked(publicationId)
        })
        binding.mesPub.adapter=adapter

        profileViewModel.myPublicationList.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        profileViewModel.navigateToPublicationDetail.observe(viewLifecycleOwner) { publication ->
            if (publication.isNotEmpty()) {
                publication?.let {
                    Repository.getPublicationWithId(it)
                    this.findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToPublicationDetailForProfil())
                    profileViewModel.onPublicationClicked("")
                }
            }
        }

        profileViewModel.myNbPublication.observe(viewLifecycleOwner){
            binding.nb.text=it
        }

        val manager = GridLayoutManager(activity, 2)
        binding.mesPub.layoutManager = manager
        return binding.root
    }
}