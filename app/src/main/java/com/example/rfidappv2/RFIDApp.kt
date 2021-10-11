package com.example.rfidappv2

import android.app.Application
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RFIDApp: Application() {


    override fun onCreate() {
        super.onCreate()
        //enable firebase online
        Firebase.database.setPersistenceEnabled(true)
    }
}