package activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.prepsmartly.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var email11:String
    lateinit var password11:String
    lateinit var storedVerificationId:String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth= FirebaseAuth.getInstance()


        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                startActivity(Intent(applicationContext, Mainscreen::class.java))
                finish()
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                password11=passw.editableText.toString().trim()
                email11=email.editableText.toString().trim()
                Log.d("TAG23","onCodeSent:$verificationId")
                storedVerificationId = verificationId
                resendToken = token
                val intent = Intent(applicationContext, otpverification::class.java)
               intent.putExtra("email",email11)
                intent.putExtra("phoneno",phoneno.text.toString().trim())
                intent.putExtra("password",password11)
                intent.putExtra("storedVerificationId",storedVerificationId)
                startActivity(intent)
            }
        }
        button.setOnClickListener {
        val password=passw.editableText.toString().trim()
            val confirmpasword=confirpass.editableText.toString().trim()
    if(name.text.isBlank())
    {
        Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show()
    }
    else if(email.text.isBlank())
    {
        Toast.makeText(this, "email cannot be empty", Toast.LENGTH_SHORT).show()

    }
        else if(passw.text.isBlank())
    {
        Toast.makeText(this, "password cannot be empty", Toast.LENGTH_SHORT).show()

    }
    else if(passw.text.length<6)
    {
        Toast.makeText(this, "password must be of atleast 6 letters", Toast.LENGTH_SHORT).show()

    }
   else if(password!=confirmpasword){
        Toast.makeText(this, "password didn't matched", Toast.LENGTH_SHORT).show()
   }



   else if(phoneno.text.isBlank()||phoneno.text.length!=10)
    {
        Toast.makeText(this, "enter a valid number", Toast.LENGTH_SHORT).show()

    }
            else{
               login()

    }




   
        }
        signuptext.setOnClickListener {
            val intent=Intent(this, loginpage::class.java)
            startActivity(intent)
        }

    }

   private fun login() {
        val mobileNumber=findViewById<EditText>(R.id.phoneno)
        var number=mobileNumber.text.toString().trim()

        if(!number.isEmpty()){
            number="+91"+number
            sendVerificationcode (number)
        }else{
            Toast.makeText(this,"Enter mobile number",Toast.LENGTH_SHORT).show()
        }
    }
    private fun sendVerificationcode(number: String) {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }


}