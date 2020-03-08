package dev.arildo.appband.setlist.activity

import android.content.Intent
import android.os.Bundle
import dev.arildo.appband.R
import dev.arildo.appband.core.base.BaseActivity
import dev.arildo.appband.databinding.ActivitySetListDetailBinding
import dev.arildo.appband.song.activity.SongDetailActivity
import dev.arildo.appband.song.adapter.SongsAdapter

class SetListDetailActivity :
    BaseActivity<ActivitySetListDetailBinding>(R.layout.activity_set_list_detail) {

    private val adapter2 =
        SongsAdapter(emptyList()) {
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
                title = getString(R.string.set_list_number, binding.setList?.number.toString())
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

}
