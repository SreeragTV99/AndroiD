package com.example.android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Types(
    val isPrimary: String,
    val Name: String
): Parcelable
