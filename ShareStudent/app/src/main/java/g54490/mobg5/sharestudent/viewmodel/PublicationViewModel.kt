package g54490.mobg5.sharestudent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import g54490.mobg5.sharestudent.model.Publication
import g54490.mobg5.sharestudent.model.Repository

class PublicationViewModel:ViewModel() {
      var publication:Publication=Publication("","","","","")
         get()=Repository.pub

    private val _navigateToHome = MutableLiveData<Boolean?>()
    val navigateToHome: LiveData<Boolean?>
        get() = _navigateToHome

    private val _writeToAuthor = MutableLiveData<Boolean?>()
    val writeToAuthor: LiveData<Boolean?>
        get() = _writeToAuthor

    fun sendEmail() {
        _writeToAuthor.value = true
    }


}