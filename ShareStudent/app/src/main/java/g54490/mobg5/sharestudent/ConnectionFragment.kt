package g54490.mobg5.sharestudent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import g54490.mobg5.sharestudent.databinding.FragmentConnectionBinding

class ConnectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentConnectionBinding>(inflater,
            R.layout.fragment_connection,container,false)
        return binding.root
    }
}