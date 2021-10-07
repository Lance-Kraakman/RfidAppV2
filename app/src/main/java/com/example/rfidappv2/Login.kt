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
import com.example.rfidappv2.models.Device
import com.example.rfidappv2.models.Station
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

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
            fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    progressBarLogin.visibility = View.INVISIBLE
                    Toast.makeText(this@Login, "Login Success", Toast.LENGTH_SHORT).show();
                    setupDatabaseForUser()
                    startActivity(
                        Intent(
                            this,
                            MainActivity::class.java
                        )
                    ) // Now we go to main navagation instead of
                } else {
                    progressBarLogin.visibility = View.INVISIBLE
                    Toast.makeText(this, "Error! " + it.exception.toString(), Toast.LENGTH_SHORT)
                        .show();
                }
            }
        })
    }

    public fun registerView(view : View) {
        startActivity(Intent(this, Register::class.java))
    }
    lateinit var mDb: FirebaseDatabase;
    private fun setupDatabaseForUser() {
        mDb = FirebaseDatabase.getInstance()
        val mRef = mDb.getReference()
       // progressBar.visibility = View.VISIBLE;

        fAuth.currentUser?.uid?.let { mRef.child("stations").child(it).push().setValue(Station("test","test","test","test","test")) }
        fAuth.currentUser?.uid?.let { mRef.child("device").child(it).push().setValue(Device("test","test","test","test","test","testdevice","demm","testkd","est")) }


    }



    //
}