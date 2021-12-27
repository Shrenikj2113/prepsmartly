package activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prepsmartly.R
import kotlinx.android.synthetic.main.activity_intro.*

class intro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        loginclick.setOnClickListener {
            startActivity(Intent(this, loginpage::class.java))

        }
        regis.setOnClickListener {
            val intent=Intent(this, MainActivity::class.java)
startActivity(intent)
        }
    }
}