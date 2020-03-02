package com.arildojr.appband.setlist

import android.content.Intent
import android.os.Bundle
import com.arildojr.appband.R
import com.arildojr.appband.core.base.BaseActivity
import com.arildojr.appband.databinding.ActivitySetListDetailBinding
import com.arildojr.appband.songlist.SongDetailActivity
import com.arildojr.appband.songlist.SongsAdapter

class SetListDetailActivity :
    BaseActivity<ActivitySetListDetailBinding>(R.layout.activity_set_list_detail) {

    private val adapter2 = SongsAdapter(emptyList()) {
        val bundle = Bundle()
        bundle.putParcelable("song", it)
        startActivity(Intent(this, SongDetailActivity::class.java).apply {
            putExtra(
                "bundle",
                bundle
            )
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra("bundle")) {
            val bundle = intent.getBundleExtra("bundle")
            binding.setList = bundle?.getParcelable("setList")
            adapter2.setData(binding.setList?.song)

            binding.toolbar.run {
                title = bundle?.getString("setListName")
                setSupportActionBar(this)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowHomeEnabled(true)
            }

        }

        binding.rvSongs.apply {
            adapter = adapter2

            viewTreeObserver.addOnScrollChangedListener {
                binding.llTopHeader.isSelected = this.canScrollVertically(-1)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
