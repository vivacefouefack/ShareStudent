package g54490.mobg5.sharestudent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import g54490.mobg5.sharestudent.model.Publication
import g54490.mobg5.sharestudent.model.Repository

class ProfileViewModel: ViewModel(){
    val login: String
        get() = Repository.getUsername()

    private val _myNbPublication = MutableLiveData<String>()
    val myNbPublication: LiveData<String> = _myNbPublication

    private val _myPublicationList = MutableLiveData<List<Publication>>()
    val myPublicationList: LiveData<List<Publication>> = _myPublicationList

    private val _navigateToPublicationDetail = MutableLiveData<String>()
    val navigateToPublicationDetail
        get() = _navigateToPublicationDetail

    fun onPublicationClicked(id: String) {
        _navigateToPublicationDetail.value = id
    }

    init {
        loadPublicationsFromFirebase()
        Repository.myPublicationLists.observeForever{
            _myNbPublication.value=it.size.toString()
            _myPublicationList.postValue(it)
        }
    }
    fun loadPublicationsFromFirebase(){
        Repository.readData()
    }

    fun findPublicationById(id: String) {
        Repository.getPublicationWithId(id)
    }
}