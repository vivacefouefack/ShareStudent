package g54490.mobg5.sharestudent.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.databinding.ActivityLoginBinding
import g54490.mobg5.sharestudent.model.Repository
import g54490.mobg5.sharestudent.viewmodel.AddViewModel
import g54490.mobg5.sharestudent.viewmodel.AddViewModelFactory
import g54490.mobg5.sharestudent.viewmodel.LoginViewModel
import g54490.mobg5.sharestudent.viewmodel.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {
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

        loginViewModel.canConnect.observe(this) {
            //FIXME (QHB) :rely on the onFailure listener of Firebase to check connectivity issues, not on ConnectivityManager
            val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Repository.isOnline(connMgr)) {
                if (it == true) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    binding.inputEmail.text = null
                    binding.inputPassword.text = null
                    finish()
                }
                if (it == false) {
                    binding.inputEmail.error = getString(R.string.invalid)
                    binding.inputPassword.error = getString(R.string.invalid)
                }
            } else {
                Toast.makeText(this@LoginActivity, getString(R.string.connexionError), Toast.LENGTH_LONG).show()
            }
        }

        loginViewModel.canGoToRegisterUi.observe(this) {
            if (it == true) {
                binding.inputEmail.text = null
                binding.inputPassword.text = null
                loginViewModel.updateCanconnect()
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }
}