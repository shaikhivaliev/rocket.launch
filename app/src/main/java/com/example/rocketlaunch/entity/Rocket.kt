package com.example.rocketlaunch.entity

import com.google.gson.annotations.SerializedName

data class Rocket(

    @SerializedName("id") val rocketId: Int,
    @SerializedName("name") val rocketName: String,
    @SerializedName("imageURL") val rocketImageURL: String

    )