package com.example.rfidappv2.models

class Users {
    lateinit var id: String
    lateinit var email:String

    constructor(id: String, email: String) {
        this.id = id
        this.email = email
    }
}