package com.example.rocketlaunch.ui.launches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rocketlaunch.R
import com.example.rocketlaunch.entity.Launch
import com.example.rocketlaunch.presentation.LaunchListPresenter
import com.example.rocketlaunch.presentation.LaunchListView
import com.example.rocketlaunch.ui.LaunchAdapter
import com.example.rocketlaunch.ui.OnItemLaunchListClickListener
import com.example.rocketlaunch.ui.launch.Detail
import com.example.rocketlaunch.ui.launch.DetailImage
import kotlinx.android.synthetic.main.fragment_launch_list.*
import kotlinx.android.synthetic.main.item_countdown.*
import java.text.SimpleDateFormat
import java.util.*


class LaunchList : Fragment(), OnItemLaunchListClickListener, LaunchListView {

    private lateinit var adapter: LaunchAdapter
    private lateinit var presenter: LaunchListPresenter
    private var stop: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_launch_list, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stop = false
        adapter = LaunchAdapter()
        adapter.setListener(this)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        presenter = LaunchListPresenter(this)

        presenter.onShowLaunches()

    }

    override fun showLaunches(launches: MutableList<Launch>?) {
        adapter.addData(launches)
    }

    override fun startTimer(windowStart: String?) {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if (stop) {
                    return
                }
                activity?.runOnUiThread {
                    initCountdown(windowStart)
                }
            }

        }, 0, 1000)
    }


    fun initCountdown(windowStart: String?) {
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

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onClick(launchId: Int) {

        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.container, Detail.newInstance(launchId))
            ?.addToBackStack(DetailImage::javaClass.name)
            ?.commit()
    }


    override fun onStop() {
        super.onStop()
        Log.d("TIMER", "onStop")
        stop = true
    }

}

