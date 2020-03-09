package dev.arildo.appband.setlist.activity

import android.content.Intent
import android.os.Bundle
import dev.arildo.appband.R
import dev.arildo.appband.core.base.BaseActivity
import dev.arildo.appband.core.util.BUNDLE
import dev.arildo.appband.core.util.POSITION
import dev.arildo.appband.core.util.SET_LIST
import dev.arildo.appband.databinding.ActivitySetListDetailBinding
import dev.arildo.appband.setlist.adapter.SetListMusiciansAdapter
import dev.arildo.appband.song.activity.SongDetailActivity
import dev.arildo.appband.song.adapter.SongsAdapter
import dev.arildo.data.setlist.model.SetList

class SetListDetailActivity :
    BaseActivity<ActivitySetListDetailBinding>(R.layout.activity_set_list_detail) {

    private var setList: SetList? = null

    private val adapterSongs =
        SongsAdapter(emptyList()) {
            val bundle = Bundle().apply {
                putParcelable(SET_LIST, setList)
                putInt(POSITION, setList?.song?.indexOf(it) ?: 0)
            }
            startActivity(Intent(this, SongDetailActivity::class.java).apply {
                putExtra(BUNDLE, bundle)
            })
        }

    private val adapterMusicians = SetListMusiciansAdapter(emptyList()) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupRecycler()

        intent.getBundleExtra(BUNDLE)?.run {
            setList = getParcelable(SET_LIST)
            adapterSongs.setData(setList?.song)
            adapterMusicians.setData(setList?.musician)
            binding.setList = setList
        }

        setupToolbar()
    }

    private fun setupRecycler() {
        binding.rvSongs.adapter = adapterSongs
        binding.rvMusicians.adapter = adapterMusicians
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupToolbar() {
        binding.toolbar.run {
            title = getString(R.string.set_list_number, binding.setList?.number.toString())
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }

}
