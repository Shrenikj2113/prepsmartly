package activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.prepsmartly.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgotpasswordscreen.*

class forgotpasswordscreen : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpasswordscreen)
        auth= FirebaseAuth.getInstance()
        button2.setOnClickListener {
            if(forgotemail.text.isBlank())
            {
                Toast.makeText(this, "Enter email!", Toast.LENGTH_SHORT).show()
            }else
            {
               auth.sendPasswordResetEmail("${forgotemail.text.toString().trim()}").addOnCompleteListener(this) { 
                   if(it.isSuccessful)
                   {
                       Toast.makeText(this, "Check your email Link has been sended to your email ", Toast.LENGTH_SHORT).show()
                   }
                   else{
                       Toast.makeText(this, "enter correct email", Toast.LENGTH_SHORT).show()
                   }
               }
                
            }
        }
    }
}