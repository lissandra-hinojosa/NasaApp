package com.example.login.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApodNasa(
    val copyright: String,
    val date: String,
    val explanation: String,
    //Allows to use another name for the Json object in the app environment
    @SerializedName("hdurl") val highDefImage:String, //hdurl is Json name, highDefImage used in the app
    val media_type: String,
    val service_version: String,
    val title:String,
    val url:String
    ): Serializable {
}