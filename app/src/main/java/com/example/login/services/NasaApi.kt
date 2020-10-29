package com.example.login.services

import com.example.login.models.ApodNasa
import com.example.login.models.EarthNasa
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//Interface implemented by a service
interface NasaApi {

    //Just one dynamic element allowed
    //{type} path
    @GET("planetary/{type}") //2 paths, 1 query
    //Takes upper GET
    fun getApod(@Path("type")type: String, @Query("api_key")apiKey: String): Call<ApodNasa>//Asynchronous retrofit object

    @GET("planetary/earth/imagery/")
    fun getEarth(@Query("lon")lon: String,
                 @Query("lat")lat: String,
                 @Query("date") date: String,
                 @Query("cloud_score") cloud_score: Boolean,
                 @Query("api_key")apiKey: String): Call<EarthNasa>
}