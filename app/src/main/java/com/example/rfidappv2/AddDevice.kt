package com.example.rfidappv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth

class AddDevice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_device)
    }

    fun onClickExit(view: View) { // You have to parse view to register as a event as it is parsed as an arg for the function
        startActivity(Intent(this, MainActivity::class.java))
    }



}