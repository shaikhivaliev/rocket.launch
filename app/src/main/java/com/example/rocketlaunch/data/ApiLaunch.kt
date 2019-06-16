package com.example.rocketlaunch.data

import com.example.rocketlaunch.entity.LaunchResponce
import com.example.rocketlaunch.entity.PadResponce
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiLaunch {


    @GET("/1.4/launch")
    fun getLaunches(): Call<LaunchResponce>

    @GET("/1.4/pad")
    fun getLaunchLocations(): Call<PadResponce>

    @GET("/1.4/launch/{launchId}")
    fun getLauncheDetail(@Path("launchId") launchId: Int): Call<LaunchResponce>

}