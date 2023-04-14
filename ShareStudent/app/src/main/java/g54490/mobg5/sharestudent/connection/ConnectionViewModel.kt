package g54490.mobg5.sharestudent.connection

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import g54490.mobg5.sharestudent.database.User
import g54490.mobg5.sharestudent.database.UserDao
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConnectionViewModel(application: Application,val database: UserDao) :ViewModel() {

    val isNewUser: Boolean=true
    private var _emailAdress = MutableLiveData<CharSequence>()
    val emailAdress: LiveData<CharSequence>
        get() = _emailAdress
    init {
        _emailAdress.value =""
    }

    private suspend fun insert(user: User) {
            database.insert(user)
    }

    private suspend fun update(user: User) {
        database.update(user)
    }

    fun isValidEmail(email:CharSequence):Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun getAllUsers(){
        val users=database.getAllUsers()
        var index=0
        while (index<users.size){
            var user=users.get(index).emailAdress+" * "+users.get(index).registrationTime+" + "+users.get(index).registrationDate
            Log.i("viewModel", user)
            index++
        }
    }

    fun saveEmailadress(email: CharSequence){
        viewModelScope.launch {
            val user: User? = database.get(email.toString())
            if (user != null) {
                update(user)
            }else{

                val newUser = User(email.toString(),System.currentTimeMillis(),122)
                insert(newUser)
                getAllUsers()
            }

        }
    }

}