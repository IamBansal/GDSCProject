package com.example.firstfirebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private var email : EditText? = null
    private var password : EditText? = null
    private var loginButton : Button? = null
    private var createAccount : TextView? = null
    private var createAccountt : TextView? = null
    private var firebaseAuth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPassword)
        loginButton = findViewById(R.id.btnLogin)
        createAccount = findViewById(R.id.tvSignup)
        createAccountt = findViewById(R.id.tvSignup2)
        firebaseAuth = FirebaseAuth.getInstance()

        createAccount?.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }

        createAccountt?.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }

        loginButton?.setOnClickListener {
            login()
        }

    }

    private fun login() {

        val emailText = email?.text.toString().trim()
        val passwordText = password?.text.toString().trim()


        if (TextUtils.isEmpty(emailText) || TextUtils.isEmpty(passwordText)) {
            Toast.makeText(this, "Fill all credentials.", Toast.LENGTH_SHORT).show()
        } else {

            firebaseAuth?.signInWithEmailAndPassword(emailText, passwordText)
                ?.addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        startActivity(Intent(this, AppMainView::class.java))
                    } else {
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                    }

                }
        }


    }
}