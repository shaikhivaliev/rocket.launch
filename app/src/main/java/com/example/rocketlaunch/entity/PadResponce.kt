package com.example.rocketlaunch.entity

import com.google.gson.annotations.SerializedName

data class PadResponce(

    @SerializedName("pads") val pads: MutableList<Pad>

)