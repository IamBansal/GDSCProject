package com.example.firstfirebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {


    private var email : EditText? = null
    private var password : EditText? = null
    private var confirmPassword : EditText? = null
    private var signUpButton : Button? = null
    private var firebaseAuth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        email = findViewById(R.id.etEmailSign)
        password = findViewById(R.id.etpassSign)
        signUpButton = findViewById(R.id.btnSign)
        confirmPassword = findViewById(R.id.etConfirmSign)
        firebaseAuth = FirebaseAuth.getInstance()

        signUpButton?.setOnClickListener {
            signUp()
        }

    }

    private fun signUp() {

        val emailText = email?.text.toString().trim()
        val passwordText = password?.text.toString().trim()
        val confirmText = confirmPassword?.text.toString().trim()

        if (TextUtils.isEmpty(emailText) || TextUtils.isEmpty(passwordText) || TextUtils.isEmpty(confirmText)) {
            Toast.makeText(this, "Fill all credentials.", Toast.LENGTH_SHORT).show()
        } else {

            firebaseAuth?.createUserWithEmailAndPassword(emailText, passwordText)
                ?.addOnCompleteListener { task ->

                    if (passwordText == confirmText && task.isSuccessful) {
                        Toast.makeText(this, "Account created.\nLogin now to continue.", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                    }

                }
        }

    }
}