package com.example.login.viewmodels

import android.content.Context
import android.net.http.SslCertificate
import android.provider.Settings.Global.getString
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login.ErrorInject
import com.example.login.R
import com.example.login.models.RegisterUser
import org.w3c.dom.Text

class RegisterViewModel(private val context: Context) : ViewModel() {

    var errorName = MutableLiveData<String>()
    var errorLastName = MutableLiveData<String>()
    var errorEmail = MutableLiveData<String>()
    var errorPass = MutableLiveData<String>()

    var lastId:Int = 0

    fun validate(name: String, lastName: String, email: String, pass: String, confirmPass: String): RegisterUser? {
             val v = arrayListOf<Boolean>(
                 validateName(name), //true
                 validateLastName(lastName), //true
                 validateEmail(email), //false
                 validatePassword(pass,confirmPass)) //true
//             lastId++
        //Accumulates the results in the array and returns it
        return if(v.reduce(({ acc, b ->  acc && b })))
             RegisterUser(lastId.toString(), name, lastName, email, pass)
        else null
    }

    private fun validateName(name: String): Boolean {
//        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(lastName))
        return when {
            TextUtils.isEmpty(name) -> {
                errorName.value = " "
                false
            }
            else -> {
                errorName.value = ""
                true
            }
        }
    }

    private fun validateLastName(lastName: String): Boolean {
//        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(lastName))
        return when {
            TextUtils.isEmpty(lastName) -> {
                errorLastName.value = " "
                false
            }
            else -> {
                errorLastName.value = ""
                true
            }
        }
    }

    private fun validateEmail(email: String): Boolean {
        return if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorEmail.value = " "
            false
        } else {
            errorEmail.value = ""
            true
        }
    }

    private fun validatePassword(pass: String, confirmPass: String): Boolean {
        return if (TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirmPass)) {
            errorPass.value = "Missing"
            false
        } else if(!TextUtils.equals(pass, confirmPass)) {
            errorPass.value = "Passwords don't match"
            false
        }
        else{
            errorPass.value = ""
            true
        }
    }

}