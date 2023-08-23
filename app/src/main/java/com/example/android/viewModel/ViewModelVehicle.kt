package com.example.android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.model.VehicleList
import com.example.android.model.Vehicles
import kotlinx.coroutines.launch

class ViewModelVehicle: ViewModel() {

    var vehiclelist = MutableLiveData<List<Vehicles>>()
    var vehicleapi = VehicleRetrofitHelper().getVehicleInstance().create(VehicleApi::class.java)

    fun getVehicleData() {
        try {
            viewModelScope.launch {
                var res = vehicleapi.getVehicle()
                vehiclelist.value = res.body()?.Results
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }
}