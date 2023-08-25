package g54490.mobg5.sharestudent.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        savedInstanceState: Bundle?): View {

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_publication_detail_for_profil, container, false)
        val viewModelFactory = DetailForProfilViewModelFactory()
        val detailViewModel = ViewModelProvider(this, viewModelFactory)[DetailForProfilViewModel::class.java]
        binding.viewModel=detailViewModel
        binding.lifecycleOwner = this

        var connectionIssue=false
        detailViewModel.onFailureDeleteElementById.observe(viewLifecycleOwner) {
            connectionIssue=true
        }

        detailViewModel.canDelete.observe(viewLifecycleOwner){
            if (it==true){
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle(getString(R.string.confirmationTitle))
                builder.setMessage(getString(R.string.deleteMessage))
                builder.setPositiveButton(getString(R.string.yes)) { dialog, which ->
                    detailViewModel.deletePublication()
                    if (connectionIssue){
                        context?.let { Toast.makeText(it, getString(R.string.onFailureDeleteElementById), Toast.LENGTH_SHORT).show()}
                        connectionIssue=false
                    }else{
                        detailViewModel.updatePublicationList() //mise à jour des données après la suppression
                        findNavController().navigateUp()
                    }
                }
                builder.setNegativeButton(getString(R.string.no)) { dialog, which ->
                    context?.let { Toast.makeText(it, getString(R.string.noDelete), Toast.LENGTH_SHORT).show()}
                }
                val dialog = builder.create()
                dialog.show()
            }
        }
        return binding.root
    }
}