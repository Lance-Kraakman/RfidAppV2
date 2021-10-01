package com.example.rfidappv2

class Device {
    var name : String = ""
    var type : Int = 0
    var rssi : Int = 0
    var status : Int = 0
    var x : Int = 0
    var y : Int = 0
    var id : Int = 0

    constructor(name:String, type:Int,status:Int) {
        this.name = name
        this.type = type
        this.rssi = 0
        this.status = status
        this.x = -1
        this.y = -1
        this.id = 0
    }

    constructor() {} //empty constructor for loop

}