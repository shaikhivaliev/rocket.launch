package com.example.rocketlaunch.server

import com.example.rocketlaunch.entity.LaunchResponce
import retrofit2.Call
import retrofit2.http.GET

interface ApiLaunch {


    @GET("/1.4/launch")
    fun getLaunches(): Call<LaunchResponce>


}