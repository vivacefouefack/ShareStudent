package g54490.mobg5.sharestudent.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import g54490.mobg5.sharestudent.model.Publication
import g54490.mobg5.sharestudent.model.Repository

class HomeViewModel:ViewModel() {

    private val _allPublication = MutableLiveData<List<Publication>>()
    val allPublication: LiveData<List<Publication>> = _allPublication

    private var _addPublication = MutableLiveData<Boolean?>()
    val addPublication: LiveData<Boolean?>
        get() = _addPublication

    private var _canNavigate = MutableLiveData<Boolean?>()
    val canNavigate: LiveData<Boolean?>
        get() = _canNavigate

    private val _navigateToPublicationDetail = MutableLiveData<String>()
    val navigateToPublicationDetail
        get() = _navigateToPublicationDetail


    init {
        loadPublicationsFromFirebase()
        Repository.publicationList.observeForever{
            _allPublication.postValue(it)
        }
        _addPublication.value=null
        _canNavigate.value=null
    }

    fun onPublicationClicked(id: String) {
        _navigateToPublicationDetail.value = id
    }

    fun loadPublicationsFromFirebase(){
        Repository.readData()
    }

    fun setAddButton(){
        _addPublication.value=false
    }

    fun goToAddPublicationUi(){
        _addPublication.value=true
    }
}