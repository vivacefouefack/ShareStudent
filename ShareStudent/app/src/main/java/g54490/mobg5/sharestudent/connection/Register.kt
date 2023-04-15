package g54490.mobg5.sharestudent.connection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding=  DataBindingUtil.setContentView<g54490.mobg5.sharestudent.databinding.ActivityRegisterBinding>(this,R.layout.activity_register)
        binding.backImg.setOnClickListener{
            val intent= Intent(this,Login::class.java)
            startActivity(intent)
        }
    }
}