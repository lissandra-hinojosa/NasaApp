package com.example.login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.login.R
import com.example.login.models.RegisterUser
import com.example.login.utils.toast
import com.example.login.viewmodels.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    private val registerViewModel by viewModel<RegisterViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        buttonRegister.setOnClickListener { validateData() }
        observeInput()
    }

    private fun observeInput() {

        registerViewModel.errorName.observe(viewLifecycleOwner, Observer { error ->
            error.also {
                inputRegisterName.error = error
            }
        })
        registerViewModel.errorLastName.observe(viewLifecycleOwner, Observer { error ->
            error.also {
                inputRegisterLastName.error = error
            }
        })
        registerViewModel.errorEmail.observe(viewLifecycleOwner, Observer { error ->
            error.also {
                inputRegisterEmail.error = error
            }
        })
        registerViewModel.errorPass.observe(viewLifecycleOwner, Observer { error ->
            error.also {
                inputRegisterPassword.error = error
                if(error.isEmpty())
                    inputConfirmPassword.error = error
                else
                    inputConfirmPassword.error = " "
            }
        })
    }

    private fun validateData(){
        registerViewModel.validate(
            inputRegisterNameText.text.toString(),
            inputRegisterLastNameText.text.toString(),
            inputRegisterEmailText.text.toString(),
            inputRegisterPasswordText.text.toString(),
            inputConfirmPasswordText.text.toString()
        )?.apply {
            actionEnter(this)
        }
    }

    private fun actionEnter(user:RegisterUser){
        val action = RegisterFragmentDirections.registerFragmentAction(user)
        findNavController().navigate(action)
    }
}