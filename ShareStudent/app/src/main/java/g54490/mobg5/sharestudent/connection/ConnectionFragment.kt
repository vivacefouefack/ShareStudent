package g54490.mobg5.sharestudent.connection

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.room.Room
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.database.ShareStudentDB
import g54490.mobg5.sharestudent.databinding.FragmentConnectionBinding

class ConnectionFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentConnectionBinding>(inflater,
            R.layout.fragment_connection,container,false)
        val application = requireNotNull(this.activity).application
        val database = Room.databaseBuilder(application,ShareStudentDB::class.java, "Share_Student_DB").allowMainThreadQueries().build().userDao()
        val viewModelFactory = ConnectionViewModelFactory(application,database)
        val connectionViewModel= ViewModelProvider(this,viewModelFactory)[ConnectionViewModel::class.java]

        binding.connectionViewModel=connectionViewModel

        connectionViewModel.emailAdress.observe(viewLifecycleOwner) { email ->
            connectionViewModel.saveEmailadress(binding.EmailAddress.text)
        }

        binding.buttonConnect.setOnClickListener {
            if(connectionViewModel.isValidEmail(binding.EmailAddress.text)){
                connectionViewModel.saveEmailadress(binding.EmailAddress.text)
                if (connectionViewModel.isNewUser){
                    Toast.makeText(context, getString(R.string.insertToastText), Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, getString(R.string.updateToastText), Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context, getString(R.string.invalideToastText), Toast.LENGTH_SHORT).show()
            }
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}