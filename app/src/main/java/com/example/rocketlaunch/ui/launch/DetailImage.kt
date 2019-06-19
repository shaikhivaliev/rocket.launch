package com.example.rocketlaunch.ui.launch

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rocketlaunch.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_image.*

class DetailImage : Fragment() {

    companion object {
        private const val DETAIL_IMAGE_KEY = "DETAIL_IMAGE_KEY"
        fun newInstance(imageUrl: String): DetailImage {
            val args = Bundle()
            args.putSerializable(DETAIL_IMAGE_KEY, imageUrl)
            val fragment = DetailImage()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_detail_image, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getString(DETAIL_IMAGE_KEY)?.let {
            val imageUrl: String = it
            setUI(imageUrl)
        }

    }


    fun setUI(imageUrl: String) {
        Picasso.get().load(imageUrl).into(launch_detail_background)
    }


}