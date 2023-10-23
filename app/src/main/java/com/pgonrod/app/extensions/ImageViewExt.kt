package com.pgonrod.app.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadurl(image: String){
    Glide.with(this).load(image).into(this)
}