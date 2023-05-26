package g54490.mobg5.sharestudent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import g54490.mobg5.sharestudent.model.Publication
import g54490.mobg5.sharestudent.model.Repository

class PublicationViewModel:ViewModel() {
    //public var publication: LiveData<Publication> = TODO()
     var publication1=Repository.getAllPublications()[1]
     public var publication:Publication
         get()=publication1

    init {
        publication=Repository.getAllPublications()[1]
        publication1=Repository.getAllPublications()[1]
    }

    private val _navigateToHome = MutableLiveData<Boolean?>()
    val navigateToHome: LiveData<Boolean?>
        get() = _navigateToHome

    fun doneNavigating() {
        _navigateToHome.value = null
    }

    fun navigateToHome() {
        _navigateToHome.value = true
    }


}