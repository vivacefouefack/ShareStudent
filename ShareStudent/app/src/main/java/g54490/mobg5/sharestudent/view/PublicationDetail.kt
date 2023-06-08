package g54490.mobg5.sharestudent.view

import android.annotation.SuppressLint
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


    @SuppressLint("IntentReset")
    private fun sendEmail(recipient: String, subject: String) {
        val mIntent = Intent(Intent.ACTION_SEND)
        val message="je suis interessé par  la publication,est ce qu'elle est toujours disponible?"
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)
        try {
            startActivity(Intent.createChooser(mIntent, "choisir la boite emaile"))
        }
        catch (e: Exception){
            Toast.makeText(this.activity, "envoyé", Toast.LENGTH_LONG).show()
        }

    }
}