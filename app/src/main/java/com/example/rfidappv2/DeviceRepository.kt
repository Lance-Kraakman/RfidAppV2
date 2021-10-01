package com.example.rfidappv2

import androidx.lifecycle.MutableLiveData

class DeviceRepository  {

    var device: Device = Device()

    constructor() {
        // Not a list only single device for example
        this.device = Device()
    }

//    fun getMutableDevice() : ArrayList<Device> {
//        sdfsdf: ArrayList<Device>() = ArrayList<Device>(Device())
//    }

    fun changeDevice() {
        var customDevice : Device = Device("new device",-9,-9)
        this.device = customDevice
    }

}