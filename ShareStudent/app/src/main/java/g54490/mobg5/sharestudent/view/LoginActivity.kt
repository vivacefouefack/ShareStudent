package g54490.mobg5.sharestudent.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.databinding.ActivityLoginBinding
import g54490.mobg5.sharestudent.viewmodel.LoginViewModel
import g54490.mobg5.sharestudent.viewmodel.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var activityBLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding=  DataBindingUtil.setContentView(this,R.layout.activity_login)
        val loginViewModelFactory= LoginViewModelFactory(this.application)
        loginViewModel=ViewModelProvider(this,loginViewModelFactory)[LoginViewModel::class.java]
        binding.loginViewModel=loginViewModel

        loginViewModel.wrongPassword.observe(this){
            if (it==true){
                binding.inputEmail.error=null
                binding.inputPassword.error = getString(R.string.invalid)
                Toast.makeText(this@LoginActivity, getString(R.string.wrongPass), Toast.LENGTH_LONG).show()
            }
        }

        loginViewModel.canConnect.observe(this) {
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
                Toast.makeText(this@LoginActivity, getString(R.string.connexionError), Toast.LENGTH_LONG).show()
            }
        }

        activityBLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                finish() // Terminer LoginActivity si RegisterActivity se termine avec succès
            }
        }

        loginViewModel.canGoToRegisterUi.observe(this) {
            if (it == true) {
                binding.inputEmail.text = null
                binding.inputPassword.text = null
                loginViewModel.updateCanconnect()
                val intent = Intent(this, RegisterActivity::class.java)
                activityBLauncher.launch(intent)
            }
        }
    }
}