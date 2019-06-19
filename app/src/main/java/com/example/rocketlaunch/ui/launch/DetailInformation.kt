package com.example.rocketlaunch.ui.launch

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rocketlaunch.R
import com.example.rocketlaunch.entity.Launch
import kotlinx.android.synthetic.main.fragment_detail_information.*

class DetailInformation : Fragment() {

    companion object {
        private const val DETAIL_INFO_KEY = "DETAIL_INFO_KEY"
        fun newInstance(launch: Launch): DetailInformation {
            val args = Bundle()
            args.putSerializable(DETAIL_INFO_KEY, launch)
            val fragment = DetailInformation()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_detail_information, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getSerializable(DETAIL_INFO_KEY)?.let {
            val launch: Launch = it as Launch
            setUI(launch)
        }

    }


    fun setUI(launch: Launch) {
        launch_detail_rocket.text = launch.rocket.rocketName
        launch_detail_date.text = launch.launchWindowStart
        if (launch.missions.isNotEmpty()) launch_detail_description.text = launch.missions[0].description
    }

}