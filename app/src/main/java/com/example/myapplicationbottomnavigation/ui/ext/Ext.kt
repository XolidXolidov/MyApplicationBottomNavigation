package com.example.myapplicationbottomnavigation.ui.ext

import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: Uri?) {
    Glide.with(this).load(url).into(this)
}

fun Fragment.showToast(msg:String) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}