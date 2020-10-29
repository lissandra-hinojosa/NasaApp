package com.example.login

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

class ErrorInject {

    fun Fragment.getContext(): Context {
        return requireContext()
    }
}