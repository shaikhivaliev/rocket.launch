package com.example.rocketlaunch.ui

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rocketlaunch.entity.LaunchResponce
import com.example.rocketlaunch.server.ApiUtils
import kotlinx.android.synthetic.main.fragment_launch_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.synthetic.main.item_countdown.*


class LaunchList : Fragment(), OnItemLaunchListClickListener {

    private lateinit var adapter: LaunchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(com.example.rocketlaunch.R.layout.fragment_launch_list, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = LaunchAdapter()
        adapter.setListener(this)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter


        getLaunches()

    }

    private fun getLaunches() {


        ApiUtils.getApiService()?.getLaunches()?.enqueue(object : Callback<LaunchResponce> {
            override fun onFailure(call: Call<LaunchResponce>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<LaunchResponce>, response: Response<LaunchResponce>) {

                adapter.addData(response.body()?.launches)

                var timer = Timer()
                timer.scheduleAtFixedRate(object : TimerTask() {
                    override fun run() {
                        activity?.runOnUiThread {
                            initCountdown(response.body()?.launches?.get(0)?.launchWindowStart!!)
                        }
                    }
                }, 0, 1000)
            }
        })

    }


    override fun onClick(launchId: Int) {

        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(com.example.rocketlaunch.R.id.container, LaunchDetail.newInstance(launchId))
            ?.addToBackStack(LaunchDetail::javaClass.name)
            ?.commit()
    }

    fun initCountdown(windowStart: String) {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val windowStart = dateFormat.parse(windowStart)
        val currentDate = Date()

        var diff = windowStart.time - currentDate.time
        val days = diff / (24 * 60 * 60 * 1000)
        diff -= days * (24 * 60 * 60 * 1000)
        val hours = diff / (60 * 60 * 1000)
        diff -= hours * (60 * 60 * 1000)
        val minutes = diff / (60 * 1000)
        diff -= minutes * (60 * 1000)
        val seconds = diff / 1000

        tv_countdown_day.text = days.toString() + " DAYS"
        tv_countdown_hour.text = hours.toString() + " HRS"
        tv_countdown_minute.text = minutes.toString() + " MINS"
        tv_countdown_second.text = seconds.toString() + " SECS"

    }

}

