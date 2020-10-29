package com.example.login.di

import com.example.login.ErrorInject
import com.example.login.services.NasaService
import com.example.login.viewmodels.LogInViewModel
import com.example.login.viewmodels.NasaViewModel
import com.example.login.viewmodels.RegisterViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val applicationModule = module {
    viewModel{ LogInViewModel()}
    viewModel { RegisterViewModel( context = androidContext())}
    viewModel { NasaViewModel(nasaService = get()) }
    single{ ErrorInject()}

}

//Tell koin to initilize it in "App"
val serviceModule = module{
    single { NasaService(getRetrofit("https://api.nasa.gov/")) }
}

fun getRetrofit(baseUrl: String): Retrofit{
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(OkHttpClient()) //Waiter between server and retrofit
        .addConverterFactory(GsonConverterFactory.create()) //Convert retrofit instances from GSON to a class
        .build()
    //Interceptor. Used to debbug. Listens client (OkHttpClient)
}