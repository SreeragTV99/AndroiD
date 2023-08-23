package com.example.android.viewModel

import com.example.android.model.VehicleList
import retrofit2.Response
import retrofit2.http.GET

interface VehicleApi {
    @GET("/api/vehicles/getallmanufacturers?format=json")
    suspend fun getVehicle() : Response<VehicleList>
}