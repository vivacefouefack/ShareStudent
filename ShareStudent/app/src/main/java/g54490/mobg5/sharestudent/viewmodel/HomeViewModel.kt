package g54490.mobg5.sharestudent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import g54490.mobg5.sharestudent.model.Publication
import g54490.mobg5.sharestudent.model.Repository

class HomeViewModel:ViewModel() {

    private val _allPublication = MutableLiveData<List<Publication>>()
    val allPublication: LiveData<List<Publication>> = _allPublication

    private val _onFailureReadData=MutableLiveData<Boolean>()
    val onFailureReadData: LiveData<Boolean> =_onFailureReadData

    private var _addPublication = MutableLiveData<Boolean>()
    val addPublication: LiveData<Boolean>
        get() = _addPublication

    private val _navigateToPublicationDetail = MutableLiveData<String>()
    val navigateToPublicationDetail
        get() = _navigateToPublicationDetail



    init {
        loadPublicationsFromFirebase()
        Repository.publicationList.observeForever{
            _allPublication.postValue(it)
        }
        Repository.onFailureReadData.observeForever {
            _onFailureReadData.postValue(true)
        }
    }

    fun onPublicationClicked(id: String) {
        _navigateToPublicationDetail.value = id
    }

    private fun loadPublicationsFromFirebase(){
        Repository.readData()
    }

    fun setAddButton(){
        _addPublication.value=false
    }

    fun goToAddPublicationUi(){
        _addPublication.value=true
    }

    fun findPublicationById(id: String) {
        Repository.getPublicationWithId(id)
    }
}