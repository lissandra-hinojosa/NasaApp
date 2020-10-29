package com.example.login.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login.dao.ApodNasaDao
import com.example.login.dao.EarthNasaDao
import com.example.login.models.ApodNasa
import com.example.login.models.EarthNasa
import com.example.login.services.NasaService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//MVVM / Model View View Model
//Private val - lets us use variables outside constructor and init function
/* Use functions as parameters
 getResult:()-> Unit //Parameter : Type of element (Function). Unit == Void
*/
class NasaViewModel(private val nasaService: NasaService): ViewModel() {

    val resultApod = MutableLiveData<ApodNasaDao>()
    val resultEarth = MutableLiveData<EarthNasaDao>()
//    enum class TYPE_SERVICE{ APOD, EARTH}

    //Uses the Service class to perform the call action
    fun callApodService(){
        nasaService.getApod() //Service Class
            .enqueue(object : Callback<ApodNasa> {
                //It is not necessarily a 400 or 500 error
                override fun onFailure(call: Call<ApodNasa>, t: Throwable) {
//                    println(t.localizedMessage)
                    resultApod.value = ApodNasaDao.error(t.localizedMessage)
                }
                //Returns something
                override fun onResponse(call: Call<ApodNasa>, response: Response<ApodNasa>) {
                    //println(response.isSuccessful)
                    if(response.isSuccessful)
                        resultApod.value = ApodNasaDao.success(response.body()!!)
                }
            }) //Returns call
    }

    fun callEarthService(){
        nasaService.getEarth() //Service Class
            .enqueue(object : Callback<EarthNasa>{
                override fun onFailure(call: Call<EarthNasa>, t: Throwable) {
//                    println(t.localizedMessage)
                    resultEarth.value = EarthNasaDao.error(t.localizedMessage)
                }
                override fun onResponse(call: Call<EarthNasa>, response: Response<EarthNasa>) {
                    if(response.isSuccessful)
                        resultEarth.value = EarthNasaDao.success(response.body()!!)
                }
            })
    }


}