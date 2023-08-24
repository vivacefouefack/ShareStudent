package g54490.mobg5.sharestudent.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.databinding.FragmentPublicationDetailBinding
import g54490.mobg5.sharestudent.viewmodel.PublicationViewModel
import g54490.mobg5.sharestudent.viewmodel.PublicationViewModelFactory

class PublicationDetail : Fragment() {
    private lateinit var binding:FragmentPublicationDetailBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View{

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_publication_detail, container, false)
        val viewModelFactory = PublicationViewModelFactory()
        val publicationDetailViewModel = ViewModelProvider(this, viewModelFactory)[PublicationViewModel::class.java]
        binding.viewModel=publicationDetailViewModel
        binding.lifecycleOwner = this
        publicationDetailViewModel.writeToAuthor.observe(viewLifecycleOwner) {
            if (it == true) {
                sendEmail(
                    publicationDetailViewModel.publication.author,
                    publicationDetailViewModel.publication.title,
                )
            }
        }
        return binding.root
    }

    private fun sendEmail(recipient: String, subject: String) {
        val mIntent = Intent(Intent.ACTION_SEND)
        val message=getString(R.string.message)
        mIntent.data = Uri.parse(getString(R.string.mailto))
        mIntent.type = getString(R.string.type)
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)
        mIntent.setPackage(getString(R.string.google))
        try {
            startActivity(mIntent)
        }
        catch (e: Exception){
            Toast.makeText(this.activity, getString(R.string.errorToSend), Toast.LENGTH_LONG).show()
        }
    }
}