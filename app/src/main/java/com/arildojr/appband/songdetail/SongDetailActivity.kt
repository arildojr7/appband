package com.arildojr.appband.songdetail

import android.os.Bundle
import android.os.Handler
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SeekBar
import com.arildojr.appband.R
import com.arildojr.appband.core.base.BaseActivity
import com.arildojr.appband.databinding.ActivitySongDetailBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

class SongDetailActivity : BaseActivity<ActivitySongDetailBinding>(R.layout.activity_song_detail) {

    companion object {
        private const val MAX_SCROLL_SPEED = 40
    }

    private var scrollSpeed: Int = 0
    private val mHandler: Handler = Handler()
    val runnableScroll: Runnable = object : Runnable {
        override fun run() {
            binding.webview.scrollBy(0, 1)
            mHandler.postDelayed(
                this,
                (scrollSpeed - MAX_SCROLL_SPEED).toLong().absoluteValue
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra("bundle")) {
            val bundle = intent.getBundleExtra("bundle")
            binding.song = bundle?.getParcelable("song")
        }

        binding.toolbar.run {
            title = binding.song?.title
            subtitle = getString(R.string.format_tone, binding.song?.tone, binding.song?.singer)
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setupSeekBar()
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
                mHandler.removeCallbacks(runnableScroll)

                if (progress != 0) {
                    scrollSpeed = progress
                    runnableScroll.run()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private suspend fun showScrollSpeed() {
        TransitionManager.beginDelayedTransition(binding.clContainer)
        binding.clScrollSpeed.visibility = View.VISIBLE
        delay(4_000)
        TransitionManager.beginDelayedTransition(binding.clContainer)
        binding.clScrollSpeed.visibility = View.GONE
    }

}
