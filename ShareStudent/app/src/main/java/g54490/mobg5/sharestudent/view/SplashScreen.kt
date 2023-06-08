package g54490.mobg5.sharestudent.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import g54490.mobg5.sharestudent.R

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler= Handler()
        handler.postDelayed({
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        },6000)
        setContentView(R.layout.activity_splash_screen)
    }
}