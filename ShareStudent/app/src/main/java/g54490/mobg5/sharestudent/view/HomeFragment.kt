package g54490.mobg5.sharestudent.view

import android.os.Bundle
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
import g54490.mobg5.sharestudent.viewmodel.HomeViewModel
import g54490.mobg5.sharestudent.viewmodel.HomeViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val binding= DataBindingUtil.inflate<g54490.mobg5.sharestudent.databinding.FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)
        val homeViewModelFactory=HomeViewModelFactory()
        homeViewModel=ViewModelProvider(this,homeViewModelFactory)[HomeViewModel::class.java]

        val adapter = PublicationAdapter(PublicationListener { publicationId ->
            homeViewModel.onPublicationClicked(publicationId)
        })

        binding.publicationList.adapter= adapter
        binding.lifecycleOwner = this
        binding.homeViewModel=homeViewModel

        homeViewModel.allPublication.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        homeViewModel.addPublication.observe(viewLifecycleOwner, Observer {
            if (it==true){
                this.findNavController().navigate(HomeFragmentDirections.actionHome2ToAddPublication2())
                homeViewModel.setAddButton()
            }
        })

        homeViewModel.navigateToPublicationDetail.observe(viewLifecycleOwner, Observer { publication->
                publication?.let {
                    this.findNavController().navigate(HomeFragmentDirections.actionHome2ToPublicationDetail())
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

