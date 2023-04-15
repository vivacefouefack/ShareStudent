package g54490.mobg5.sharestudent.connection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding=  DataBindingUtil.setContentView<g54490.mobg5.sharestudent.databinding.ActivityLoginBinding>(this,R.layout.activity_login)
        binding.registerLink.setOnClickListener{ view: View ->
            val intent= Intent(this,Register::class.java)
            startActivity(intent)
        }
    }
}