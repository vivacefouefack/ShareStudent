package g54490.mobg5.sharestudent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import g54490.mobg5.sharestudent.model.Publication
import g54490.mobg5.sharestudent.model.Repository

class ProfileViewModel: ViewModel(){
    private var _login = MutableLiveData<String>()
    var login: String=""
        get() = Repository.getUsername()

    private var _nbPublication = MutableLiveData<String>()
    var nbPublication: String=""
        get() =Repository.getMyPublications().size.toString()

    private var _myPublication = MutableLiveData<List<Publication>>()
    val myPublication: LiveData<List<Publication>>
        get() = _myPublication

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
        _myPublication.value=Repository.getMyPublications()
    }

}