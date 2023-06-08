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
import g54490.mobg5.sharestudent.databinding.ActivityRegisterBinding
import g54490.mobg5.sharestudent.model.Repository
import g54490.mobg5.sharestudent.viewmodel.RegisterViewModel
import g54490.mobg5.sharestudent.viewmodel.RegisterViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerViewModelFactory=RegisterViewModelFactory(this.application)
        registerViewModel= ViewModelProvider(this,registerViewModelFactory)[RegisterViewModel::class.java]

        binding=  DataBindingUtil.setContentView(this,R.layout.activity_register)

        binding.registerViewModel=registerViewModel
        registerViewModel.createUser.observe(this) {
            val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Repository.isOnline(connMgr)) {
                if (it == true) {
                    Toast.makeText(this@RegisterActivity, "new user", Toast.LENGTH_LONG).show()
                    binding.emailInputEt.text = null
                    binding.passwordInputEt.text = null
                    binding.confirmPasswordInputEt.text = null
                    finish()
                }
                if (it == false) {
                    binding.emailInputEt.error = ""
                    binding.passwordInputEt.error = ""
                    binding.confirmPasswordInputEt.error = ""
                }
            } else {
                Toast.makeText(this@RegisterActivity, "connexion error", Toast.LENGTH_LONG).show()
            }


        }

        registerViewModel.backToLoginUi.observe(this) {
            if (it == true) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}