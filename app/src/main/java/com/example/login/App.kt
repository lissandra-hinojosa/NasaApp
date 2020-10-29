package com.example.login

import android.app.Application
import com.example.login.di.applicationModule
import com.example.login.di.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(applicationModule, serviceModule)
        }
    }
}