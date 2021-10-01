package com.example.rfidappv2.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rfidappv2.DataBaseHandler
import com.example.rfidappv2.Device
import kotlinx.coroutines.launch
import com.example.rfidappv2.AddDevice
import com.example.rfidappv2.DeviceRepository

class DashboardViewModel : ViewModel() {

    var deviceRepository : DeviceRepository = DeviceRepository()

    private var _device = MutableLiveData<Device>()
    val device: LiveData<Device> = _device

//    init {
//        _device = deviceRepository.device
//    }

    public var _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    var text: MutableLiveData<String> = _text


}

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is dashboard Fragment"
//    }
//
//    public val _displayQueriedDevices = MutableLiveData<List<Device>>().apply {
//        value = ArrayList() // The initial list is an empty array list
//    }
//    public val _displayAllDevices = MutableLiveData<List<Device>>().apply {
//        value = ArrayList()
//    }

    

//    val text: MutableLiveData<String> = _text
//    val displayAllDevices: MutableLiveData<List<Device>> = _displayAllDevices
//    val displayQueriedDevices: MutableLiveData<List<Device>> = _displayQueriedDevices

 //   public fun updateDisplayAllDevices(deviceList : List<Device>) {
//        viewModelScope.launch {
//            _displayAllDevices.apply {
//                value = deviceList
//            }
//            Log.i("fuck", "-------------------------------------------------")
//            Log.i("off",_displayAllDevices.value.toString())
//            Log.i("off",displayAllDevices.value.toString())
//            _displayAllDevices.value = deviceList
//        }
//        _displayAllDevices.value = deviceList
//    }


//}