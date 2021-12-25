package com.example.prepsmartly

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_otpverification.*

class otpverification : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverification)
        digitauto()
        auth=FirebaseAuth.getInstance()

        val storedVerificationId=intent.getStringExtra("storedVerificationId")
numberentred.text=intent.getStringExtra("phoneno")
        Login.setOnClickListener{

            var otpGiven=otp_edit_box1.text.toString().trim()+otp_edit_box2.text.toString().trim()+otp_edit_box3.text.toString().trim()+otp_edit_box4.text.toString().trim()+otp_edit_box5.text.toString().trim()+otp_edit_box6.text.toString().trim()

            var otp=otpGiven
            if(!otp.isEmpty()){
                val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(
                    storedVerificationId.toString(), otp)
                signInWithPhoneAuthCredential(credential)
            }else{
                Toast.makeText(this,"Enter OTP",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        val email=intent.getStringExtra("email")
        val password=intent.getStringExtra("password")
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent1=Intent(this,loginpage::class.java)

                    auth.createUserWithEmailAndPassword(email.toString(), password.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success")

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                                Toast.makeText(baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()

                            }
                        }
                    startActivity(intent1)

                    finish()
// ...
                } else {


                    if (task.exception is FirebaseAuthInvalidCredentialsException) {

                        Toast.makeText(this,"Invalid OTP",Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
        private fun digitauto() {
            otp_edit_box1.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence? , p1: Int, p2: Int, p3: Int) {
                    if(!p0.toString().isEmpty())
                    {
                        otp_edit_box2.requestFocus()
                    }

                }


                override fun afterTextChanged(p0: Editable?) {

                }

            })

            otp_edit_box2.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence? , p1: Int, p2: Int, p3: Int) {
                    if(!p0.toString().isEmpty())
                    {
                        otp_edit_box3.requestFocus()
                    }

                }


                override fun afterTextChanged(p0: Editable?) {

                }

            })

            otp_edit_box3.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence? , p1: Int, p2: Int, p3: Int) {
                    if(!p0.toString().isEmpty())
                    {
                        otp_edit_box4.requestFocus()
                    }

                }


                override fun afterTextChanged(p0: Editable?) {

                }

            })
            otp_edit_box4.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence? , p1: Int, p2: Int, p3: Int) {
                    if(!p0.toString().isEmpty())
                    {
                        otp_edit_box5.requestFocus()
                    }

                }


                override fun afterTextChanged(p0: Editable?) {

                }

            })
            otp_edit_box5.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence? , p1: Int, p2: Int, p3: Int) {
                    if(!p0.toString().isEmpty())
                    {
                        otp_edit_box6.requestFocus()
                    }

                }


                override fun afterTextChanged(p0: Editable?) {
                    // val finalotp =
                    //   otp_edit_box1.text.toString() + otp_edit_box2.text.toString() + otp_edit_box3.text.toString() + otp_edit_box4.text.toString() + otp_edit_box5.text.toString() + otp_edit_box6.text.toString()


                }

            })




        }

}