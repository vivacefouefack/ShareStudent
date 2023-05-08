package g54490.mobg5.sharestudent.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.databinding.ActivityLoginBinding
import g54490.mobg5.sharestudent.viewmodel.AddViewModel
import g54490.mobg5.sharestudent.viewmodel.AddViewModelFactory
import g54490.mobg5.sharestudent.viewmodel.LoginViewModel
import g54490.mobg5.sharestudent.viewmodel.LoginViewModelFactory

//FIXME (QHB) :this class should have an explicit name : LoginActivity
class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var add: AddViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding=  DataBindingUtil.setContentView(this,R.layout.activity_login)
        val loginViewModelFactory= LoginViewModelFactory(this.application)
        loginViewModel=ViewModelProvider(this,loginViewModelFactory)[LoginViewModel::class.java]

        val addViewModelFactory=AddViewModelFactory()
        add=ViewModelProvider(this,addViewModelFactory)[AddViewModel::class.java]

        binding.loginViewModel=loginViewModel

        loginViewModel.canConnect.observe(this, Observer {
            if (it == true) {
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
                //binding.inputEmail.text=null
                //binding.inputPassword.text=null
            }
            if (it == false){
                binding.inputEmail.error = "invalid"
                binding.inputPassword.error = "invalid"
            }
        })

        loginViewModel.canGoToRegisterUi.observe(this, Observer {
            if (it == true) {
                val intent= Intent(this,Register::class.java)
                startActivity(intent)
            }
        })
    }
}