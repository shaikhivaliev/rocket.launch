package com.example.rocketlaunch.entity

import com.google.gson.annotations.SerializedName

data class Pad(
    @SerializedName("locationId") val locationId: Int,
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String,
    @SerializedName("name") val name: String

)