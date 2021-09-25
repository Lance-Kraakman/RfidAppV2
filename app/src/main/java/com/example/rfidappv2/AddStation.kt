package com.example.rfidappv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AddStation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_station)
    }

    fun onClickExit(view: View) { // You have to parse view to register as a event as it is parsed as an arg for the function
        startActivity(Intent(this, MainActivity::class.java))
    }
}