package com.example.rocketlaunch.presentation

import android.util.Log
import android.widget.Toast
import com.example.rocketlaunch.data.ApiUtils
import com.example.rocketlaunch.entity.LaunchResponce
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LaunchListPresenter(val view: LaunchListView) {

    fun onShowLaunches() {

        Log.d("TIMER", "getLaunches")

        ApiUtils.getApiService()?.getLaunches()?.enqueue(object : Callback<LaunchResponce> {
            override fun onFailure(call: Call<LaunchResponce>, t: Throwable) {
                view.showError()
            }

            override fun onResponse(call: Call<LaunchResponce>, response: Response<LaunchResponce>) {
                view.showLaunches(response.body()?.launches)
                view.startTimer(response.body()?.launches?.get(0)?.launchWindowStart)

            }
        })


    }


}