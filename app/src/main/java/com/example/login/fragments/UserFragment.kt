package com.example.login.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.login.R
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment: Fragment() {

    private val OPEN_GALLERY = 1
    private val TAKE_PHOTO = 2
    private val args: UserFragmentArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
//        userName.text = "Welcome $args.user.name"
        args.user.name.apply {
            userName.text = getString(R.string.welcome) +" $this"
        }
        takePhotoButton.setOnClickListener{takePhoto()}
        openImageButton.setOnClickListener{openGallery()}

    }

    private fun takePhoto(){
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            startActivityForResult(it,TAKE_PHOTO)
        }
    }

    private fun openGallery(){
        //Invoke image gallery. Implicit Intent.
        Intent(Intent.ACTION_PICK).also { photoPickerIntent->
            //Path for Pictures data on SD Card
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).also { pictureDirectory ->
                //Parse path to uri
                Uri.parse(pictureDirectory.path).also { uriData ->
                    //Where to look for the media - Get all types of images
                    photoPickerIntent.setDataAndType(uriData,"image/*")
                }
            }
            startActivityForResult(photoPickerIntent,OPEN_GALLERY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode ==  Activity.RESULT_OK){
            when(requestCode){
                OPEN_GALLERY-> {
                    Glide.with(this)
                        .load(data?.data)
                        .fitCenter()
                        .into(profilePicture)
                }
                TAKE_PHOTO-> {
                    Glide.with(this)
                        .load(data?.extras?.get("data"))
                        .fitCenter()
                        .into(profilePicture)
                }
            }
        }
    }
}