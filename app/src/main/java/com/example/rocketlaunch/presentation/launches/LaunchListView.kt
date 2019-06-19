package com.example.rocketlaunch.presentation

import com.example.rocketlaunch.entity.Launch

interface LaunchListView {

    fun showLaunches(launches: MutableList<Launch>?)
    fun startTimer(windowStart: String?)
    fun showError()

}