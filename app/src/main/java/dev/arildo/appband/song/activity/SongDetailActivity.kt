package dev.arildo.appband.song.activity

import android.os.Bundle
import android.os.Handler
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SeekBar
import androidx.viewpager.widget.ViewPager
import dev.arildo.appband.R
import dev.arildo.appband.core.base.BaseActivity
import dev.arildo.appband.core.util.BUNDLE
import dev.arildo.appband.core.util.POSITION
import dev.arildo.appband.core.util.SET_LIST
import dev.arildo.appband.core.util.SONG
import dev.arildo.appband.databinding.ActivitySongDetailBinding
import dev.arildo.appband.song.adapter.SongViewPagerAdapter
import dev.arildo.data.setlist.model.SetList
import dev.arildo.data.song.model.Song
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

class SongDetailActivity : BaseActivity<ActivitySongDetailBinding>(R.layout.activity_song_detail),
    ViewPager.OnPageChangeListener {

    companion object {
        private const val MAX_SCROLL_SPEED = 200
        private const val MIN_SCROLL_SPEED = 50
    }

    private var setList: SetList? = null
    private var initialPosition = 0
    private var scrollSpeed: Int = 0
    private val mHandler: Handler = Handler()
    private val runnableScroll: Runnable = object : Runnable {
        override fun run() {
            binding.vpSongs.scrollBy(0, 1)

            mHandler.postDelayed(
                this,
                (scrollSpeed - MAX_SCROLL_SPEED).absoluteValue.toLong()
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getBundleExtra(BUNDLE)?.run {
            getParcelable<Song?>(SONG)?.let {
                setList = SetList(song = listOf(it))
            } ?: run {
                setList = getParcelable(SET_LIST)
            }

            initialPosition = getInt(POSITION)
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setupSeekBar()
        setupViewPager()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        binding.song = setList?.song?.get(initialPosition)
    }

    private fun setupViewPager() {
        binding.vpSongs.adapter = SongViewPagerAdapter(setList?.song, supportFragmentManager)
        binding.vpSongs.addOnPageChangeListener(this)
        binding.vpSongs.currentItem = initialPosition
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.song_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btnAutoScroll -> {
                launch {
                    showScrollSpeed()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupSeekBar() {
        binding.sbScrollSpeed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                stopAutoScroll()

                startAutoScroll(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun stopAutoScroll() {
        mHandler.removeCallbacks(runnableScroll)
    }

    private fun startAutoScroll(velocity: Int) {
        if (velocity != MIN_SCROLL_SPEED) {
            scrollSpeed = velocity
            runnableScroll.run()
        }
    }

    private suspend fun showScrollSpeed() {
        TransitionManager.beginDelayedTransition(binding.clContainer)
        binding.clScrollSpeed.visibility = View.VISIBLE

        if (binding.sbScrollSpeed.progress == MIN_SCROLL_SPEED) {
            binding.sbScrollSpeed.progress = MIN_SCROLL_SPEED + 1
        }

        delay(4_000)
        TransitionManager.beginDelayedTransition(binding.clContainer)
        binding.clScrollSpeed.visibility = View.GONE
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        stopAutoScroll()
        binding.song = setList?.song?.get(position)
    }

}
