package com.example.android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.model.Vehicles
import kotlinx.coroutines.launch

class ViewModelVehicle: ViewModel() {

    val vehicleList = MutableLiveData<List<Vehicles>>()
    private val vehicleApi = RetrofitHelper().getVehicleInstance().create(VehicleApi::class.java)

    fun getVehicleData() {
        viewModelScope.launch {
            val res = vehicleApi.getVehicle()
            vehicleList.value = res.body()?.Results
        }
    }
}