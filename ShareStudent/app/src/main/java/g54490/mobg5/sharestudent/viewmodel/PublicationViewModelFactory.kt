package g54490.mobg5.sharestudent.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PublicationViewModelFactory: ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PublicationViewModel::class.java)) {
            return PublicationViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}