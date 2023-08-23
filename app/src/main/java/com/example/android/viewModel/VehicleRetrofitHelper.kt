package com.example.android.viewModel

import com.example.android.Constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VehicleRetrofitHelper {

    fun getVehicleInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL_CARS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}