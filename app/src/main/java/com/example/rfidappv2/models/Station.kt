package com.example.rfidappv2.models

class Station {
    constructor(id: String, uuid: String, name: String, height: String, width: String) {
        this.id = id
        this.uuid = uuid
        this.name = name
        this.height = height
        this.width = width
    }

    constructor()

    lateinit var id: String
    lateinit var uuid: String
    lateinit var name: String
    lateinit var height: String
    lateinit var width: String

}