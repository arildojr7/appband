package com.arildojr.appband.main

import android.os.Bundle
import android.util.Log
import com.arildojr.appband.R
import com.arildojr.appband.core.base.BaseActivity
import com.arildojr.appband.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    Log.e(">>>>> ", ":")
                }
            }
            true
        }
    }


}
