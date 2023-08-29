package com.example.android.viewModel
import com.example.android.model.LoginRequest
import com.example.android.model.LoginRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersApi {
    @POST("auth/login")
    suspend fun getUsers(@Body loginRequest: LoginRequest):Response<LoginRes>
}