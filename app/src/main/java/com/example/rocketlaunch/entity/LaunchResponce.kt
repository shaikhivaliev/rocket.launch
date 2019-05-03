package com.example.rocketlaunch.entity

import com.google.gson.annotations.SerializedName

data class LaunchResponce(

    @SerializedName("launches") val launches: MutableList<Launch>

)