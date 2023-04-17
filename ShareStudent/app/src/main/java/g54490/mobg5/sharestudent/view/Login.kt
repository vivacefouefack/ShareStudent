package g54490.mobg5.sharestudent.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.databinding.ActivityLoginBinding
import g54490.mobg5.sharestudent.viewmodel.LoginViewModel
import g54490.mobg5.sharestudent.viewmodel.LoginViewModelFactory

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding=  DataBindingUtil.setContentView<g54490.mobg5.sharestudent.databinding.ActivityLoginBinding>(this,R.layout.activity_login)
        val loginViewModelFactory= LoginViewModelFactory(this.application)
        loginViewModel=ViewModelProvider(this,loginViewModelFactory)[LoginViewModel::class.java]

        auth=FirebaseAuth.getInstance()
        binding.loginButton.setOnClickListener {view: View ->
            loginViewModel.setEmailAndPassword(binding.inputEmail.text.toString(),binding.inputPassword.text.toString())
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            /*if(loginViewModel.checkData()){
                val email=loginViewModel.email.value.toString()
                val password=loginViewModel.password.value.toString()
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent= Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        binding.inputEmail.text=null
                        binding.inputPassword.text=null
                    } else {
                        binding.inputEmail.setError("invalid")
                        binding.inputPassword.setError("invalid")
                        Log.w("montag1", "signInWithEmail:failure", task.exception)
                        Toast.makeText(this, "Authentication failed.",Toast.LENGTH_SHORT).show()
                    }
                })
            }else{
                binding.inputEmail.setError("invalid*")
                binding.inputPassword.setError("invalid*")
            }*/
        }

        binding.registerLink.setOnClickListener{ view: View ->
            val intent= Intent(this,Register::class.java)
            startActivity(intent)
        }
    }
}