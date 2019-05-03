package com.example.rocketlaunch.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rocketlaunch.R
import com.example.rocketlaunch.entity.Launch
import kotlinx.android.synthetic.main.item_launch.view.*

class LaunchAdapter : RecyclerView.Adapter<LaunchAdapter.ViewHolder>() {

    private var launchList: MutableList<Launch> = ArrayList()
    private lateinit var listener: OnItemLaunchListClickListener

    fun addData(data: MutableList<Launch>?) {
        launchList.addAll(data!!)
        notifyDataSetChanged()
    }

    fun setListener(onItemClickListener: OnItemLaunchListClickListener) {
        listener = onItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_launch, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = launchList.size


    override fun onBindViewHolder(launchHolder: ViewHolder, position: Int) {
        val launch: Launch = launchList[position]
        launchHolder.bind(launch, listener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(launch: Launch, listener: OnItemLaunchListClickListener) {

            itemView.tv_launch_name.text = launch.launchName
            itemView.tv_window_open.text = launch.launchWindowStart
            itemView.tv_window_close.text = launch.launchWindowEnd

            itemView.setOnClickListener {
                listener.onClick(launch.launchId)
            }
        }
    }

}