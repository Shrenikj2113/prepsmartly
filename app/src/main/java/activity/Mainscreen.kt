package activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prepsmartly.R
import com.google.firebase.auth.FirebaseAuth

class Mainscreen : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainscreen)
        auth= FirebaseAuth.getInstance()

            }
        }



