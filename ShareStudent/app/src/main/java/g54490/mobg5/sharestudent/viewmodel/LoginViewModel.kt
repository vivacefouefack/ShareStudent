package g54490.mobg5.sharestudent.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import g54490.mobg5.sharestudent.model.Repository

class LoginViewModel :ViewModel() {
    private var _email = MutableLiveData<CharSequence>()
    private val email: LiveData<CharSequence>
        get() = _email

    private var _password = MutableLiveData<CharSequence>()
    private val password: LiveData<CharSequence>
        get() = _password

    private val _canConnect = MutableLiveData<Boolean?>()
    val canConnect: LiveData<Boolean?>
        get() = _canConnect

    private val _canGoToRegisterUi = MutableLiveData<Boolean?>()
    val canGoToRegisterUi: LiveData<Boolean?>
        get() = _canGoToRegisterUi

    init {
        _email.value =""
        _password.value =""
        _canConnect.value=null
        _canGoToRegisterUi.value=false
    }

    private fun isValidEmail(email: CharSequence?):Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun emailEnter(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.i("insert",""+start+before+count)
        _email.value = s.toString()
    }

    fun passwordEnter(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.i("insert",""+start+before+count)
        _password.value = s.toString()
    }

    fun checkData(){
        if (isValidEmail(email.value) && password.value.toString().isNotEmpty()){
            Repository.getAuth().signInWithEmailAndPassword(email.value.toString(),password.value.toString()).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Repository.setUsername(email.value.toString())
                    _canConnect.value = true
                } else {
                    this._canConnect.value = false
                }
            }
        }else{
            this._canConnect.value=false
        }
    }

    fun goToRegisterUi(){
        _canGoToRegisterUi.value=true
    }
}