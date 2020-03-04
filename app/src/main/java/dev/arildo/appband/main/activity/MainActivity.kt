package dev.arildo.appband.main.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import dev.arildo.appband.R
import dev.arildo.appband.core.base.BaseActivity
import dev.arildo.appband.databinding.ActivityMainBinding
import dev.arildo.appband.home.fragment.HomeFragment
import dev.arildo.appband.setlist.fragment.SetListFragment
import dev.arildo.appband.songlist.fragment.SongsFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    enum class MenuEnum { HOME, SONGS, SET_LIST, PROFILE }

    private var actualFragment: MenuEnum? = null

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
                R.id.menu_set_list -> {
                    setFragment(MenuEnum.SET_LIST)
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

    private fun setFragment(menu: MenuEnum) {
        actualFragment = menu

        when (menu) {

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
                    fragment =
                        SongsFragment()
                    setFragment(fragment, MenuEnum.SONGS.name)
                }
            }

            MenuEnum.SET_LIST -> {
                var fragment = supportFragmentManager.findFragmentByTag(MenuEnum.SET_LIST.name)

                if (fragment != null) {
                    (fragment as SetListFragment).scrollToTop()
                } else {
                    fragment =
                        SetListFragment()
                    setFragment(fragment, MenuEnum.SET_LIST.name)
                }

            }

            MenuEnum.PROFILE -> {

            }
        }
    }

    override fun onBackPressed() {
        if (actualFragment != MenuEnum.HOME) {
            setFragment(MenuEnum.HOME)
        } else {
            super.onBackPressed()
        }
    }
}