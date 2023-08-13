package g54490.mobg5.sharestudent.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import g54490.mobg5.sharestudent.model.Publication
import g54490.mobg5.sharestudent.model.Repository

class HomeViewModel:ViewModel() {

    private var _allPublication = MutableLiveData<List<Publication>>()
    val allPublication: LiveData<List<Publication>>
        get() = _allPublication

    private var _addPublication = MutableLiveData<Boolean?>()
    val addPublication: LiveData<Boolean?>
        get() = _addPublication

    private var _canNavigate = MutableLiveData<Boolean?>()
    val canNavigate: LiveData<Boolean?>
        get() = _canNavigate

    private val _navigateToPublicationDetail = MutableLiveData<String>()
    val navigateToPublicationDetail
        get() = _navigateToPublicationDetail

    fun onPublicationClicked(id: String) {
        _navigateToPublicationDetail.value = id
    }

   init {
       // //FIXME (QHB) :refactor this. Fragment should observe LiveData from ViewModel assigned to LiveData in Repository
       Log.d("homeViewModel","init")
       Repository.readData(object:Repository.FirebaseSuccessListener{
           override fun onSuccess(publications: MutableList<Publication>){
               _allPublication.value=publications
               Log.d("homeViewModel", (_allPublication.value as MutableList<Publication>).size.toString())
               _addPublication.value=null
               _canNavigate.value=null
           }

           override fun onError(){
               Log.d("homeViewModel","erreur")
           }
       })

   }

    fun setAddButton(){
        _addPublication.value=false
    }

    fun goToAddPublicationUi(){
        _addPublication.value=true
    }
}