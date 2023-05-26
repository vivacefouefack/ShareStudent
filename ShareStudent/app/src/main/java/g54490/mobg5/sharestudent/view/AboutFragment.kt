package g54490.mobg5.sharestudent.view

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.databinding.FragmentAboutBinding
import g54490.mobg5.sharestudent.databinding.FragmentPublicationDetailBinding

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= DataBindingUtil.inflate<g54490.mobg5.sharestudent.databinding.FragmentAboutBinding>(inflater, R.layout.fragment_about, container, false)
        return binding.root
    }
}