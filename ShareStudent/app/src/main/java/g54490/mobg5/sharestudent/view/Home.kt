package g54490.mobg5.sharestudent.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.model.Publication
import g54490.mobg5.sharestudent.viewmodel.HomeViewModel
import g54490.mobg5.sharestudent.viewmodel.HomeViewModelFactory
import g54490.mobg5.sharestudent.viewmodel.LoginViewModel
import g54490.mobg5.sharestudent.viewmodel.LoginViewModelFactory

class Home : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val binding= DataBindingUtil.inflate<g54490.mobg5.sharestudent.databinding.FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)

        val adapter = PublicationAdapter()
        //binding.homeViewModel=homeViewModel
        binding.publicationList.adapter= adapter

        val homeViewModelFactory=HomeViewModelFactory()
        homeViewModel=ViewModelProvider(this,homeViewModelFactory)[HomeViewModel::class.java]
        binding.lifecycleOwner = this

        homeViewModel.allPublication.observe(viewLifecycleOwner, Observer {
            Log.i("montag","pas ??????????????????????????????????????? avant")
            it?.let {
                Log.i("montag","???????????????????????????????????????apres")
                adapter.data=it
            }
        })

        homeViewModel.addButton.observe(viewLifecycleOwner, Observer {
            Log.i("montag","pas ****************************** avant")
            if (it==true){
                Log.i("montag","pas ****************************** apres")
                //this.findNavController().navigate(HomeDirections.actionHome2ToAddPublication2())
            }
        })

        val manager = GridLayoutManager(activity, 2)
        binding.publicationList.layoutManager = manager

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

