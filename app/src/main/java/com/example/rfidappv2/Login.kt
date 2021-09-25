package com.example.rfidappv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    lateinit var mLoginButton: Button;
    lateinit var mLoginEmail: EditText; lateinit var mLoginPassword: EditText;
    lateinit var progressBarLogin: ProgressBar; lateinit var fAuth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        mLoginButton = findViewById(R.id.buttonLogin)
        mLoginEmail = findViewById(R.id.editTextLoginEmail)
        mLoginPassword = findViewById(R.id.editTextLoginPassword)
        progressBarLogin = findViewById(R.id.progressBarLogin)

        fAuth = FirebaseAuth.getInstance()


        // Now we can do the app programming
        mLoginButton.setOnClickListener(View.OnClickListener {
            var email: String = mLoginEmail.text.toString().trim()
            var password: String = mLoginPassword.text.toString().trim()

            Log.i("pass",password)
            Log.i("email",email)

            if (TextUtils.isEmpty(email)) {
                mLoginEmail.setError("Email Is Required")
                return@OnClickListener
            }

            if (TextUtils.isEmpty(password)) {
                mLoginPassword.setError("Password Is Required")
                return@OnClickListener
            }

            if (password.length < 6) {
                mLoginPassword.setError("Password Must Be 6 Characters or more")
                return@OnClickListener
            }

            progressBarLogin.visibility = View.VISIBLE
            fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener({
                if (it.isSuccessful) {
                    progressBarLogin.visibility = View.INVISIBLE
                    Toast.makeText(this@Login, "Login Success", Toast.LENGTH_SHORT).show();
                    startActivity(Intent(this, MainActivity::class.java)) // Now we go to main navagation instead of
                }
                else {
                    progressBarLogin.visibility = View.INVISIBLE
                    Toast.makeText(this, "Error! " + it.exception.toString(), Toast.LENGTH_SHORT).show();
                }
            })
        })
    }

    public fun registerView(view : View) {
        startActivity(Intent(this, Register::class.java))
    }
}