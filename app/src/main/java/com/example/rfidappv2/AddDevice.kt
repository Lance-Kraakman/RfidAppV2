package com.example.rfidappv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.rfidappv2.ui.about.AboutViewModel
import com.example.rfidappv2.ui.dashboard.DashboardViewModel
import com.google.android.gms.common.api.internal.ActivityLifecycleObserver.of

class AddDevice : AppCompatActivity() {

    lateinit var buttonAddDevice: Button;
    lateinit var editTextDeviceName : EditText;
    var db = DataBaseHandler(this)

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_device)

        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)


        buttonAddDevice = findViewById(R.id.buttonStationConnect)
        editTextDeviceName = findViewById(R.id.editTextDeviceName)

        buttonAddDevice.setOnClickListener{
            if (editTextDeviceName.text.length > 0) {
                var device  = Device(editTextDeviceName.text.toString(),23,0)
                db.insertData(device)
                var devList : List<Device> = db.readDevices()

            } else {
                Toast.makeText(this, "Please Give Name", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun onClickExit(view: View) { // You have to parse view to register as a event as it is parsed as an arg for the function
        startActivity(Intent(this, MainActivity::class.java))
    }

}