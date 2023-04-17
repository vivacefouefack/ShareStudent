package g54490.mobg5.sharestudent.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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

    fun isValidEmail():Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
    }

    fun setEmailAndPasswordAndConfirm(email:String,password:String,passwordConfirm:String){
        _email.value =email
        _password.value =password
        _passwordConfirm.value=passwordConfirm
    }

    fun isValidpassword():Boolean {
        if (password.value.toString().isNotEmpty() && passwordConfirm.value.toString().isNotEmpty()){
            if (password.value==passwordConfirm.value){
                return true
            }
        }
        return false
    }

    fun checkIsOkData():Boolean{
        if (isValidEmail() && isValidpassword()){
            return true
        }
        return false
    }
}