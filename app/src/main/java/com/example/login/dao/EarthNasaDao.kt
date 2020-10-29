package com.example.login.dao

import com.example.login.models.EarthNasa

class EarthNasaDao private constructor(val status: Status, val earthNasa: EarthNasa?, val error: String?) {

    companion object {
        fun success(earthNasa: EarthNasa) = EarthNasaDao(Status.SUCCESS, earthNasa,null)
        fun error(error: String?) = EarthNasaDao(Status.ERROR, null, error)
    }
}