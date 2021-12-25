package com.example.prepsmartly

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_loginpage.*

class loginpage : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginpage)
        firebaseAuth = FirebaseAuth.getInstance()
        registertext.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        forgot.setOnClickListener {
            val intent = Intent(this, forgotpasswordscreen::class.java)
            startActivity(intent)
        }

        yoooo.setOnClickListener {
            val email2 = emailid.editableText.toString().trim()
            val password2 = passwordid.editableText.toString().trim()
            if (emailid.text.isBlank()) {
                Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show()
            } else if (passwordid.text.isBlank() || password2.length < 6) {
                Toast.makeText(this, "Enter correct password", Toast.LENGTH_SHORT).show()
            } else {
                send(email2,password2)
            }


        }
    }

    private fun send(email2:String,password2:String) {
        firebaseAuth.signInWithEmailAndPassword(email2, password2)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "yeeee", Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
    }
}