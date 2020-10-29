package com.example.login.models

import java.io.Serializable

data class EarthNasaResource(
    val dataset: String,
    val planet: String
): Serializable