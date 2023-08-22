package g54490.mobg5.sharestudent.viewmodel

import androidx.lifecycle.ViewModel
import android.content.Context
import androidx.lifecycle.ViewModelProvider

class AddViewModelFactory(private val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddViewModel::class.java)) {
            return AddViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}