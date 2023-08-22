package com.example.android.model

data class VehicleList(
    val Count: Int,
    val Message: String,
    val SearchCriteria: String,
    val Results: List<Vehicles>
)
