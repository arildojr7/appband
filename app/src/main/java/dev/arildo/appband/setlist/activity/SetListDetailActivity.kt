package dev.arildo.appband.setlist.activity

import android.content.Intent
import android.os.Bundle
import dev.arildo.appband.R
import dev.arildo.appband.core.base.BaseActivity
import dev.arildo.appband.databinding.ActivitySetListDetailBinding
import dev.arildo.appband.setlist.adapter.SetListMusiciansAdapter
import dev.arildo.appband.song.activity.SongDetailActivity
import dev.arildo.appband.song.adapter.SongsAdapter

class SetListDetailActivity :
    BaseActivity<ActivitySetListDetailBinding>(R.layout.activity_set_list_detail) {

    private val adapterSongs =
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

    private val adapterMusicians =
        SetListMusiciansAdapter(emptyList()) {
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra("bundle")) {
            val bundle = intent.getBundleExtra("bundle")
            binding.setList = bundle?.getParcelable("setList")
            adapterSongs.setData(binding.setList?.song)
            adapterMusicians.setData(binding.setList?.musician)

            binding.toolbar.run {
                title = getString(R.string.set_list_number, binding.setList?.number.toString())
                setSupportActionBar(this)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowHomeEnabled(true)
            }

        }

        binding.rvSongs.adapter = adapterSongs
        binding.rvMusicians.adapter = adapterMusicians
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
