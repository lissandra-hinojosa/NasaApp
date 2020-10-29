package com.example.login.models

import java.io.Serializable

data class EarthNasa(
    val cloud_score: Float,
    val date: String,
    val id: String,
    val resource: EarthNasaResource,
    val service_version: String,
    val url: String
):Serializable