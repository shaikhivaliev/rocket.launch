package com.example.rocketlaunch.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.rocketlaunch.BuildConfig
import com.example.rocketlaunch.R
import com.example.rocketlaunch.entity.LaunchResponce
import com.example.rocketlaunch.server.ApiLaunch
import com.example.rocketlaunch.server.ApiUtils
import kotlinx.android.synthetic.main.fragment_launch_list.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LaunchList : Fragment(), OnItemLaunchListClickListener {

    private lateinit var adapter: LaunchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_launch_list, container, false)


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

            }
        })


    }


    override fun onClick(launchId: Int) {

        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.container, LaunchDetail.newInstance(launchId))
            ?.addToBackStack(LaunchDetail::javaClass.name)
            ?.commit()
    }

}

