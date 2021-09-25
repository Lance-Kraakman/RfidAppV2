package com.example.rfidappv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.EditText
//import com.example.tutorial.databinding.ActivityMainBinding
import android.widget.Button
import android.widget.ProgressBar
import com.google.firebase.auth.FirebaseAuth
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import org.jetbrains.annotations.NotNull

class Register : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding;
    lateinit var mRegisterButton:Button;
    lateinit var mFullName:EditText; lateinit var mEmail:EditText;
    lateinit var mPassword:EditText; lateinit var mPhone:EditText;
    lateinit var fAuth: FirebaseAuth; lateinit var progressBar: ProgressBar;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.e("asdfasdf","ssadsfdf")
        setContentView(R.layout.activity_register2)
        Log.e("asdfasdf","ssadsfdf")

        mEmail = findViewById(R.id.editTextRegisterEmail)
        mPassword = findViewById(R.id.editTextRegisterPassword)
        mPhone = findViewById(R.id.editTextRegisterPhone)
        mFullName = findViewById(R.id.editTextRegisterName)
        progressBar = findViewById(R.id.progressBarReg)

        mRegisterButton = findViewById(R.id.buttonRegister)
        fAuth = FirebaseAuth.getInstance()

        if (fAuth.currentUser != null) {
            Log.e("Register","Already Logged In")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        // Now we can do the app programming
        mRegisterButton.setOnClickListener(View.OnClickListener {
            var email: String = mEmail.text.toString().trim()
            var password: String = mPassword.text.toString().trim()

            Log.i("pass",password)
            Log.i("email",email)

            if (TextUtils.isEmpty(email)) {
                mEmail.setError("Email Is Required")
                return@OnClickListener
            }

            if (TextUtils.isEmpty(password)) {
                mPassword.setError("Password Is Required")
                return@OnClickListener
            }

            if (password.length < 6) {
                mPassword.setError("Password Must Be 6 Characters or more")
                return@OnClickListener
            }

            progressBar.visibility = View.VISIBLE
            fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener({
                if (it.isSuccessful) {
                    Toast.makeText(this@Register, "User Created", Toast.LENGTH_SHORT).show();
                    startActivity(Intent(this, MainActivity::class.java))
                }
                else {
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this@Register, "Error! " + it.exception.toString(), Toast.LENGTH_SHORT).show();
                }
            })
        })
    }

    public fun loginView(view : View) {
        startActivity(Intent(this, Login::class.java))
    }


}


