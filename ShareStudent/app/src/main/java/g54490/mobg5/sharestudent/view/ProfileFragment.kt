package g54490.mobg5.sharestudent.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.databinding.FragmentProfileBinding
import g54490.mobg5.sharestudent.viewmodel.ProfileViewModel
import g54490.mobg5.sharestudent.viewmodel.ProfileViewModelFactory

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding= DataBindingUtil.inflate<g54490.mobg5.sharestudent.databinding.FragmentProfileBinding>(inflater, R.layout.fragment_profile, container, false)

        val profileViewModelFactory= ProfileViewModelFactory()
        profileViewModel= ViewModelProvider(this,profileViewModelFactory)[ProfileViewModel::class.java]
        binding.profileViewModel=profileViewModel

        return binding.root
    }
}