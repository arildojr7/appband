package dev.arildo.appband.song.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import dev.arildo.appband.song.fragment.SongDetailFragment
import dev.arildo.data.song.model.Song

class SongViewPagerAdapter(private val songs: List<Song>?, fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = songs?.size ?: 0

    override fun getItem(position: Int): Fragment {
        return SongDetailFragment.newInstance(songs?.get(position))
    }


}