package com.example.android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.model.Vehicles
import kotlinx.coroutines.launch

class ViewModelVehicle: ViewModel() {

    var vehicleList = MutableLiveData<List<Vehicles>>()
    private var vehicleApi = RetrofitHelper().getVehicleInstance().create(VehicleApi::class.java)

    fun getVehicleData() {
        try {
            viewModelScope.launch {
                var res = vehicleApi.getVehicle()
                vehicleList.value = res.body()?.Results
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }
}