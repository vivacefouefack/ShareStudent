package g54490.mobg5.sharestudent.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import g54490.mobg5.sharestudent.model.Repository

class ProfileViewModel: ViewModel(){
    private var _login = MutableLiveData<String>()
    var login: String=""
        get() = Repository.getUsername()

}