package com.arildojr.appband.songdetail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.arildojr.appband.R
import com.arildojr.appband.core.base.BaseActivity
import com.arildojr.appband.databinding.ActivitySongDetailBinding
import com.arildojr.appband.songlist.SongViewModel
import org.koin.android.ext.android.inject

class SongDetailActivity : BaseActivity<ActivitySongDetailBinding>(R.layout.activity_song_detail) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}
