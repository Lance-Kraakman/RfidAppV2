package com.example.rfidappv2.mvvm

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import androidx.lifecycle.MutableLiveData
import com.example.rfidappv2.models.Device
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.example.rfidappv2.models.Station
import java.util.ArrayList

class FirebaseRepo {
    var firebaseDatabase: FirebaseDatabase
    var mRef: DatabaseReference

    constructor() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        mRef = firebaseDatabase.reference
        mRef.keepSynced(true) //for offline compatibility
    }

    constructor(ref: String?) {
        firebaseDatabase = FirebaseDatabase.getInstance()
        mRef = firebaseDatabase.getReference(ref!!)
        mRef.keepSynced(true)
    }

    var TAG = "Hack_firebase_repo"
    fun getAllDeviceList(uuid: String): MutableLiveData<List<Device?>> {
        Log.d(TAG, "getAllDeviceList: userid: $uuid")
        val mutableLiveData = MutableLiveData<List<Device?>>()
        val deviceList: MutableList<Device?> = ArrayList()
        mRef.child("device").child(uuid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("HACK", "onDataChange:--1 " + snapshot.value)
                for (sp in snapshot.children) {
                    Log.d(TAG, "onDataChange:key " + sp.key)
                    val device = sp.getValue(
                        Device::class.java
                    )
                    deviceList.add(device)
                    Log.d(TAG, "onDataChange: $device")
                }
                mutableLiveData.value = deviceList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "onCancelled: ")
            }
        })
        return mutableLiveData
    }

    fun getAllStationsList(userId: String?): MutableLiveData<List<Station?>> {
        val mutableLiveData = MutableLiveData<List<Station?>>()
        val stationList: MutableList<Station?> = ArrayList()
        mRef.child(userId!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snap in snapshot.children) {
                    val station = snap.getValue(Station::class.java)
                    stationList.add(station)
                }
                mutableLiveData.value = stationList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "onCancelled: " + error.message)
            }
        })
        return mutableLiveData
    }

    fun addDevice(refId: String?, device: Device?) {
        mRef.child("device").child(refId!!).push().setValue(device)
    }

    fun addStations(uuid: String?, station: Station?) {
        mRef.child(uuid!!).push().setValue(station)
    }

    fun removeStation(uuid: String?, station: Station) {
        val query = mRef.child(uuid!!).equalTo(station.uuid)
        query.removeEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snap in snapshot.children) {
                    snap.ref.removeValue()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "onCancelled: ")
            }
        })
        //empty for now
    }

    fun removeDevice(uuid: String?, station: Device) {
        val query = mRef.child(uuid!!).equalTo(station.uuid)
        query.removeEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snapshot1 in snapshot.children) {
                    snapshot1.ref.removeValue()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "onCancelled: " + error.details)
            }
        })
        //empty for now
    }

    fun getLastAddedDevice(userId:String?) : Device? {

        // by limitToLast expecting only last item to retrieve
        var device: Device? = null
        mRef.child(userId!!).limitToLast(1).addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(snap in snapshot.children){
                     device = snap.getValue(Device::class.java)
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        return  device
    }
}