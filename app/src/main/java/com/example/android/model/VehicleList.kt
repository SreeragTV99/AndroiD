package com.example.android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VehicleList(
    val Count: Int,
    val Message: String,
    val SearchCriteria: String,
    val Results: List<Vehicles>
):Parcelable


