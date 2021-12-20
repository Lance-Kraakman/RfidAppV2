package com.example.rfidappv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class AddStation : AppCompatActivity() {
    var stationName: String? = null
    var editText: EditText? = null
    var connectBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_station)
        initViews()
        bindAction()
    }
var wifiSSid:String? = null
var wifiPassword:String? = null
    private fun bindAction() {
        connectBtn?.setOnClickListener {
            if (editText?.text?.isEmpty() == true) {
                editText!!.error = "Field is empty!"
                return@setOnClickListener
            }
            if (edit_text_wifiSSid?.text?.isEmpty() == true) {
                edit_text_wifiSSid!!.error = "Field is empty!"
            return@setOnClickListener
        }
            if (editText_password?.text?.isEmpty() == true) {
                editText_password!!.error = "Field is empty!"
            return@setOnClickListener
        }
            stationName = editText?.text.toString()
            wifiSSid = edit_text_wifiSSid?.text.toString()
            wifiPassword = editText_password?.text.toString()


            connectStation()
        }
    }

    private fun connectStation() {
        //sent info to esp32 station provisioning.


    }

    fun onClickExit(view: View) { // You have to parse view to register as a event as it is parsed as an arg for the function
        startActivity(Intent(this, MainActivity::class.java))
    }

    var edit_text_wifiSSid: EditText? = null
    var editText_password: EditText? = null
    fun initViews() {
        connectBtn = findViewById(R.id.buttonStation)
        editText = findViewById(R.id.editTextStationName)
        edit_text_wifiSSid = findViewById(R.id.edit_text_wififssd)
        editText_password = findViewById(R.id.edit_text_password)

    }
}