package com.example.rfidappv2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


val DATABASE_NAME="RfidDatabase"
val DEVICE_TABLE_NAME="Devices"
val COL_NAME="Name"
val COL_TYPE="Type"
val COL_RSSI="Rssi"
val COL_STATUS="Status"
val COL_X="X"
val COL_Y="Y"
val COL_ID="id"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " +  DEVICE_TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COL_NAME+" VARCHAR(256),"+
                COL_TYPE+" INTEGER,"+ COL_RSSI+" INTEGER,"+ COL_STATUS+" INTEGER,"+ COL_X+" INTEGER,"+ COL_Y+" INTEGER)";
        p0?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun insertData(device: Device) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, device.name)
        cv.put(COL_TYPE, device.rssi)
        cv.put(COL_RSSI, device.status)
        cv.put(COL_STATUS, device.type)
        cv.put(COL_X, device.x)
        cv.put(COL_Y, device.y)
        var result = db.insert(DEVICE_TABLE_NAME, null, cv)
        if (result==-1.toLong()) {
            Toast.makeText(context, "Fail - Devices Cannot Have The Same Name!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun readDevices() : MutableList<Device> {
        var list: MutableList<Device> = ArrayList()

        val db = this.readableDatabase
        val queryHandler = "Select * from " + DEVICE_TABLE_NAME
        val result = db.rawQuery(queryHandler, null)
        if (result.moveToFirst()) {
            do {
                var device = Device()
                device.id = result.getString(0).toInt()
                device.name = result.getString(1).toString()
                device.type = result.getString(2).toInt()
                device.rssi = result.getString(3).toInt()
                device.status = result.getString(4).toInt()
                device.x = result.getString(5).toInt()
                device.y = result.getString(6).toInt()
                list.add(device)
            } while (result.moveToNext())
        }

        return list

    }

}

//var name : String = ""
//var type : Int = 0
//var rssi : Int = 0
//var status : Int = 0
//var x : Int = 0
//var y : Int = 0
