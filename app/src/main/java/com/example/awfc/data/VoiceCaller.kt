package com.example.awfc.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VoiceCaller(
    val callerName: String,
    val callerMobile: String
) : Parcelable
