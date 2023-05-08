package g54490.mobg5.sharestudent.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(application: Application):ViewModel() {
    private var auth: FirebaseAuth

    private var _email = MutableLiveData<CharSequence>()
    val email: LiveData<CharSequence>
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
        auth= FirebaseAuth.getInstance()
    }

    private fun isValidEmail(email: CharSequence?):Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    fun emailEnter(s: CharSequence, start: Int, before: Int, count: Int) {
        _email.value = s.toString()
    }

    fun passwordEnter(s: CharSequence, start: Int, before: Int, count: Int) {
        _password.value = s.toString()
    }

    fun checkData(){
        if (isValidEmail(email.value) && password.value.toString().isNotEmpty()){

            auth.signInWithEmailAndPassword(email.value.toString(),password.value.toString()).addOnCompleteListener(
                OnCompleteListener { task ->
                if (task.isSuccessful) {
                    _canConnect.value=true
                } else {
                    this._canConnect.value=false
                }
            })
        }else{
            this._canConnect.value=false
        }
    }

    fun goToRegisterUi(){
        _canGoToRegisterUi.value=true
    }
}