package com.example.android.model

import android.widget.ImageView
import com.bumptech.glide.Glide
import retrofit2.http.Url

fun ImageView.setImage(url: String)
{
    Glide.with(this)
        .load(url).into(this)
}