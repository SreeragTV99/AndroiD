package com.example.android.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("username")
    var username:String,
    @SerializedName("password")
    var password:String
)
data class LoginRes(
    var id:Int,
    var username:String,
    var message:String
)