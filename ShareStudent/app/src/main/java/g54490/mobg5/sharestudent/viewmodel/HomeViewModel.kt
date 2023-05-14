package g54490.mobg5.sharestudent.viewmodel

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

    private val _navigateToPublicationDetail = MutableLiveData<String>()
    val navigateToPublicationDetail
        get() = _navigateToPublicationDetail

    fun onPublicationClicked(id: String) {
        _navigateToPublicationDetail.value = id
    }

    fun onPublicationDetailNavigated() {
        _navigateToPublicationDetail.value = null
    }

    init {
        _allPublication.value=Repository.getAllPublications()
        _addPublication.value=null
        //_navigateToPublicationDetail.value=1
    }

    fun setAddButton(){
        _addPublication.value=false
    }

    fun goToAddPublicationUi(){
        _addPublication.value=true
    }
}