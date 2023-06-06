package g54490.mobg5.sharestudent.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.databinding.FragmentLogoutBinding
import g54490.mobg5.sharestudent.model.Repository

class LogoutFragment : Fragment() {
    private lateinit var binding: FragmentLogoutBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding= DataBindingUtil.inflate<g54490.mobg5.sharestudent.databinding.FragmentLogoutBinding>(inflater, R.layout.fragment_logout, container, false)
        binding.userName.text=Repository.getUsername()
        binding.logoutButton.setOnClickListener {
            Repository.getAuth().signOut()
            requireActivity().run {
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
        }
        return binding.root
    }

}