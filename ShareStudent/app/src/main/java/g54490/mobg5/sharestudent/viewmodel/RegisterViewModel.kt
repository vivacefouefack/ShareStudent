package g54490.mobg5.sharestudent.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import g54490.mobg5.sharestudent.model.Repository

class RegisterViewModel(application: Application):ViewModel() {
    private var _email = MutableLiveData<CharSequence>()
    val email: LiveData<CharSequence>
        get() = _email

    private var _password = MutableLiveData<CharSequence>()
    val password: LiveData<CharSequence>
        get() = _password

    private var _passwordConfirm = MutableLiveData<CharSequence>()
    val passwordConfirm: LiveData<CharSequence>
        get() = _passwordConfirm

    private val _createUser = MutableLiveData<Boolean?>()
    val createUser: LiveData<Boolean?>
        get() = _createUser

    private val _backToLoginUi = MutableLiveData<Boolean?>()
    val backToLoginUi: LiveData<Boolean?>
        get() = _backToLoginUi

    init {
        _email.value =""
        _password.value =""
        _passwordConfirm.value=""
        _createUser.value=null
        _backToLoginUi.value=null
    }

    fun isValidEmail():Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
    }

    fun emailInput(s: CharSequence, start: Int, before: Int, count: Int) {
        _email.value = s.toString()
    }

    fun passwordInput(s: CharSequence, start: Int, before: Int, count: Int) {
        _password.value = s.toString()
    }

    fun passwordConfirmInput(s: CharSequence, start: Int, before: Int, count: Int) {
        _passwordConfirm.value = s.toString()
    }

    fun isValidpassword():Boolean {
        if (password.value.toString().isNotEmpty() && passwordConfirm.value.toString().isNotEmpty()){
            if (password.value==passwordConfirm.value){
                return true
            }
        }
        return false
    }

    fun createNewUser(){
        if (isValidEmail() && isValidpassword()){
            Repository.getAuth().createUserWithEmailAndPassword(_email.value.toString(),_password.value.toString()).addOnCompleteListener {
                if(it.isSuccessful) {
                    _createUser.value=true
                } else {
                    this._createUser.value=false
                }
            }
        }else{
            _createUser.value=false
        }
    }

    fun backToLoginPage(){
        _backToLoginUi.value=true
    }
}