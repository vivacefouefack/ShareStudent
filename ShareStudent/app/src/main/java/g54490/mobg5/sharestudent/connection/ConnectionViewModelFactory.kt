package g54490.mobg5.sharestudent.connection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import g54490.mobg5.sharestudent.database.UserDao

class ConnectionViewModelFactory(
    private val userId: String,
    private val dataSource: UserDao):ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConnectionViewModel::class.java)) {
            return ConnectionViewModel(userId, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
//fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {