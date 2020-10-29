package com.example.login.services

import retrofit2.Retrofit
import retrofit2.create

//Bridge between interface and viewModel
class NasaService(retrofit: Retrofit) { //Inject retrofit

    private val apiKey = "4hroX0lmGPQrifpHIb1ZH0SlLuwkdyD5AfIi5aj6"
    //Create a retrofit variable that uses Api interface
    private val service = retrofit.create(NasaApi::class.java)

    fun getApod() = service.getApod("apod", apiKey)
    fun getEarth() = service.getEarth("100.75", "1.5", "2014-02-01", true, apiKey)
}


