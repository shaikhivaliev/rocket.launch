package com.example.rocketlaunch.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Launch(
    @SerializedName("id") val launchId: Int,
    @SerializedName("name") val launchName: String,
    @SerializedName("windowstart") val launchWindowStart: String,
    @SerializedName("windowend") val launchWindowEnd: String,
    @SerializedName("rocket") val rocket: Rocket,
    @SerializedName("missions") val missions: MutableList<Mission>
) : Serializable