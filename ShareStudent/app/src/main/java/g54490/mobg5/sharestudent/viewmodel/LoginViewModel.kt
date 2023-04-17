package g54490.mobg5.sharestudent.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel(application: Application):ViewModel() {

    private var _email = MutableLiveData<CharSequence>()
    val email: LiveData<CharSequence>
        get() = _email


    private var _password = MutableLiveData<CharSequence>()
    val password: LiveData<CharSequence>
        get() = _password

    init {
        _email.value =""
        _password.value =""
    }

    fun setEmailAndPassword(email:String,password:String){
        _email.value =email
        _password.value =password
    }

    fun isValidEmail(email: CharSequence?):Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun checkData():Boolean{
        if (isValidEmail(email.value) && password.value.toString().isNotEmpty()){
            return true
        }
        return false
    }

    fun emailEnter(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.d("montag3", "je suis passéeeeeeeeeeeeeeeeeeeeeeeee")
        _email.value = s.toString()
    }

    fun passwordEnter(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.d("montag3", "passsssssssssssssssssssssssssssss")
        _password.value = s.toString()
    }
}