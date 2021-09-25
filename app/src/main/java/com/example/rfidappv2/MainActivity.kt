package com.example.rfidappv2

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.rfidappv2.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    public fun addDevicePage(view: View) { // You have to parse view to register as a event as it is parsed as an arg for the function
        startActivity(Intent(this, AddDevice::class.java))
    }

    fun onClickAddDevice(view: View) { // You have to parse view to register as a event as it is parsed as an arg for the function
        startActivity(Intent(this, AddDevice::class.java))
    }

    fun onClickAddStation(view: View) { // You have to parse view to register as a event as it is parsed as an arg for the function
        startActivity(Intent(this, AddStation::class.java))
    }

    fun onClickLogout(view: View) {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, Login::class.java))
    }




}