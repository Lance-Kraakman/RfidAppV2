package com.example.rfidappv2

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.rfidappv2.models.Device
import com.example.rfidappv2.mvvm.IViewModal
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*

class AddDevice : AppCompatActivity() {
    var device_Name: String? = null;
    var device_tag: String? = null;
    var editText: EditText? = null;
    var blueRadio: RadioButton? = null;
    var greenRadio: RadioButton? = null;
    var yellowRadio: RadioButton? = null;
    var redRadio: RadioButton? = null;
    var viewModal: IViewModal? = null;

    var connectBtn: Button? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_device)
        init()
        bindViews()

    }

    fun bindViews() {
        editText = findViewById(R.id.editTextDeviceName)
        blueRadio = findViewById(R.id.radioButtonTagBlue)
        greenRadio = findViewById(R.id.radioButtonTagGreen)
        yellowRadio = findViewById(R.id.radioButtonTagYellow)
        redRadio = findViewById(R.id.radioButtonTagRed)
        connectBtn = findViewById(R.id.buttonStation)
    }

    fun init() {
        viewModal = ViewModelProvider(this).get(IViewModal::class.java)
    }

    fun bindAction() {
        connectBtn?.setOnClickListener(View.OnClickListener {
            if (editText?.text?.isEmpty() == true) {
                editText!!.error = "Field is empty!"
                return@OnClickListener
            }
            device_Name = editText?.text.toString()
           suspend {
               Log.d("Hack", "bindAction: ")
               connectDevice()
           }
        })
        blueRadio?.setOnCheckedChangeListener { p0, p1 ->
            if (p1)
                device_tag = "Blue";
        }
        redRadio?.setOnCheckedChangeListener { p0, p1 ->
            if (p1)
                device_tag = "Red";


            greenRadio?.setOnCheckedChangeListener { p0, p1 ->
                if (p1)
                    device_tag = "Green";

            }
            yellowRadio?.setOnCheckedChangeListener { p0, p1 ->
                if (p1)
                    device_tag = "Yellow";

            }
        }

        fun onClickExit(view: View) { // You have to parse view to register as a event as it is parsed as an arg for the function
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    private suspend fun connectDevice() {
        //first job to add device to db
        var d = device_Name?.let { Device("-1", it, "43", "53", "null", "null", "0", "null") }
        val job = runBlocking {
            launch {
                viewModal?.addNewDevice(FirebaseAuth.getInstance().currentUser?.uid, d)
            }
        }
        var lastAddedDevice: Device? = null
        //wait untill job1 is complete
        //step 2
        job.join()
        val dialog = ProgressDialog(this)
        dialog.setMessage("Please push add device button on nearest station")
        dialog.show()
        val job2 = runBlocking {
            launch {

                lastAddedDevice =
                    viewModal?.getLastAddedDevice(FirebaseAuth.getInstance().currentUser?.uid);
            }
        }
        //wait for second device
        job2.join()
        val isAdded: Boolean? = false
        //step 4
        val job3 = runBlocking {
            launch {
                repeat(10) {i ->
                    lastAddedDevice =
                        viewModal?.getLastAddedDevice(FirebaseAuth.getInstance().currentUser?.uid);
                    delay(3000) //
                }
            }

        }
        delay(30000)
        job3.cancelAndJoin()
        if(lastAddedDevice?.is_register.equals("1") ){
            Toast.makeText(this@AddDevice,"Device added successfully!",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this@AddDevice,"Device not added! Try again later",Toast.LENGTH_SHORT).show()

        }


    }
}
