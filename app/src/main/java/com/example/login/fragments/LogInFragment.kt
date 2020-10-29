package com.example.login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.login.R
import com.example.login.viewmodels.LogInViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController


class LogInFragment: Fragment() {

    private val logInViewModel by viewModel<LogInViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        textForgotPassword.setOnClickListener{ intentReestablishPasswordFragment()}
        textRegister.setOnClickListener{actionRegisterFragment()}
        buttonLogIn.setOnClickListener{showRequests()}
    }

    private fun intentReestablishPasswordFragment(){
//        Toast.makeText(view?.context,"Hola",Toast.LENGTH_SHORT).show()
        val action = LogInFragmentDirections.resetPasswordFragmentAction()
        findNavController().navigate(action)
    }

    private fun actionRegisterFragment(){
        val action = LogInFragmentDirections.logInFragmentAction()
        findNavController().navigate(action)
    }

    private fun showRequests(){
        val action = LogInFragmentDirections.requestAction()
        findNavController().navigate(action)
    }
}