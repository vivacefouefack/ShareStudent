package g54490.mobg5.sharestudent.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import g54490.mobg5.sharestudent.model.Repository

class RegisterViewModel :ViewModel() {
    private var _email = MutableLiveData<CharSequence>()
    private val email: LiveData<CharSequence>
        get() = _email

    private var _password = MutableLiveData<CharSequence>()
    private val password: LiveData<CharSequence>
        get() = _password

    private var _passwordConfirm = MutableLiveData<CharSequence>()
    private val passwordConfirm: LiveData<CharSequence>
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

    private fun isValidEmail():Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
    }

    fun emailInput(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.i("insert",""+start+before+count)
        _email.value = s.toString()
    }

    fun passwordInput(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.i("insert",""+start+before+count)
        _password.value = s.toString()
    }

    fun passwordConfirmInput(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.i("insert",""+start+before+count)
        _passwordConfirm.value = s.toString()
    }

    private fun isValidpassword():Boolean {
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