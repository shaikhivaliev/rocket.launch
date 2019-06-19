package com.example.rocketlaunch.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.rocketlaunch.R
import com.example.rocketlaunch.ui.launches.LaunchList
import com.example.rocketlaunch.ui.map.MapFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) changeFragment(LaunchList())

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {

            R.id.item_map -> {
                changeFragment(MapFragment())
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }


    private fun changeFragment(fragment: Fragment) {

        Log.d("TIMER", "changeFragment")


        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(fragment::javaClass.name)
            .commit()

    }
}
