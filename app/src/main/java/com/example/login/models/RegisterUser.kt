package com.example.login.models

import java.io.Serializable

data class RegisterUser(
    val id: String,
    var name: String,
    var lastName:String,
    val email:String,
    var pass:String
) : Serializable {}