package g54490.mobg5.sharestudent.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.databinding.ActivityRegisterBinding
import g54490.mobg5.sharestudent.viewmodel.RegisterViewModel
import g54490.mobg5.sharestudent.viewmodel.RegisterViewModelFactory

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerViewModelFactory=RegisterViewModelFactory(this.application)
        registerViewModel= ViewModelProvider(this,registerViewModelFactory)[RegisterViewModel::class.java]

        auth = FirebaseAuth.getInstance()
        binding=  DataBindingUtil.setContentView<g54490.mobg5.sharestudent.databinding.ActivityRegisterBinding>(this,R.layout.activity_register)

        binding.registerBt.setOnClickListener {
            registerViewModel.setEmailAndPasswordAndConfirm(binding.emailInputEt.text.toString(),
            binding.passwordInputEt.text.toString(),binding.confirmPasswordInputEt.text.toString())

            if (registerViewModel.checkIsOkData()){
                auth.createUserWithEmailAndPassword(binding.emailInputEt.text.toString(), binding.passwordInputEt.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful) {
                        Toast.makeText(this@Register, "new user", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        Toast.makeText(this@Register, "error try again! ", Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                binding.emailInputEt.setError("")
                binding.passwordInputEt.setError("")
                binding.confirmPasswordInputEt.setError("")
            }
        }

        binding.backImg.setOnClickListener{
            val intent= Intent(this,Login::class.java)
            startActivity(intent)
        }
    }
}