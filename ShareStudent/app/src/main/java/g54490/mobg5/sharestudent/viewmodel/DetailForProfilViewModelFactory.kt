package g54490.mobg5.sharestudent.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailForProfilViewModelFactory:ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailForProfilViewModel::class.java)) {
            return DetailForProfilViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}