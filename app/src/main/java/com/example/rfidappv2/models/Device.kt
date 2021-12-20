package com.example.rfidappv2.models

class Device {
    //  lateinit var id: String
    lateinit var uuid: String
    lateinit var name: String
    lateinit var x_location: String
    lateinit var y_location: String
    lateinit var station_id: String
    lateinit var temp: String
    lateinit var is_register: String
    lateinit var time_update_esp: String

    constructor(
        // id: String,
        uuid: String,
        name: String,
        x_location: String,
        y_location: String,
        station_id: String,
        temp: String,
        is_register: String,
        time_update_esp: String
    ) {
        // this.id = id
        this.uuid = uuid
        this.name = name
        this.x_location = x_location
        this.y_location = y_location
        this.station_id = station_id
        this.temp = temp
        this.is_register = is_register
        this.time_update_esp = time_update_esp
    }

    constructor()
    constructor(s: String, s1: String, s2: String, s3: String, s4: String, s5: String, s6: String, s7: String, s8: String)
}