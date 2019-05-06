package com.example.rocketlaunch.map

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rocketlaunch.R
import com.example.rocketlaunch.entity.Launch
import com.example.rocketlaunch.entity.Pad
import com.example.rocketlaunch.entity.PadResponce
import com.example.rocketlaunch.server.ApiUtils
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapFragment : Fragment(), OnMapReadyCallback {

    private var googleMap: GoogleMap? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_map, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap
        setMarkers()
    }

    private fun setMarkers() {
        ApiUtils.getApiService()?.getLaunchLocations()?.enqueue(object : Callback<PadResponce> {
            override fun onFailure(call: Call<PadResponce>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<PadResponce>, response: Response<PadResponce>) {

                val pads: MutableList<Pad> = response.body()?.pads ?: return
                for (pad: Pad in pads) {

                    val marker = LatLng(pad.latitude.toDouble(), pad.longitude.toDouble())
                    googleMap?.addMarker(MarkerOptions().position(marker).title(pad.name))
                }
            }
        }
        )
    }


}