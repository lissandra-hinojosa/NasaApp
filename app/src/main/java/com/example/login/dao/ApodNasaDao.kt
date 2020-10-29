package com.example.login.dao

import com.example.login.models.ApodNasa

//DATA ACCESS OBJECT
/*
  Lets us use a single live data variable for errors and successes
 */
//Nobody can access private constructor
//Just the elements inside the class, can access the constructor elements
class ApodNasaDao private constructor(val status: Status, val apodNasa: ApodNasa?, val error: String?) {

    //Everybody can access this elements
    companion object {
        fun success(apodNasa: ApodNasa) = ApodNasaDao(Status.SUCCESS, apodNasa, null)
        fun error(error: String) = ApodNasaDao(Status.ERROR, null, error)
    }
}