package com.example.rocketlaunch.ui.launch

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rocketlaunch.R
import com.example.rocketlaunch.data.ApiUtils
import com.example.rocketlaunch.entity.Launch
import com.example.rocketlaunch.entity.LaunchResponce
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail_image.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Detail : Fragment() {

    companion object {
        private const val TAB_IMAGE = 0
        private const val TAB_INFO = 1

        private val DETAIL_FRAGMENT_KEY = "DETAIL_FRAGMENT_KEY"
        fun newInstance(launchId: Int): Detail {
            val args = Bundle()
            args.putSerializable(DETAIL_FRAGMENT_KEY, launchId)
            val fragment = Detail()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getInt(DETAIL_FRAGMENT_KEY)?.let {
            val launchId: Int = it
            getDetailLaunch(launchId)
        }
    }


    fun getDetailLaunch(launchId: Int) {
        ApiUtils.getApiService()?.getLauncheDetail(launchId)?.enqueue(object : Callback<LaunchResponce> {
            override fun onFailure(call: Call<LaunchResponce>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<LaunchResponce>, response: Response<LaunchResponce>) {

                if (response.body() == null) return
                val launch: Launch = response.body()?.launches?.get(0)!!

                tab_layout.setupWithViewPager(viewPager)
                viewPager.adapter = DetailPagesAdapter(launch)

            }
        })
    }

    private inner class DetailPagesAdapter(val launch: Launch) : FragmentPagerAdapter(childFragmentManager) {

        override fun getItem(position: Int): Fragment = when (position) {
            TAB_IMAGE -> DetailImage.newInstance(launch.rocket.rocketImageURL)
            else -> DetailInformation.newInstance(launch)
        }

        override fun getCount(): Int = 2

        override fun getPageTitle(position: Int) = when (position) {
            TAB_IMAGE -> getString(R.string.detail_image)
            TAB_INFO -> getString(R.string.detail_text)
            else -> null
        }
    }


}