package com.example.rocketlaunch.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.rocketlaunch.R
import com.example.rocketlaunch.entity.Launch
import com.example.rocketlaunch.entity.LaunchResponce
import com.example.rocketlaunch.server.ApiUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_launch_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LaunchDetail : Fragment() {

    companion object {
        private val DETAIL_FRAGMENT_KEY = "DETAIL_FRAGMENT_KEY"

        fun newInstance(launchId: Int): LaunchDetail {
            val args = Bundle()
            args.putSerializable(DETAIL_FRAGMENT_KEY, launchId)
            val fragment = LaunchDetail()
            fragment.arguments = args
            return fragment

        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        arguments?.getInt(DETAIL_FRAGMENT_KEY)?.let {
            val launchId: Int = it
            getDetailLaunch(launchId)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_launch_detail, container, false)


    fun getDetailLaunch(launchId: Int) {
        ApiUtils.getApiService()?.getLauncheDetail(launchId)?.enqueue(object : Callback<LaunchResponce> {
            override fun onFailure(call: Call<LaunchResponce>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<LaunchResponce>, response: Response<LaunchResponce>) {

                if (response.body() == null) return
                val launch: Launch = response.body()?.launches?.get(0)!!


                initUI(launch)
            }
        })
    }

    fun initUI(launch: Launch) {

        val imageUrl: String = launch.rocket.rocketImageURL
        Picasso.get().load(imageUrl).into(iv_detail_fragment)

    }

}