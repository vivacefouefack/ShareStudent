package g54490.mobg5.sharestudent.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import g54490.mobg5.sharestudent.R

class AboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }
}