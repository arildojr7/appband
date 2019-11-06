package com.arildojr.appband.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arildojr.appband.R
import com.arildojr.appband.core.base.BaseActivity
import com.arildojr.appband.core.home.HomeFragment
import com.arildojr.appband.songs.SongsFragment
import com.arildojr.appband.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    enum class MenuEnum { HOME, SONGS, FAVORITE, PROFILE }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    setFragment(MenuEnum.HOME)
                }
                R.id.menu_songs -> {
                    setFragment(MenuEnum.SONGS)
                }
            }
            true
        }

        setFragment(MenuEnum.HOME)
    }

    private fun setFragment(fragment: Fragment, TAG: String) {
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.animator.fade_in, R.animator.fade_out)
            replace(R.id.flContent, fragment, TAG).commitAllowingStateLoss()
        }
    }

    private fun setFragment(menu: MenuEnum) = when (menu) {
        MenuEnum.HOME -> {
            var fragment = supportFragmentManager.findFragmentByTag(MenuEnum.HOME.name)

            if (fragment != null) {
                (fragment as HomeFragment).scrollToTop()
            } else {
                fragment = HomeFragment()
                setFragment(fragment, MenuEnum.HOME.name)
            }
        }

        MenuEnum.SONGS -> {
            var fragment = supportFragmentManager.findFragmentByTag(MenuEnum.SONGS.name)

            if (fragment != null) {
                (fragment as SongsFragment).scrollToTop()
            } else {
                fragment = SongsFragment()
                setFragment(fragment, MenuEnum.SONGS.name)
            }
        }

        MenuEnum.FAVORITE -> {

        }

        MenuEnum.PROFILE -> {

        }
    }



}
