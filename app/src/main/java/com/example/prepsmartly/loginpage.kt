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
    lateinit var firebaseAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginpage)
        firebaseAuth= FirebaseAuth.getInstance()
    registertext.setOnClickListener {
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
     forgot.setOnClickListener {
         startActivity(Intent(this,forgotpasswordscreen::class.java))
     }
        yoooo.setOnClickListener {
            val email2=emailid.editableText.toString().trim()
            val password2=passwordid.editableText.toString().trim()
            firebaseAuth.signInWithEmailAndPassword(email2, password2)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }
        }

    }
}