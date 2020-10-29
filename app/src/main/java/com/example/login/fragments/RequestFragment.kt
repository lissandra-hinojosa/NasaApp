package com.example.login.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.login.R
import com.example.login.dao.Status
import com.example.login.viewmodels.NasaViewModel
import kotlinx.android.synthetic.main.fragment_request.*
import kotlinx.android.synthetic.main.item_apod.*
import kotlinx.android.synthetic.main.item_earth.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

//import com.example.login.R


class RequestFragment: Fragment() {

    private val nasaViewModel by viewModel<NasaViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        hideElements()
        progressRequest.visibility = View.INVISIBLE
        buttonApod.setOnClickListener{getApodNasa()}
        buttonEarth.setOnClickListener{getEarthNasa()}
        triggers()
    }

    private fun triggers(){
        nasaViewModel.resultApod.observe(viewLifecycleOwner,androidx.lifecycle.Observer {
            it?.also{ apod ->
                when(apod.status) {
                    Status.SUCCESS -> {
                        apod.apodNasa?.also { result ->
                            textRequest.text = getString(R.string.apod)
                            apodTitle.text = result.title
                            apodDate.text = result.date
                            apodExplanation.text = result.explanation
                            Glide.with(this)
                                .load(result.url)
                                .placeholder(R.drawable.ic_error_outline_black_24dp)
                                .into(apodImage)
                            apodItem.visibility = View.VISIBLE
                        }
                    }
                    Status.ERROR->{
                        Toast.makeText(view!!.context, apod.error?:"", Toast.LENGTH_SHORT).show()
                    }
                }
                progressRequest.visibility = View.INVISIBLE
            }
        })

        nasaViewModel.resultEarth.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.also{earth ->
                when(earth.status) {
                    Status.SUCCESS -> {
                        earth.earthNasa?.also { result ->
                            textRequest.text = getString(R.string.earth)
                            textRequest.text = getString(R.string.earth)
                            earthId.text = result.id
                            earthDate.text = result.date
                            planet.text = result.resource.planet
                            earthItem.visibility = View.VISIBLE
                        }
                    }
                    Status.ERROR ->{
                        Toast.makeText(view!!.context, earth.error?:"", Toast.LENGTH_SHORT).show()
                    }
                }
                progressRequest.visibility = View.INVISIBLE
            }
        })
    }

    private fun getApodNasa() {
        hideElements()
        progressRequest.visibility = View.VISIBLE
        nasaViewModel.callApodService()
    }
    private fun getEarthNasa(){
        hideElements()
        progressRequest.visibility = View.VISIBLE
        nasaViewModel.callEarthService()
    }

    private fun hideElements(){
        apodItem.visibility = View.INVISIBLE
        earthItem.visibility = View.INVISIBLE
    }
}